package vlad110kg.news.aggregator.service;

import java.util.List;
import java.util.Optional;

import vlad110kg.news.aggregator.entity.Source;

public interface ISourceService {

    Source create(String name);

    Source save(Source name);

    List<Source> findAll();

    Optional<Source> find(Long id);

    Optional<Source> findByName(String name);
}
