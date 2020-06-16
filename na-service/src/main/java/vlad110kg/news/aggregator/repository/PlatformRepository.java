package vlad110kg.news.aggregator.repository;

import vlad110kg.news.aggregator.entity.Platform;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, String> {
}
