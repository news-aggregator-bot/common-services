wget -q $1 -O docker-stack-common.yml
docker stack ls | grep -q na-common
if [ $? -eq 0 ]; then
    echo "Updating na-registry"
    docker service update --image ghcr.io/news-aggregator-bot/bepicky.na-registry:latest --with-registry-auth na-common_na-registry
else
    echo "Deploying new na-common stack"
    docker stack deploy --compose-file docker-stack-common.yml --with-registry-auth na-common
fi