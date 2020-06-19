package vlad110kg.news.aggregator.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vlad110kg.news.aggregator.domain.dto.CategoryDto;
import vlad110kg.news.aggregator.entity.Category;
import vlad110kg.news.aggregator.exception.ResourceNotFoundException;
import vlad110kg.news.aggregator.service.ICategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngestionCategoryFacade {

    @Autowired
    private ICategoryService categoryService;

    public List<Category> ingest(List<CategoryDto> dtos) {
        List<Category> categories = dtos.stream().map(c -> {
                Category cat = categoryService.findByName(c.getName()).orElseGet(() -> {
                    Category category = new Category();
                    category.setName(c.getName());
                    return category;
                });
                Category parent = categoryService.findByName(c.getParent())
                    .orElseThrow(() -> new ResourceNotFoundException(c.getParent() + " parent category must be " +
                        "created " +
                        "before setting it as a parent."));
                cat.setParent(parent);
                return cat;
            }
        ).collect(Collectors.toList());
        return categoryService.saveAll(categories);
    }
}
