#!/bin/sh
set -e

echo "Loading secrets"
# Auto-load Docker secrets into environment variables
for file in /run/secrets/*; do
  if [ -f "$file" ]; then
    var=$(basename "$file" | tr '[:lower:]' '[:upper:]')
    export "$var"="$(cat "$file")"
    echo "Loading secret variable $var"
  fi
done

exec java -jar /app/app.jar
