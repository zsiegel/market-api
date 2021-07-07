FROM openjdk:14-alpine
COPY build/libs/market-api-*-all.jar market-api.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "market-api.jar"]