FROM openjdk:17-jdk-slim

LABEL maintainer="Sibonelo Msabala <stmsabala@gmail.com>"

RUN apt-get update
RUN apt-get -y install curl
RUN apt-get install -y maven
RUN apt-get install -y p7zip \
    p7zip-full \
    unace \
    zip \
    unzip \
    bzip2
RUN echo 'debconf debconf/frontend select Noninteractive' | debconf-set-selections
RUN apt-get install -y -q

ENV DEBIAN_FRONTEND noninteractive

#Install Chrome
RUN curl http://dl.google.com/linux/chrome/deb/pool/main/g/google-chrome-stable/google-chrome-stable_109.0.5414.74-1_amd64.deb -o /chrome.deb
RUN ls
RUN apt-get install -y ./chrome.deb
RUN rm /chrome.deb

#Install chromedriver for Selenium
RUN curl https://chromedriver.storage.googleapis.com/109.0.5414.74/chromedriver_linux64.zip -o /tmp/chromedriver.zip \
    && unzip /tmp/chromedriver.zip -d /bin/ \
    && rm /tmp/chromedriver.zip
RUN chmod +x /bin/chromedriver

ADD target/comparisonServer-0.1.0-SNAPSHOT-jar-with-dependencies.jar /srv/comparisonServer-0.1.0-SNAPSHOT.jar

WORKDIR /srv
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "comparisonServer-0.1.0-SNAPSHOT.jar"]