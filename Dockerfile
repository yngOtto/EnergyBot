FROM node:14.15.0 as node
RUN npm install -g npm@latest

FROM gradle:6.9-jdk11 as builder
RUN mkdir -p app
COPY . /app
WORKDIR /app


EXPOSE 8080

RUN gradle build
CMD ["gradle", "bootRun"]

