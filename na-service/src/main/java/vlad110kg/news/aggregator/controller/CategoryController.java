package vlad110kg.news.aggregator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vlad110kg.news.aggregator.domain.dto.response.ListCategoryResponse;
import vlad110kg.news.aggregator.facade.CategoryFacade;

import javax.websocket.server.PathParam;

@RestController
public class CategoryController {

    @Autowired
    private CategoryFacade categoryFacade;

    @GetMapping("/category/list")
    public ListCategoryResponse findAll(
        @PathParam("chatId") long chatId,
        @PathParam("page") int page,
        @PathParam("size") int size
    ) {
        return categoryFacade.listAll(chatId, page, size);
    }
}
