#!/usr/bin/env sh

docker run --rm -v "./:/spec" redocly/cli:2.12.6 bundle api/src/openapi.yaml -o build/openApiCheck/openapi.yaml
diff -u api/dist/openapi.yaml build/openApiCheck/openapi.yaml