Networks-MacBook-Pro:~ nsa$ curl http://localhost:8080/movies

[{"id":"a8e2c492-1968-4542-9772-eb6d32b1869b","title":"Aeon Flux","genre":" action","director":" trudeu","rating":4.0},{"id":"cbcc69c6-c3bc-4543-932c-553050347d76","title":"Back to the Fluxes","genre":" action","director":" trudeu","rating":4.0},{"id":"3a6432ef-7665-465b-a6c4-34cd4a797d4e","title":"Enter the Mono","genre":" rom-com","director":" shawn","rating":4.0},{"id":"4b2d86b7-fd62-4a80-a090-2eb37f304053","title":"Fluxinator","genre":" action","director":" trudeu","rating":4.0},{"id":"473c1287-d205-4704-81cc-617a3f6e9e8c","title":"Silence of Lambdas","genre":" drama","director":" mathew","rating":4.0},{"id":"cc110bcf-9a91-4bd5-b11f-f337cf6cec7c","title":"Kill Lambdas","genre":" action","director":"christopher","rating":4.0},{"id":"bb06326f-b444-4db4-a609-0a8502abbfba","title":"Attack of the Fluxes","genre":" thriller","director":"christopher","rating":4.0},{"id":"046b304b-428c-4fd2-aac0-76df4d0ea0b7","title":"Reactive Mongos on a Plane","genre":"horror","director":" mathew","rating":4.0},{"id":"9e57608a-eb16-4594-a6b7-a42c88dd8684","title":"Fluxxyy","genre":" action","director":" trudeu","rating":4.0}]

Networks-MacBook-Pro:~ nsa$

Networks-MacBook-Pro:~ nsa$ curl http://localhost:8080/movies | json_pp
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100  1124    0  1124    0     0  86461      0 --:--:-- --:--:-- --:--:-- 86461
[
   {
      "rating" : 4,
      "title" : "Aeon Flux",
      "id" : "a8e2c492-1968-4542-9772-eb6d32b1869b",
      "director" : " trudeu",
      "genre" : " action"
   },
   {
      "genre" : " action",
      "director" : " trudeu",
      "rating" : 4,
      "title" : "Back to the Fluxes",
      "id" : "cbcc69c6-c3bc-4543-932c-553050347d76"
   },
   {
      "genre" : " rom-com",
      "director" : " shawn",
      "rating" : 4,
      "title" : "Enter the Mono",
      "id" : "3a6432ef-7665-465b-a6c4-34cd4a797d4e"
   },
   {
      "rating" : 4,
      "title" : "Fluxinator",
      "id" : "4b2d86b7-fd62-4a80-a090-2eb37f304053",
      "director" : " trudeu",
      "genre" : " action"
   },
   {
      "genre" : " drama",
      "director" : " mathew",
      "title" : "Silence of Lambdas",
      "rating" : 4,
      "id" : "473c1287-d205-4704-81cc-617a3f6e9e8c"
   },
   {
      "genre" : " action",
      "director" : "christopher",
      "id" : "cc110bcf-9a91-4bd5-b11f-f337cf6cec7c",
      "title" : "Kill Lambdas",
      "rating" : 4
   },
   {
      "director" : "christopher",
      "genre" : " thriller",
      "id" : "bb06326f-b444-4db4-a609-0a8502abbfba",
      "rating" : 4,
      "title" : "Attack of the Fluxes"
   },
   {
      "genre" : "horror",
      "director" : " mathew",
      "id" : "046b304b-428c-4fd2-aac0-76df4d0ea0b7",
      "title" : "Reactive Mongos on a Plane",
      "rating" : 4
   },
   {
      "genre" : " action",
      "director" : " trudeu",
      "id" : "9e57608a-eb16-4594-a6b7-a42c88dd8684",
      "rating" : 4,
      "title" : "Fluxxyy"
   }
]
Networks-MacBook-Pro:~ nsa$ curl http://localhost:8080/movies/9e57608a-eb16-4594-a6b7-a42c88dd8684
{"id":"9e57608a-eb16-4594-a6b7-a42c88dd8684","title":"Fluxxyy","genre":" action","director":" trudeu","rating":4.0}Networks-MacBook-Pro:~ nsa$ 
Networks-MacBook-Pro:~ nsa$ 
Networks-MacBook-Pro:~ nsa$ 
Networks-MacBook-Pro:~ nsa$ curl http://localhost:8080/movies/9e57608a-eb16-4594-a6b7-a42c88dd8684/events
data:{"movie":{"id":"9e57608a-eb16-4594-a6b7-a42c88dd8684","title":"Fluxxyy","genre":" action","director":" trudeu","rating":4.0},"when":"2020-06-19T07:32:08.045+00:00","user":"mkkhan"}

data:{"movie":{"id":"9e57608a-eb16-4594-a6b7-a42c88dd8684","title":"Fluxxyy","genre":" action","director":" trudeu","rating":4.0},"when":"2020-06-19T07:32:09.056+00:00","user":"ifatima"}

data:{"movie":{"id":"9e57608a-eb16-4594-a6b7-a42c88dd8684","title":"Fluxxyy","genre":" action","director":" trudeu","rating":4.0},"when":"2020-06-19T07:32:10.049+00:00","user":"mkkhan"}

data:{"movie":{"id":"9e57608a-eb16-4594-a6b7-a42c88dd8684","title":"Fluxxyy","genre":" action","director":" trudeu","rating":4.0},"when":"2020-06-19T07:32:11.048+00:00","user":"ifatima"}

data:{"movie":{"id":"9e57608a-eb16-4594-a6b7-a42c88dd8684","title":"Fluxxyy","genre":" action","director":" trudeu","rating":4.0},"when":"2020-06-19T07:32:12.045+00:00","user":"maftab"}

data:{"movie":{"id":"9e57608a-eb16-4594-a6b7-a42c88dd8684","title":"Fluxxyy","genre":" action","director":" trudeu","rating":4.0},"when":"2020-06-19T07:32:13.045+00:00","user":"ishaq"}
Networks-MacBook-Pro:~ nsa$ 