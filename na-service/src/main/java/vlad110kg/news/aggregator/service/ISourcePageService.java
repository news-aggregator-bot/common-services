package vlad110kg.news.aggregator.service;

import vlad110kg.news.aggregator.entity.Source;
import vlad110kg.news.aggregator.entity.SourcePage;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ISourcePageService {

    List<SourcePage> findAll();

    List<SourcePage> findBySource(Source source);

    Optional<SourcePage> findByUrl(String url);

    SourcePage save(SourcePage page);

    Collection<SourcePage> save(Collection<SourcePage> pages);

    void delete(long id);
}
