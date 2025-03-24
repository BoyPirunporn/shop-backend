## Build stage
#FROM maven:3.8.1-openjdk-17 as build
#WORKDIR /apps
#COPY . .
#COPY pom.xml .
#RUN mvn -f pom.xml clean install -DskipTests

# Package stage
FROM openjdk:17.0.1-jdk-slim

# กำหนด Environment Variable
ENV MAVEN_VERSION=3.9.9
ENV MAVEN_HOME=/usr/share/maven
ENV PATH="${MAVEN_HOME}/bin:${PATH}"
WORKDIR /apps
# ติดตั้ง maven
RUN apt-get update \
    && apt-get install -y curl tar iputils-ping netcat curl \
    && curl -fsSL https://downloads.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
    | tar -xz -C /usr/share \
    && ln -s /usr/share/apache-maven-${MAVEN_VERSION} ${MAVEN_HOME}


# เช็ค mavem
RUN mvn --version

COPY . .
#COPY --from=build /apps/shop-ecommerce.jar /apps/application.jar

RUN mvn -f pom.xml clean install -DskipTests


COPY ./target/shop-ecommerce.jar /apps/application.jar
# ตั้งค่า entrypoint ให้ใช้ไฟล์ jar ที่ต้องการ
ENTRYPOINT ["java","-jar", "/apps/application.jar"] \
EXPOSE 8080