# Alan Systems Task

## Description
Simple web application with REST endpoint and database entries replication service.

## Requirements
1. Latest JDK
2. Git

## Configuration
1. `alertcontroller.requestmapping.value=/api/alerts` can be configured to set default path for REST endpoint
2. `logging.level.com.wiktorkielar=DEBUG` can be set to DEBUG to enable logging DB state

## Building and Running
1. `git clone https://github.com/wiktorkielar/alan-systems-task.git .`
2. `./mvnw clean install`
3. `java -jar target/alan-systems-task-0.0.1-SNAPSHOT.jar`

## Running Unit Tests
1. `./mvnw test`