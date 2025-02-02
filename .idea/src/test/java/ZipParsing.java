import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;


public class ZipParsing {
    private ClassLoader cl = ZipParsing.class.getClassLoader();
    @Test
    public void zipTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("download.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".xlsx")) {
                    XLS xls = new XLS(zis);

                }
            }
        }
    }
}
