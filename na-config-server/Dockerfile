FROM java:8-jre

MAINTAINER Vladyslav Yemelianov <emelyanov.vladyslav@gmail.com>

ADD ./target/na-config-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/na-config-server.jar"]

EXPOSE 8888