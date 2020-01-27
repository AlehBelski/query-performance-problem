# Query Performance Problem
## Requirements
In company ABC we run quite a few queries against our databases, and the queries have different performance
characteristics. We frequently need to work on optimizing them. We want to build a system that allow us to
compare different versions of the same query and be able to benchmark the performance of its versions.

Build a system to perform this benchmarking with the following characteristics:
* it is a restful service built with Spring (and optionally Spring Boot)
* it can execute a performance test
* it measures the time for each query to complete (work time)
* exactly one of the performance tests can execute at any point of time against a database installation.
* tests against different database installations can execute in parallel. So if a user starts a test for query Q, this
* test can execute in parallel against databases A,B,C and collect the results in a "report"

Define a data model, define the rest api and write the code for the service.

## Application description
* This is the RESTfull application that can consume/produce the json format request/response accordingly.
* The configuration locates at _resources/application.properties_
    * The # DataBases properties section contains information about a configuration for the databases
    * The # Custom properties section contains custom properties that can impact on the behavior of the application
* The measurement work based on the StopWatch library and return the time in milliseconds.

## How to use
* Open the _application.properties_ file and edit the connection to the databases, using the following template
    * list.databases[n].url - the jdbc url to connect to a database, example - _jdbc:postgresql://localhost:5432/postgres_
    * list.databases[n].username - the username for database connection
    * list.databases[n].password - the password for database connection
    * list.databases[n].dbName - the database name \
    Where **n** - the ordered number of the configurable database
* To run the application please open a new command line and pass the following command - _**mvn spring-boot:run**_
* After the command will be completed the application will be started at - _**localhost:8080**_
* After that you are able to send a request to _localhost:8080/performance/measure_
* To stop the application please open the command line where you started the application and click Ctrl+C, after that you
will be asked to Terminate the job, put Y for Yes and N for No and click Enter.

## Example
**Request**: \
POST localhost:8080/performance/measure
```json
[
    {
    	"query": "SELECT * FROM entity"
    },
    {
    	"query": "SELECT name FROM entity"
    }
]
```
**Response**
```json
[
    {
        "database": "myDB",
        "query": "SELECT * FROM entity",
        "averageTime": 0
    },
    {
        "database": "myDB",
        "query": "SELECT name FROM entity",
        "averageTime": 1
    },
    {
        "database": "postgres",
        "query": "SELECT * FROM entity",
        "averageTime": 0
    },
    {
        "database": "postgres",
        "query": "SELECT name FROM entity",
        "averageTime": 0
    }
]
```
