FROM alpine

RUN apk add openjdk11 \
     && echo "http://dl-cdn.alpinelinux.org/alpine/edge/testing" >> /etc/apk/repositories \
echo "http://dl-cdn.alpinelinux.org/alpine/edge/main" >> /etc/apk/repositories \
apk update && apk add --no-cache firefox

VOLUME /home/server

WORKDIR /home/server

COPY ./ /home/server/

EXPOSE 8080
