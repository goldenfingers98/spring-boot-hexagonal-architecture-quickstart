#! /bin/bash

echo "Starting postgres container."
docker run \
-e POSTGRES_USER=user \
-e POSTGRES_PASSWORD=user \
-e POSTGRES_DB=demo \
-p 5432:5432 \
-d \
postgres:11

echo "Postgres container started successfully."