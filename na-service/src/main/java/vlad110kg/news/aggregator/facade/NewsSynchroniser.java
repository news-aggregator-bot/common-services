package vlad110kg.news.aggregator.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vlad110kg.news.aggregator.domain.NewsSyncResult;
import vlad110kg.news.aggregator.entity.SourcePage;
import vlad110kg.news.aggregator.service.INewsService;
import vlad110kg.news.aggregator.service.IReaderService;
import vlad110kg.news.aggregator.service.ISourcePageService;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class NewsSynchroniser {

    @Autowired
    private INewsService newsService;

    @Autowired
    private ISourcePageService sourcePageService;

    @Autowired
    private IReaderService readerService;

    private final AtomicInteger sourcePage = new AtomicInteger();

    @Transactional
    @Scheduled(cron = "*/20 * * * * *")
    public void sync() {
        long sourcePageAmount = sourcePageService.countAll();
        if (sourcePage.get() == sourcePageAmount) {
            sourcePage.set(0);
        }

        PageRequest singleElementRequest = PageRequest.of(sourcePage.getAndIncrement(), 1);
        SourcePage sourcePage = sourcePageService.findFirst(singleElementRequest).orElse(null);
        if (sourcePage != null) {
            log.info("synchronisation:started:{}", sourcePage.getUrl());

            NewsSyncResult freshNotes = newsService.sync(sourcePage);
            log.info("synchronisation:collected:{}:{}", sourcePage.getUrl(), freshNotes.getNewsNotes());

            sourcePage.getReaders().forEach(r -> r.addQueueNewsNote(freshNotes.getNewsNotes()));
            readerService.save(sourcePage.getReaders());
            log.info("synchronisation:ended:{}", sourcePage.getUrl());
        }
    }

}
