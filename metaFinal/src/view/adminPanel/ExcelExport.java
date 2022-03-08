package view.adminPanel;

import javafx.scene.control.TableView;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelExport<T> {

    public void export(TableView<T> table) {

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Sheet1");
        HSSFRow firstRow = hssfSheet.createRow(0);

        ///set titles of columns
        for (int i = 0; i < table.getColumns().size(); i++) {

            firstRow.createCell(i).setCellValue(table.getColumns().get(i).getText());

        }

        for (int row = 0; row < table.getItems().size(); row++) {

            HSSFRow hssfRow = hssfSheet.createRow(row + 1);

            for (int col = 0; col < table.getColumns().size(); col++) {

                Object celValue = table.getColumns().get(col).getCellObservableValue(row).getValue();

                try {
                    if (celValue != null && Double.parseDouble(celValue.toString()) != 0.0) {
                        hssfRow.createCell(col).setCellValue(Double.parseDouble(celValue.toString()));
                    }
                } catch (NumberFormatException e) {

                    hssfRow.createCell(col).setCellValue(celValue.toString());
                }

            }

        }

        //save excel file and close the workbook
        try {
            hssfWorkbook.write(new FileOutputStream("WorkBook.xls"));
            // hssfWorkbook.close();
        } catch (IOException e) {
        }

    }

}
