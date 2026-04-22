FROM maven:3.9.9-eclipse-temurin-21 as build

WORKDIR /workspace

COPY ../pom.xml .
COPY ../polaris-flash-cards-domain/pom.xml polaris-flash-cards-domain/pom.xml
COPY ../polaris-flash-cards-application/pom.xml polaris-flash-cards-application/pom.xml
COPY ../polaris-flash-cards-data-access/pom.xml polaris-flash-cards-data-access/pom.xml
COPY ../polaris-flash-cards-web/pom.xml polaris-flash-cards-web/pom.xml
COPY ../polaris-flash-cards-container/pom.xml polaris-flash-cards-container/pom.xml

RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -q -pl polaris-flash-cards-container -am dependency:go-offline

COPY . .

RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -DskipTests -pl polaris-flash-cards-container -am package

COPY polaris-flash-cards-container/target/*.jar /tmp/polaris-flash-cards.jar

FROM eclipse-temurin:21-jre-alpine AS runtime

WORKDIR /polaris-flash-cards

COPY --from=build /tmp/polaris-flash-cards.jar /polaris-flash-cards/polaris-flash-cards.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/polaris-flash-cards/polaris-flash-cards.jar"]