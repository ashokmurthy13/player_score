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
        "player": "john",
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
        "player": "john",
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

### Get list of scores

```
Request
-------
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

Response
--------
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

```
Request
-------

GET /restapi/v1/player/history?player=string

Response
--------
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