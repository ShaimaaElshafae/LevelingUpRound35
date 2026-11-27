package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExcelReader {

    private static final Logger LOGGER = Logger.getLogger(ExcelReader.class.getName());

    private ExcelReader() {
    }

    public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) {
        String cellData = "";
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);

            if (cell.getCellType() == CellType.STRING) {
                cellData = cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.NUMERIC) {
                cellData = String.valueOf((int) cell.getNumericCellValue());
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unable to read Excel cell data", e);
        }
        return cellData;
    }
}
