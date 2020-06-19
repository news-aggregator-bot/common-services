package vlad110kg.news.aggregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlad110kg.news.aggregator.entity.ContentTag;

import java.util.Optional;

@Repository
public interface ContentTagRepository extends JpaRepository<ContentTag, Long> {

    Optional<ContentTag> findByValue(String value);
}
