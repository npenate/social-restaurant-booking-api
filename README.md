# Social Restaurant Booking API :rocket:
REST API that aims booking restaurant tables.

## Table of contents
- [Solution considerations](#Solution-considerations)
- [Environment](#Environment)
- [Documentation](#Documentation)
- [Postman Collection](#postman-collection)
- [Stack](#stack)
- [Build and Run](#Build-and-Run)
- [Dummy data](#Dummy-data)

## Solution considerations
* **Zip codes**  
Since it was not possible to consult the Google API for free, the postal codes were saved directly in the database. The 
goal was get zip code from google api giving latitude and longitude. The present solution is based on first search by 
zip codes filtering the large number of elements to only those of the locality, speding up the remaining process.

* **Latitude and Longitude**
Coordinates could be used for give the list of restaurants ordered by distance to a concentric point between all the 
diners. For that we can use [Eequirectangular Projection](https://en.wikipedia.org/wiki/Equirectangular_projection),
this is no accurate in terms of exact distances but for ordering seems reasonable comparing it with the 
[Great Circle Distance](https://en.wikipedia.org/wiki/Great-circle_distance#:~:text=The%20great%2Dcircle%20distance%2C%20orthodromic,line%20through%20the%20sphere's%20interior)
which is too expensive. So the main idea was saving location in a PostgreSQL point and define an operator and ordering by the 
distance to a specific (concentric) point. This part is not implemented.

* **Double Booking**
This was something I put to last and didn't have time, maybe we can discuss it a bit ;).

* **Default endorsement/restriction**
The solution works to always find a match between endorsements and constraints. A diner with empty restrictions will 
not match any restaurant. Therefore, there must be a default restriction/endorsement at each diner/restaurant. 
In the dummy data I used `FOOD`.
 
## Environment
* Localhost: http://localhost:8080
* Test

## Documentation
**API Docs:** After running the project locally you can see the documentation in http://localhost:8080/swagger-ui.html

### Postman Collection
[![Run in Postman](https://run.pstmn.io/button.svg)](https://god.postman.co/run-collection/10b1cae13c12071628a2)  
Or you can see the collection in this repo. Anyway, the samples in the collection are in sync with the dummy data.

## Stack ##
* **Language:** [Kotlin](https://kotlinlang.org/)
* **Web framework:** [Spring Boot](https://spring.io/projects/spring-boot)
* **Database:** [PostgreSQL](https://www.postgresql.org)
* **Build & Dependency management:** [Gradle](https://gradle.org/)
* **Tests:** [Mockk](https://mockk.io/)
* **Static code analysis:** [Detekt](https://detekt.github.io/detekt/)

 ## Build and Run ##
 ### Via Docker ### 
 #### Prerequisite ####
 You need to have [Docker](https://www.docker.com) installed. 
 ```shell
 $  docker-compose up
 ```
 ### Via Gradle ### 
 #### Prerequisite ####
You need to set `NELO_TEST_DATABASE_URL` and/or `NELO_TEST_DATABASE_URL_FOR_TESTS` environment variables. Depending on
if you want to run or test the application.
For run:
 ```shell
 $  ./gradlew bootRun
 ```
For test:
 ```shell
 $  ./gradlew test
 ```
 ## Dummy data ##
 The dummy data is in the [last migration](src/main/resources/db/migration/V11__Add_initial_data.sql)
