import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipParsing {
    private ClassLoader cl = ZipParsing.class.getClassLoader();
    @Test
    public void zipTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("download.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".xlsx")) {
                    XLS xlsx = new XLS(zis);
                    String value = xlsx.excel.getSheetAt(0).getRow(1).getCell(3).getStringCellValue();
                    Assertions.assertTrue(value.contains("Лекция"));
                }
            }
        }
    }
}
