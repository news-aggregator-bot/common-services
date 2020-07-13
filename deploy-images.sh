mvn clean package
docker build na-registry -t docker.pkg.github.com/news-aggregator-bot/artifactory/vlad110kg.na-registry:latest
docker push docker.pkg.github.com/news-aggregator-bot/artifactory/vlad110kg.na-registry:latest
