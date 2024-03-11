FROM openjdk:21

LABEL author="Andres Campaz"

ENV DATABASE_DRIVER org.postgresql.Driver
ENV DATABASE_URL jdbc:postgresql://dpg-cnnik5779t8c739idl5g-a.oregon-postgres.render.com:5432/projectify
ENV DATABASE_USERNAME projectify_dev
ENV DATABASE_PASSWORD gfFcYdpWUGWCce5Tsz0NehEZTssmlija
ENV DATABASE_PLATFORM org.hibernate.dialect.PostgreSQLDialect

COPY target/projectify-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java", "-jar", "java-app.jar"]