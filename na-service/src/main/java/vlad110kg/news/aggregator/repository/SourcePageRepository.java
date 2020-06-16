package vlad110kg.news.aggregator.repository;

import java.util.List;

import vlad110kg.news.aggregator.entity.Source;
import vlad110kg.news.aggregator.entity.SourcePage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourcePageRepository extends JpaRepository<SourcePage, Long> {

    List<SourcePage> findAllBySource(Source source);
}
