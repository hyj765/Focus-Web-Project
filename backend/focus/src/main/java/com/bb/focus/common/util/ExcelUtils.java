package com.bb.focus.common.util;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;


@Component
public class ExcelUtils {
    
    //엑셀 확장자를 검사하는 함수
    public String FileNameFilter(String fileName){
        String extension = FilenameUtils.getExtension(fileName);
        if(extension.equals("xls") || extension.equals("xlsx")  || extension.equals("csv")){
            return extension;
        }
        return "invalid";
    }
    
    //엑셀 워크시트를 만드는 함수
    public Workbook CreateWorkbook(String[] headers){
        Workbook workbook = new XSSFWorkbook();
        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        cellStyle.setFont(font);
        try{

            Sheet sheet = workbook.createSheet("sheet 1");
            Row row = sheet.createRow(0);
            Cell cell = null;
            for(int i=0; i<headers.length; ++i){
                cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(cellStyle);
            }
        }catch(Exception e){
            return null;
        }
        return workbook;
    }


    public void ExtractPDF(StringBuilder Html){
        Document document = new Document(PageSize.A4);
       // PdfWriter.getInstance()


    }


}
