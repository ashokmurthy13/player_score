# player_score

## Build the Project

### From CommandLine

Go to the project path - project home folder and run either of the below command based on the installation.

* If gradle is installed on your local system, please use the below command.

      >./gradle build (Mac or Linux)

      > gradle.bat build (Windows)

* If gradle is not installed on your local system, please use the below command.

      >./gradlew build (Mac or Linux)
     
      > gradlew.bat build (Windows)

### From IDE
* You can use the IDE Gradle plugins to build the project

## Run the Project

### From Command Line

```java -jar build/libs/player_score-0.0.1-SNAPSHOT.jar```

### From IntelliJ IDE

```Run PlayerApplication.java file as Java Application```
## Run Tests

### Unit and Integration tests

    >./gradlew test (or) >gradle test (Mac or Linux)
    >gradle.bat test (Windows)

### Sample Console output

```
ashokmurthy@Ashoks-MacBook-Pro player_score % gradle test   

> Task :compileTestJava
Note: /Users/ashokmurthy/player_score/src/test/java/integrationtests/PlayerControllerTest.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.

> Task :test

com.oyo.player.score.app.PlayerApplicationTest

  Test contextLoads() PASSED

com.oyo.player.score.app.PlayerServiceTest

  Test testDeleteScoreById() PASSED
  Test testGetPlayersHistory() PASSED
  Test testCreateScoreException() PASSED
  Test testFindScoreById() PASSED
  Test testFindScoreByIdException() PASSED

com.oyo.player.score.app.ValidationRequestTest

  Test testNullPlayerValidation() PASSED
  Test testScoreValidation() PASSED
  Test testDateValidation() PASSED
  Test testDateFormatPlayerValidation() PASSED

integrationtests.PlayerControllerTest

  Test testGetScorePlayer() PASSED
  Test testGetPlayerHistory() PASSED
  Test testGetScoreHttpStatus() PASSED
2022-02-16 11:50:50.516  INFO 28113 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2022-02-16 11:50:50.517  INFO 28113 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2022-02-16 11:50:50.518  INFO 28113 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
2022-02-16 11:50:50.525  INFO 28113 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2022-02-16 11:50:50.525  INFO 28113 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Shutdown initiated...
2022-02-16 11:50:50.526  INFO 28113 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Shutdown completed.

SUCCESS: Executed 13 tests in 3.8s



```

**Test reports can be found at : build/reports/tests/test/index.html**

## APIs

* Swagger Documentation : http://localhost:8080/swagger-ui.html

### Create Score
#### Request

```
POST /restapi/v1/player/score

Body:
{
  "player" : "john",
  "score" : 8,
  "time": "2022-02-10 11:40:03"
}
```
```
curl -X POST "http://localhost:8080/restapi/v1/player/score" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"player\": \"ashok\", \"score\": 90, \"time\": \"2022-02-15 15:30:00\"}"
```
#### Response
```
{
    "message": "success",
    "status": "OK",
    "result": {
        "id": 1,
        "player": "john",
        "score": 8,
        "time": "2022-02-10T11:40:03"
    },
    "timestamp": "2022-02-13T11:40:32.866"
}
```

### Get Score
#### Request

```
GET /restapi/v1/player/score?scoreId=1
```
```
curl -X GET "http://localhost:8080/restapi/v1/player/score?scoreId=1" -H "accept: */*"
```
#### Response
```
{
    "message": "success",
    "status": "OK",
    "result": {
        "id": 1,
        "player": "john",
        "score": 8,
        "time": "2022-02-10T11:40:03"
    },
    "timestamp": "2022-02-13T11:42:26.986"
}
```
### Delete Score
#### Request
```
DELETE /restapi/v1/player/score?scoreId=1
```
```
curl -X DELETE "http://localhost:8080/restapi/v1/player/score?scoreId=12" -H "accept: */*"
```
#### Response
```
{
    "message": "success",
    "status": "OK",
    "result": "deleted score with id 1",
    "timestamp": "2022-02-13T11:43:18.398"
}
```

### Get list of scores
#### Request

```
GET /restapi/v1/player/scores

Parameters
----------
players : array[string]
afterDate : [string]
beforeDate : [string]
pagination
page : integer (page number)
size : integer (page size)

Examples
--------
GET /restapi/v1/player/scores?players='john','king'&beforeDate='2022-02-14 09:00:00'&afterDate='2022-02-14 23:0:00'
GET /restapi/v1/player/scores?players='john'&size=5&page=0
```
```
curl -X GET "http://localhost:8080/restapi/v1/player/scores?afterDate=2022-02-12&beforeDate=2022-02-15&page=0&pageSize=10" -H "accept: */*"
```
#### Response
```
{
  "message": "string",
  "result": {
    "pageNumber": 0,
    "pageSize": 0,
    "scores": [
      {
        "id": 0,
        "player": "string",
        "score": 0,
        "time": "2022-02-14T04:19:35.895Z"
      }
    ],
    "totalElements": 0
  },
  "status": "100 CONTINUE",
  "timestamp": "2022-02-14T04:19:35.895Z"
}
```

### Get Players History
#### Request

```
GET /restapi/v1/player/history?player=string
```
```
curl -X GET "http://localhost:8080/restapi/v1/player/history?player=ashok" -H "accept: */*"
```
#### Response
```
{
  "message": "string",
  "result": {
    "avgScore": 0,
    "lowScore": {
      "score": 0,
      "time": "2022-02-14T07:40:26.446Z"
    },
    "scores": [
      {
        "score": 0,
        "time": "2022-02-14T07:40:26.446Z"
      }
    ],
    "topScore": {
      "score": 0,
      "time": "2022-02-14T07:40:26.446Z"
    }
  },
  "status": "100 CONTINUE",
  "timestamp": "2022-02-14T07:40:26.446Z"
}
```