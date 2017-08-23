FROM openjdk:8u121-jre-alpine

RUN apk add --no-cache --update curl tzdata ca-certificates java-cacerts
RUN update-ca-certificates

ENV LANG en_SG.UTF-8
ENV LANGUAGE en_SG:en
ENV LC_ALL en_SG.UTF-8
ENV TZ='Asia/Singapore'

RUN mkdir /config && chown -R 1000:1001 /config

ADD stixcloud-eureka.jar app.jar
RUN sh -c 'touch /app.jar'
#ENV JAVA_OPTS="-Dspring.profiles.active=docker -Xmx512m -Xss512k"
#ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar