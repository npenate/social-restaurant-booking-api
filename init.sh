#!/usr/bin/env bash

set -e

ENVIRONMENT_NAME="${SPRING_PROFILES_ACTIVE:-"development"}"
JVM_OPS="${JVM_OPS:-""}"

COMMAND=${1:-"web"}
echo $COMMAND

case "$COMMAND" in
  migrate|web)
    # NOTE: NewRelic won't work unless -javaagent is the first argument
    exec java ${JVM_OPS} \
      -Dspring.profiles.active=${ENVIRONMENT_NAME} \
      -jar /app/socialrestaurant-*.jar \
      $COMMAND
    ;;
  *)
    exec sh -c "$*"
    ;;
esac