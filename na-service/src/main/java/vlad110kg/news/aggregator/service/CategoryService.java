package vlad110kg.news.aggregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlad110kg.news.aggregator.entity.Category;
import vlad110kg.news.aggregator.entity.CategoryLocalisation;
import vlad110kg.news.aggregator.repository.CategoryLocalisationRepository;
import vlad110kg.news.aggregator.repository.CategoryRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryLocalisationRepository localisationRepository;

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public List<Category> saveAll(Collection<Category> categories) {
        return repository.saveAll(categories);
    }

    @Override
    public Optional<Category> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void delete(long id) {
        find(id).ifPresent(c -> repository.deleteById(id));
    }

    @Override
    public List<CategoryLocalisation> saveAllLocalisations(Collection<CategoryLocalisation> categories) {
        return localisationRepository.saveAll(categories);
    }

    @Override
    public Optional<CategoryLocalisation> findLocalisationByValue(String value) {
        return localisationRepository.findByValue(value);
    }
}
