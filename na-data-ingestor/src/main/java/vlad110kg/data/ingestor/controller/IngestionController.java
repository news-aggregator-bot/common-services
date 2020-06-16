package vlad110kg.data.ingestor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vlad110kg.data.ingestor.service.SourceService;

import java.io.IOException;

@RestController
@RequestMapping("/ingest")
public class IngestionController {

    @Autowired
    private SourceService sourceService;

    @PostMapping(path = "/source", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void ingestSources(@RequestParam("file") MultipartFile file) throws IOException {
        sourceService.ingest(file.getInputStream());
    }
}
