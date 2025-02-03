import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import model.GeneralInformation;
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
    void zipTestXlxs() throws Exception {
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

    @Test
    void zipTestPDF() throws Exception {
        try (InputStream is = cl.getResourceAsStream("download.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertEquals(pdf.numberOfPages, 2);
                }

            }
        }

    }

    @Test
    void zipTestCSV() throws Exception {
        try (InputStream is = cl.getResourceAsStream("download.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> list = csvReader.readAll();
                    org.assertj.core.api.Assertions.assertThat(list).contains(
                            new String[]{"6859", "Avdotyev", "Nikolai", "Igorevich", "VMF"}
                    );
                }
            }
        }
    }

    @Test
    void TestJSONParser() throws Exception {
        try (InputStream is = cl.getResourceAsStream("Tolstoy.json")) {
            assert is != null;
            try (InputStreamReader isr = new InputStreamReader(is)) {
                ObjectMapper objectMapper = new ObjectMapper();
                GeneralInformation information;
                information = objectMapper.readValue(isr, GeneralInformation.class);
                Assertions.assertEquals("Война и мир", information.getTitle());
                Assertions.assertEquals("Лев Толстой", information.getAuthor());
                Assertions.assertEquals(1869, information.getPublication_year());
                Assertions.assertEquals(List.of(
                        "исторический роман",
                        "философский роман"), information.getGenres());
                Assertions.assertEquals("Эпос о жизни русского общества и жизни людей в годы Наполеоновских войн.", information.getSummary());


            }

        }
    }
}