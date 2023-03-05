FROM openjdk:11
EXPOSE 9111
ADD target/springboot-redis.jar springboot-redis.jar
ENTRYPOINT ["java","-jar","/springboot-redis.jar"]