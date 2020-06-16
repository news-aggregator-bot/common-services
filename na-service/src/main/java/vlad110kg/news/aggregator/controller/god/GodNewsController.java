package vlad110kg.news.aggregator.controller.god;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlad110kg.news.aggregator.domain.NewsSyncResult;
import vlad110kg.news.aggregator.service.INewsService;

import java.util.List;

@RestController
@RequestMapping("/god")
public class GodNewsController {

    @Autowired
    private INewsService newsService;

    @PutMapping("/news/sync/{name}")
    public NewsSyncResult sync(@PathVariable String name) {
        return newsService.sync(name);
    }

    @PutMapping("/news/sync")
    public List<NewsSyncResult> syncAll() {
        return newsService.syncAll();
    }
}
