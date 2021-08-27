mvn clean package
docker build na-registry -t docker.pkg.github.com/news-aggregator-bot/artifactory/bepicky.na-registry:latest
docker push docker.pkg.github.com/news-aggregator-bot/artifactory/bepicky.na-registry:latest
