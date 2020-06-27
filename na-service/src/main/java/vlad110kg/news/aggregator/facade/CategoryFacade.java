package vlad110kg.news.aggregator.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import vlad110kg.news.aggregator.domain.dto.response.CategoryResponse;
import vlad110kg.news.aggregator.domain.dto.response.ListCategoryResponse;
import vlad110kg.news.aggregator.entity.Category;
import vlad110kg.news.aggregator.entity.CategoryLocalisation;
import vlad110kg.news.aggregator.entity.Reader;
import vlad110kg.news.aggregator.exception.ResourceNotFoundException;
import vlad110kg.news.aggregator.service.ICategoryService;
import vlad110kg.news.aggregator.service.ILanguageService;
import vlad110kg.news.aggregator.service.IReaderService;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class CategoryFacade {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IReaderService readerService;

    @Autowired
    private ILanguageService languageService;

    public ListCategoryResponse listAll(long chatId, int page, int size) {
        Reader reader = readerService.find(chatId).orElse(null);
        if (reader == null) {
            return ListCategoryResponse.builder().error("Reader with chat id " + chatId + " not found").build();
        }
        PageRequest req = PageRequest.of(page - 1, size);
        try {
            List<CategoryResponse> responses = categoryService.findAll(req)
                .stream()
                .map(c -> toResponse(c, reader))
                .collect(Collectors.toList());

            return ListCategoryResponse.builder()
                .categories(responses)
                .language(reader.getPrimaryLanguage().getLang())
                .totalAmount(categoryService.countAll())
                .build();
        } catch (ResourceNotFoundException e) {
            return ListCategoryResponse.builder().error(e.getMessage()).build();
        }
    }

    private CategoryResponse toResponse(Category c, Reader reader) {
        CategoryLocalisation localisation = c.getLocalisations().stream()
            .filter(cl -> cl.getLanguage().equals(reader.getPrimaryLanguage()))
            .findFirst()
            .orElseThrow(notFoundLang(c, reader));
        return CategoryResponse.builder()
            .id(c.getId())
            .name(c.getName())
            .localised(localisation.getValue())
            .parent(toResponse(c.getParent(), reader))
            .children(c.getSubcategories().stream().map(subC -> toResponse(subC, reader)).collect(Collectors.toList()))
            .build();
    }

    private Supplier<ResourceNotFoundException> notFoundLang(Category c, Reader reader) {
        return () -> new ResourceNotFoundException("Language " + reader.getPrimaryLanguage() + " for category " + c.getName() + " not found");
    }
}
