FROM openjdk:8u121-jre-alpine

RUN apk add --no-cache --update curl tzdata ca-certificates java-cacerts

ENV LANG en_SG.UTF-8
ENV LANGUAGE en_SG:en
ENV LC_ALL en_SG.UTF-8
ENV TZ='Asia/Singapore'

RUN mkdir /config && chown -R 1000:1001 /config

ADD *.crt /etc/ssl/certs/
ENV JAVA_CACERTS $JAVA_HOME/jre/lib/security/cacerts
RUN keytool -noprompt -import -trustcacerts -file /etc/ssl/certs/sit.sinopayonline.com.crt -alias sinopay-uat -keystore ${JAVA_CACERTS} -storepass changeit
RUN keytool -noprompt -import -trustcacerts -file /etc/ssl/certs/www.sinopayonline.com.crt -alias sinopay-prod -keystore ${JAVA_CACERTS} -storepass changeit
RUN update-ca-certificates

ADD stixcloud-paymentgateway.jar app.jar
RUN sh -c 'touch /app.jar'
#ENV JAVA_OPTS="-Dspring.profiles.active=live -Dspring.cloud.kubernetes.secrets.enable-api=true -Dspring.cloud.kubernetes.secrets.name=stixcloud-secrets"
#ENV JAVA_OPTS="-Dspring.profiles.active=live -Xmx512m -Xss512k -Dspring.cloud.kubernetes.secrets.paths=/etc/secrets"
#ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar