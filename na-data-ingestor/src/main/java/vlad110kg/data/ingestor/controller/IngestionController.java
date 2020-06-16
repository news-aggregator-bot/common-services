package vlad110kg.data.ingestor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vlad110kg.data.ingestor.service.CategoryService;
import vlad110kg.data.ingestor.service.LanguageService;
import vlad110kg.data.ingestor.service.LocalisationService;
import vlad110kg.data.ingestor.service.SourceService;

import java.io.IOException;

@RestController
@RequestMapping("/ingest")
public class IngestionController {

    @Autowired
    private SourceService sourceService;

    @Autowired
    private LanguageService langService;

    @Autowired
    private LocalisationService localisationService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping(path = "/source", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void ingestSources(@RequestParam("file") MultipartFile file) throws IOException {
        sourceService.ingest(file.getInputStream());
    }

    @PostMapping(path = "/language", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void ingestLangs(@RequestParam("file") MultipartFile file) throws IOException {
        langService.ingest(file.getInputStream());
    }

    @PostMapping(path = "/localisation", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void ingestLocalisations(@RequestParam("file") MultipartFile file) throws IOException {
        localisationService.ingest(file.getInputStream());
    }

    @PostMapping(path = "/category", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void ingestCategories(@RequestParam("file") MultipartFile file) throws IOException {
        categoryService.ingest(file.getInputStream());
    }
}
