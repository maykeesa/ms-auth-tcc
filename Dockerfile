FROM maven as build

WORKDIR /auth
COPY . .

RUN mvn clean package -Dspring.profiles.active=dev

FROM ghcr.io/graalvm/jdk-community:17

WORKDIR /auth
COPY --from=build /auth/target/ms-auth-0.0.1-SNAPSHOT.jar ./app.jar

CMD java -jar app.jar