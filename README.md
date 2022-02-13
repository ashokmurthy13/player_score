# player_score

## Build & Run

### How to Build
Go to the project path - project home folder and run the below command

```gradle build```

### How to Run

Run from Command Line

```java -jar build/libs/player_score-0.0.1-SNAPSHOT.jar```

Run from IntelliJ

```Run PlayerApplication.java file as Java Application```

## APIs

### Create Score
```
Request
-------
POST /restapi/v1/player/score HTTP1
Body:
{
  "player" : "john",
  "score" : 8,
  "time": "2022-02-10 11:40:03"
}

Response
--------
{
    "message": "success",
    "status": "OK",
    "result": {
        "id": 1,
        "player": "ashok",
        "score": 8,
        "time": "2022-02-10T11:40:03"
    },
    "timestamp": "2022-02-13T11:40:32.866"
}
```

### Get Score
```
Request
-------

GET /restapi/v1/player/score?scoreId=1

Response
--------
{
    "message": "success",
    "status": "OK",
    "result": {
        "id": 1,
        "player": "ashok",
        "score": 8,
        "time": "2022-02-10T11:40:03"
    },
    "timestamp": "2022-02-13T11:42:26.986"
}
```
### Delete Score
```
Request
-------

DELETE /restapi/v1/player/score?scoreId=1

Response
--------
{
    "message": "success",
    "status": "OK",
    "result": "deleted score with id 1",
    "timestamp": "2022-02-13T11:43:18.398"
}
```