#!/bin/bash
# Starts all the services passed as arguments
docker-compose --project-name na -f docker-compose.yml -f docker-compose.dev.yml up --no-start --build $@
docker stack deploy --compose-file docker-compose.yml --compose-file docker-compose.dev.yml --with-registry-auth na-common
docker service scale na-common_na-pma=0