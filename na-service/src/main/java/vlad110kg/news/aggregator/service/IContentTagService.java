package vlad110kg.news.aggregator.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import vlad110kg.news.aggregator.entity.Source;
import vlad110kg.news.aggregator.entity.SourcePageContentTag;

public interface IContentTagService {

    List<SourcePageContentTag> findAll();

    List<SourcePageContentTag> findByIds(Collection<Long> ids);

    List<SourcePageContentTag> findBySource(Source source);

    Optional<SourcePageContentTag> findById(Long id);

    SourcePageContentTag save(SourcePageContentTag tag);

    List<SourcePageContentTag> saveAll(List<SourcePageContentTag> tags);

    void delete(Long id);
}
