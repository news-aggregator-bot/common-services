package vlad110kg.data.ingestor.service;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlad110kg.data.ingestor.client.ServiceClient;
import vlad110kg.data.ingestor.domain.ContentTag;
import vlad110kg.data.ingestor.domain.Source;
import vlad110kg.data.ingestor.domain.SourcePage;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;

import static vlad110kg.data.ingestor.domain.ContentTagType.AUTHOR;
import static vlad110kg.data.ingestor.domain.ContentTagType.DESCRIPTION;
import static vlad110kg.data.ingestor.domain.ContentTagType.LINK;
import static vlad110kg.data.ingestor.domain.ContentTagType.MAIN;
import static vlad110kg.data.ingestor.domain.ContentTagType.TITLE;

@Service("source")
public class SourceService implements IngestionService {

    private final Map<Integer, Supplier<Object>> entityMapping =
        ImmutableMap.<Integer, Supplier<Object>>builder()
            .put(0, SourcePage::new)
            .put(4, () -> new ContentTag(MAIN))
            .put(5, () -> new ContentTag(TITLE))
            .put(6, () -> new ContentTag(LINK))
            .put(7, () -> new ContentTag(DESCRIPTION))
            .put(8, () -> new ContentTag(AUTHOR))
            .build();

    private final Map<Integer, IngestionConsumer> fieldMapping =
        ImmutableMap.<Integer, IngestionConsumer>builder()
            .put(0, (v, o) -> ((SourcePage) o).setName(v))
            .put(1, (v, o) -> ((SourcePage) o).setUrl(v))
            .put(2, (v, o) -> ((SourcePage) o).setCategories(Arrays.asList(v.split(","))))
            .put(3, (v, o) -> ((SourcePage) o).setLanguage(v))
            .put(4, (v, o) -> ((ContentTag) o).setValue(v))
            .put(5, (v, o) -> ((ContentTag) o).setValue(v))
            .put(6, (v, o) -> ((ContentTag) o).setValue(v))
            .put(7, (v, o) -> ((ContentTag) o).setValue(v))
            .put(8, (v, o) -> ((ContentTag) o).setValue(v))
            .build();

    @Autowired
    private ServiceClient sourceServiceClient;

    @Override
    public void ingest(InputStream data) {
        try {
            Workbook wb = WorkbookFactory.create(data);
            for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {
                Source source = new Source();
                source.setName(wb.getSheetName(sheetNum));

                Sheet sheet = wb.getSheetAt(sheetNum);
                int rows = sheet.getPhysicalNumberOfRows();
                parseSourcePages(source, sheet, rows);
                sourceServiceClient.ingestSource(source);
            }

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

    private void parseSourcePages(Source source, Sheet sheet, int rows) {
        SourcePage srcPage = null;
        for (int r = 1; r < rows; r++) {
            Row row = sheet.getRow(r);
            ContentTag contentTag = null;

            for (Map.Entry<Integer, IngestionConsumer> e : fieldMapping.entrySet()) {
                Cell cell = row.getCell(e.getKey());

                String cellValue = cell.getStringCellValue();
                if (StringUtils.isNotBlank(cellValue)) {
                    Supplier<Object> entitySupplier = entityMapping.get(e.getKey());
                    if (entitySupplier != null) {
                        Object entity = entitySupplier.get();

                        if (entity instanceof SourcePage) {
                            srcPage = (SourcePage) entity;
                            source.addPage(srcPage);
                        } else {
                            contentTag = (ContentTag) entity;
                            srcPage.addTag(contentTag);
                        }
                    }

                    if (e.getKey() < 4) {
                        e.getValue().consume(cellValue, srcPage);
                    } else {
                        e.getValue().consume(cellValue, contentTag);
                    }
                }
            }
        }
    }
}
