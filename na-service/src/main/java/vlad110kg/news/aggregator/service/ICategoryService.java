package vlad110kg.news.aggregator.service;

import vlad110kg.news.aggregator.entity.Category;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    Category save(Category category);

    List<Category> saveAll(Collection<Category> categories);

    Optional<Category> find(Long id);

    Optional<Category> findByName(String name);

    void delete(long id);
}
