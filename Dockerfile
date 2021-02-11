FROM openjdk:14-alpine
COPY target/smartquiz-api-*.jar smartquiz-api.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "smartquiz-api.jar"]