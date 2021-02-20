FROM gradle:6.3 as builder

USER root

ENV APP_DIR /app
WORKDIR $APP_DIR

COPY build.gradle.kts $APP_DIR/
COPY settings.gradle.kts $APP_DIR/


RUN gradle dependencies
COPY . $APP_DIR

RUN gradle build -x test -x detekt

# -----------------------------------------------------------------------------

FROM openjdk:12-alpine3.9

WORKDIR /app

RUN apk add tini

COPY --from=builder /app/init.sh /app
COPY --from=builder /app/build/libs/socialrestaurant-*.jar /app/

EXPOSE 8080

ENTRYPOINT ["tini", "-s", "--", "sh", "init.sh"]