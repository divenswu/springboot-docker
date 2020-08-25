FROM anapsix/alpine-java:latest

MAINTAINER springboot-docker

WORKDIR /app/springboot-docker

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/{TZ} /etc/localtime && echo "{TZ}" > /etc/timezone

ADD target/springboot-docker.jar /app/springboot-docker

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/springboot-docker/springboot-docker.jar"]
