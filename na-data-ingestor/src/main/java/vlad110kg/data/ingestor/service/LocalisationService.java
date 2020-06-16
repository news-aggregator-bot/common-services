package vlad110kg.data.ingestor.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlad110kg.data.ingestor.client.ServiceClient;
import vlad110kg.data.ingestor.domain.CategoryLocalisation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("localisation")
public class LocalisationService implements IngestionService {

    @Autowired
    private ServiceClient serviceClient;

    @Override
    public void ingest(InputStream data) {
        try {
            Workbook wb = WorkbookFactory.create(data);
            Sheet sheet = wb.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            Row initial = sheet.getRow(0);
            int cellNum = 1;

            Map<Integer, String> languages = new HashMap<>();
            while (true) {
                Cell cell = initial.getCell(cellNum);
                String cellValue = cell.getStringCellValue();
                if (StringUtils.isNotBlank(cellValue)) {
                    languages.put(cellNum, cellValue);
                    cellNum++;
                } else {
                    break;
                }
            }

            List<CategoryLocalisation> localisations = new ArrayList<>();
            for (int r = 1; r < rows; r++) {
                Row row = sheet.getRow(r);
                CategoryLocalisation categoryLocalisation = new CategoryLocalisation();

                for (int c = 0; c < cellNum; c++) {
                    String cellValue = row.getCell(c).getStringCellValue();
                    if (StringUtils.isNotBlank(cellValue)) {
                        String lang = languages.get(c);
                        if (StringUtils.isBlank(lang)) {
                            categoryLocalisation.setCategory(cellValue);
                        } else {
                            categoryLocalisation.setValue(cellValue);
                            categoryLocalisation.setLanguage(lang);
                        }
                    }
                }
                localisations.add(categoryLocalisation);
            }
            serviceClient.ingestLocalisations(localisations);
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }
}
