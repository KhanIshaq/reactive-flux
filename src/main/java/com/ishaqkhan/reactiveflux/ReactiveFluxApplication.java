package com.ishaqkhan.reactiveflux;

import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.http.MediaType;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.ServerResponse.BodyBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

//Static Imports
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class ReactiveFluxApplication {

	@Bean
	RouterFunction<?> routes(ReactiveFluxService service){
		return route(GET("/movies"), request -> ok().body(service.all(), Movie.class))
			   .andRoute(GET("/movies/{id}"), request -> ok().body(service.byId(request.pathVariable("id")), Movie.class))
			   .andRoute(GET("/movies/{id}/events"), request -> ok().contentType(MediaType.TEXT_EVENT_STREAM).body(service.byId(request.pathVariable("id"))
					   															 .flatMapMany(service::streamStreams), MovieEvent.class));
					   												
	}
	//Before you run this Boot Application through Run Command in an IDE. Please start the Mongo Server 
	//On MacOS you can start Mongo Server using $ mongod command
	@Bean
	CommandLineRunner demo(MovieRepository movieRepository) {
		return args -> {
			
			movieRepository.deleteAll()
						   .subscribe(null, null, () -> {
					  Stream.of("Aeon Flux", "Back to the Fluxes", "Enter the Mono",
									"Fluxinator", "Silence of Lambdas", "Kill Lambdas", 
									"Reactive Mongos on a Plane", "Attack of the Fluxes", "Fluxxyy")
							.map(name -> new Movie(UUID.randomUUID().toString(), name, randomGenre(), randomDirector(), new Double(4.0)))
							.forEach(movie -> movieRepository.save(movie).subscribe(System.out::println));
			});
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ReactiveFluxApplication.class, args);
	}

	private String randomGenre() {
		String[] genres = "horror, rom-com, drama, action, thriller".split(",");
		return genres[new Random().nextInt(genres.length)];
	}
	
	private String randomDirector() {
		String[] directors = "christopher, james, trudeu, shawn, mathew".split(",");
		return directors[new Random().nextInt(directors.length)];
	}
}

@Service
class ReactiveFluxService{
	
	private final MovieRepository movieRepository;
	
	ReactiveFluxService(MovieRepository movieRepository){
		this.movieRepository = movieRepository;
	}
	
	public Flux<MovieEvent> streamStreams(Movie movie){
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
		Flux<MovieEvent> events = Flux.fromStream(Stream.generate(() -> new MovieEvent(movie, new Date(), randomUser())));
		
		return Flux.zip(interval, events).map(Tuple2::getT2);
	}
	
	public Flux<Movie> all(){
		return movieRepository.findAll();
	}
	
	public Mono<Movie> byId(String id){
		return movieRepository.findById(id);
	}
	
	private String randomUser() {
		String[] users = "ishaq,maftab,shnndarvish,mkkhan,ifatima".split(",");
		return users[new Random().nextInt(users.length)];
	}
}
@RestController
@RequestMapping("/movies")
class MovieRestController{
	private final ReactiveFluxService reactiveFluxService;
	
	public MovieRestController(ReactiveFluxService reactiveFluxService) {
		this.reactiveFluxService = reactiveFluxService;
	}
	
	@GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<MovieEvent> events(@PathVariable String id){
		return reactiveFluxService.byId(id)
								  .flatMapMany(reactiveFluxService::streamStreams);
	}
	
	@GetMapping
	public Flux<Movie> all(){
		return reactiveFluxService.all();
	}
	
	@GetMapping("/{id}")
	public Mono<Movie> byId(@PathVariable String id){
		return reactiveFluxService.byId(id);
	}
}

interface MovieRepository extends ReactiveMongoRepository<Movie, String>{
	
}

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
class MovieEvent{
	private Movie movie;
	private Date when;
	private String user;
}

@Document
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
class Movie{
	
	@Id
	private String id;
	private String title;
	private String genre;
	private String director;
	private Double rating;
}