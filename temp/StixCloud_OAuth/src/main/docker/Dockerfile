FROM openjdk:8u121-jre-alpine

RUN apk add --no-cache --update curl tzdata ca-certificates java-cacerts
RUN update-ca-certificates

ENV LANG en_SG.UTF-8
ENV LANGUAGE en_SG:en
ENV LC_ALL en_SG.UTF-8
ENV TZ='Asia/Singapore'

RUN mkdir /config && chown -R 1000:1001 /config

ADD stixcloud-oauth.jar app.jar
RUN sh -c 'touch /app.jar'
#ENV JAVA_OPTS="-Dspring.profiles.active=live -Xmx1024m -Xss512k -Dspring.cloud.kubernetes.secrets.paths=/etc/secrets"
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar