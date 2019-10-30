#!/usr/bin/env bash
java -jar -Dspring.profiles.active=server1 target/eureka-0.0.1-SNAPSHOT.jar &
java -jar -Dspring.profiles.active=server2 target/eureka-0.0.1-SNAPSHOT.jar &
java -jar -Dspring.profiles.active=server3 target/eureka-0.0.1-SNAPSHOT.jar & 
