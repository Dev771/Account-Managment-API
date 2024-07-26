FROM openjdk:17-oracle
ADD target/config-server.jar config-server.jar
ENTRYPOINT [ "java", "-jar", "/config-server.jar" ]