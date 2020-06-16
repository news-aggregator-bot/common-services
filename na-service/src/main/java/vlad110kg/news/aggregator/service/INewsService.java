package vlad110kg.news.aggregator.service;

import java.util.List;

import vlad110kg.news.aggregator.domain.NewsSyncResult;

public interface INewsService {

    NewsSyncResult sync(String name);

    List<NewsSyncResult> syncAll();
}
