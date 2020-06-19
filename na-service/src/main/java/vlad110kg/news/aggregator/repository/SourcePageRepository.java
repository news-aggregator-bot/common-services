package vlad110kg.news.aggregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlad110kg.news.aggregator.entity.Source;
import vlad110kg.news.aggregator.entity.SourcePage;

import java.util.List;
import java.util.Optional;

@Repository
public interface SourcePageRepository extends JpaRepository<SourcePage, Long> {

    List<SourcePage> findAllBySource(Source source);

    Optional<SourcePage> findByUrl(String url);
}
