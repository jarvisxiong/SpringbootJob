FROM openjdk:8u121-jre-alpine

RUN apk add --no-cache --update curl tzdata ca-certificates java-cacerts
RUN update-ca-certificates

ENV LANG en_SG.UTF-8
ENV LANGUAGE en_SG:en
ENV LC_ALL en_SG.UTF-8
ENV TZ='Asia/Singapore'

RUN mkdir /config && chown -R 1000:1001 /config

ADD stixcloud-cart.jar app.jar
RUN sh -c 'touch /app.jar'
#ENV JAVA_OPTS="-Dspring.profiles.active=dev -Dlogging.config=/tmp/log4j2-spring.xml -Deureka.client.enabled=false -Dribbon.eureka.enabled=false -Dfeign.hystrix.enabled=false"
#ENV JAVA_OPTS="-Dspring.profiles.active=live -Dspring.cloud.kubernetes.secrets.enable-api=true -Dspring.cloud.kubernetes.secrets.name=stixcloud-secrets"
#ENV JAVA_OPTS="-Dspring.profiles.active=live -Xmx1024m -Xss512k -Dspring.cloud.kubernetes.secrets.paths=/etc/secrets"
#ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar