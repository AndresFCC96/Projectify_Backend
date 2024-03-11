FROM openjdk:21

LABEL author="Andres Campaz"

ENV DATABASE_DRIVER org.postgresql.Driver
ENV DATABASE_URL jdbc:postgresql://127.0.0.1:5432/Projectify
ENV DATABASE_USERNAME postgres
ENV DATABASE_PASSWORD Andres1738
ENV DATABASE_PLATFORM org.hibernate.dialect.PostgreSQLDialect

COPY /target/projectify-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java", "-jar", "java-app.jar"]