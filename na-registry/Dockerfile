FROM adoptopenjdk/openjdk11

MAINTAINER Vladyslav Yemelianov <emelyanov.vladyslav@gmail.com>

ADD ./target/na-registry.jar /app/
CMD ["java", "-Xmx300m", "-jar", "/app/na-registry.jar"]

EXPOSE 8761