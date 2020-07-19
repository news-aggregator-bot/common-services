wget -q $1 -O docker-stack-registry.yml
docker stack ls | grep -q na-common
if [ $? -eq 0 ]; then
    echo "Updating na-registry"
    docker service update --image docker.pkg.github.com/news-aggregator-bot/artifactory/vlad110kg.na-registry --with-registry-auth na-common_na-registry
else
    echo "Deploying new na-common stack"
    docker stack deploy --compose-file docker-stack-service.yml --with-registry-auth na-common
fi