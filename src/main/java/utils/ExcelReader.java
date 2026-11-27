package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) {
        String cellData = "";
        try (FileInputStream fis = new FileInputStream(new File(filePath));
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
            e.printStackTrace();
        }
        return cellData;
    }
}
