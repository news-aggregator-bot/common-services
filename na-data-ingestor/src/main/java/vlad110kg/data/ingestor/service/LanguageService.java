package vlad110kg.data.ingestor.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlad110kg.data.ingestor.client.ServiceClient;
import vlad110kg.data.ingestor.domain.Language;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageService implements IngestionService {

    @Autowired
    private ServiceClient serviceClient;

    @Override
    public void ingest(InputStream data) {
        try {
            Workbook wb = WorkbookFactory.create(data);
            Sheet sheet = wb.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();

            List<Language> languages = new ArrayList<>();
            for (int r = 0; r < rows; r++) {
                Row row = sheet.getRow(r);
                languages.add(Language.builder()
                    .lang(row.getCell(0).getStringCellValue())
                    .name(row.getCell(1).getStringCellValue())
                    .localised(row.getCell(2).getStringCellValue())
                    .build());
            }
            serviceClient.ingestLanguages(languages);
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }
}
