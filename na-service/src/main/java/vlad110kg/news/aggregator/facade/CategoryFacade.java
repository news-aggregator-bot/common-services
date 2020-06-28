package vlad110kg.news.aggregator.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import vlad110kg.news.aggregator.domain.dto.request.ListCategoryRequest;
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

    public ListCategoryResponse listAll(ListCategoryRequest request) {
        Reader reader = readerService.find(request.getChatId()).orElse(null);
        if (reader == null) {
            return ListCategoryResponse.builder()
                .error("Reader with chat id " + request.getChatId() + " not found")
                .build();
        }
        PageRequest req = PageRequest.of(request.getPage() - 1, request.getSize());
        return getListCategoryResponse(
            reader,
            categoryService.findTopCategories(req),
            categoryService.countTopCategories()
        );
    }

    public ListCategoryResponse listSub(ListCategoryRequest request) {
        Category parent = categoryService.find(request.getParentId()).orElse(null);
        if (parent == null) {
            return ListCategoryResponse.builder()
                .error("Parent category " + request.getParentId() + " not found")
                .build();
        }
        Reader reader = readerService.find(request.getChatId()).orElse(null);
        if (reader == null) {
            return ListCategoryResponse.builder()
                .error("Reader with chat id " + request.getChatId() + " not found")
                .build();
        }
        PageRequest req = PageRequest.of(request.getPage() - 1, request.getSize());
        return getListCategoryResponse(
            reader,
            categoryService.findByParent(parent, req),
            categoryService.countByParent(parent)
        );
    }

    private ListCategoryResponse getListCategoryResponse(Reader reader, List<Category> categories, long totalAmount) {
        try {
            List<CategoryResponse> responses = categories
                .stream()
                .map(c -> toResponse(c, reader))
                .collect(Collectors.toList());

            return ListCategoryResponse.builder()
                .categories(responses)
                .language(reader.getPrimaryLanguage().getLang())
                .totalAmount(totalAmount)
                .build();
        } catch (ResourceNotFoundException e) {
            return ListCategoryResponse.builder().error(e.getMessage()).build();
        }
    }

    private CategoryResponse toResponse(Category c, Reader reader) {
        CategoryLocalisation localisation = getLocalisation(c, reader);
        return CategoryResponse.builder()
            .id(c.getId())
            .name(c.getName())
            .localised(localisation.getValue())
            .parent(singleResponse(c.getParent(), reader))
            .children(c.getSubcategories()
                .stream()
                .map(subC -> singleResponse(subC, reader))
                .collect(Collectors.toList()))
            .build();
    }

    private CategoryResponse singleResponse(Category c, Reader reader) {
        if (c == null) {
            return null;
        }
        CategoryLocalisation localisation = getLocalisation(c, reader);
        return CategoryResponse.builder()
            .id(c.getId())
            .name(c.getName())
            .localised(localisation.getValue())
            .build();
    }

    private CategoryLocalisation getLocalisation(Category c, Reader reader) {
        return c.getLocalisations().stream()
            .filter(cl -> cl.getLanguage().equals(reader.getPrimaryLanguage()))
            .findFirst()
            .orElseThrow(notFoundLang(c, reader.getPrimaryLanguage().getLang()));
    }

    private Supplier<ResourceNotFoundException> notFoundLang(Category c, String language) {
        return () -> new ResourceNotFoundException("Language " + language + " for category " + c.getName() + " not " +
            "found");
    }
}
