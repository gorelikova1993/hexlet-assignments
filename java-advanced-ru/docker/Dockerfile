FROM eclipse-temurin:20-jdk AS builder

# Устанавливаем Gradle вручную
ARG GRADLE_VERSION=8.2

RUN apt-get update && apt-get install -yq make unzip wget \
  && wget -q https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
  && unzip gradle-${GRADLE_VERSION}-bin.zip \
  && rm gradle-${GRADLE_VERSION}-bin.zip

ENV GRADLE_HOME=/opt/gradle
RUN mv gradle-${GRADLE_VERSION} ${GRADLE_HOME}
ENV PATH=$PATH:$GRADLE_HOME/bin

COPY . /app

WORKDIR /app

RUN gradle clean build

FROM eclipse-temurin:20-jdk

COPY --from=builder /app/build/libs/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]