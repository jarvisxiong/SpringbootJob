FROM openjdk:8-jdk-alpine

RUN apk add --no-cache --update curl tzdata ca-certificates java-cacerts
RUN update-ca-certificates

ENV LANG en_SG.UTF-8
ENV LANGUAGE en_SG:en
ENV LC_ALL en_SG.UTF-8
ENV TZ='Asia/Singapore'

ADD stixcloud-be.jar app.jar
RUN mkdir /config && chown -R 1000:1001 /config
RUN sh -c 'touch /app.jar'

EXPOSE 80
HEALTHCHECK --interval=5s --timeout=3s --retries=3 CMD curl -f http://localhost:80/health || exit 1

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar