package vlad110kg.data.ingestor.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlad110kg.data.ingestor.client.ServiceClient;
import vlad110kg.data.ingestor.domain.Category;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service("category")
public class CategoryService implements IngestionService {

    @Autowired
    private ServiceClient serviceClient;

    @Override
    public void ingest(InputStream data) {
        try {
            Workbook wb = WorkbookFactory.create(data);
            Sheet sheet = wb.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();

            List<Category> categories = new ArrayList<>();
            for (int r = 1; r < rows; r++) {
                Row row = sheet.getRow(r);
                categories.add(Category.builder()
                    .name(row.getCell(0).getStringCellValue())
                    .parent(row.getCell(1).getStringCellValue())
                    .build());
            }
            serviceClient.ingestCategories(categories);
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }
}
