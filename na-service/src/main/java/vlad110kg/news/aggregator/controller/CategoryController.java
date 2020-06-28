package vlad110kg.news.aggregator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vlad110kg.news.aggregator.domain.dto.request.ListCategoryRequest;
import vlad110kg.news.aggregator.domain.dto.response.ListCategoryResponse;
import vlad110kg.news.aggregator.facade.CategoryFacade;

import java.util.Map;

@RestController
public class CategoryController {

    @Autowired
    private CategoryFacade categoryFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/category/list")
    public ListCategoryResponse findAll(@RequestParam Map<String, Object> params) {
        ListCategoryRequest request = objectMapper.convertValue(params, ListCategoryRequest.class);
        return request.getParentId() == 0 ? categoryFacade.listAll(request) : categoryFacade.listSub(request);
    }
}
