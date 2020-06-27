package vlad110kg.news.aggregator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vlad110kg.news.aggregator.domain.dto.request.ListCategoryRequest;
import vlad110kg.news.aggregator.domain.dto.response.ListCategoryResponse;
import vlad110kg.news.aggregator.facade.CategoryFacade;

@RestController
public class CategoryController {

    @Autowired
    private CategoryFacade categoryFacade;

    @GetMapping("/category/list")
    public ListCategoryResponse findAll(ListCategoryRequest request) {
        return request.getParentId() == 0 ? categoryFacade.listAll(request) : categoryFacade.listSub(request);
    }
}
