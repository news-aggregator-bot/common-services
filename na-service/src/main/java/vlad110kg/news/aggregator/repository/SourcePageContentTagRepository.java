package vlad110kg.news.aggregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlad110kg.news.aggregator.entity.Source;
import vlad110kg.news.aggregator.entity.SourcePageContentTag;

import java.util.List;

@Repository
public interface SourcePageContentTagRepository extends JpaRepository<SourcePageContentTag, Long> {

    List<SourcePageContentTag> findBySource(Source source);
}
