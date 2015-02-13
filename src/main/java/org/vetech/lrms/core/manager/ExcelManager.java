//package org.vetech.lrms.core.manager;
//
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.web.servlet.view.document.AbstractExcelView;
//import org.vetech.lrms.core.hibernate.models.Client;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.*;
//
///**
// * Created by nessy on 07/02/15.
// */
//public class ExcelManager extends AbstractExcelView {
////    public static void main(String[] args)
////    {
////        //Blank workbook
////        XSSFWorkbook workbook = new XSSFWorkbook();
////
////        //Create a blank sheet
////        XSSFSheet sheet = workbook.createSheet("Employee Data");
////
////        //This data needs to be written (Object[])
////        Map<String, Object[]> data = new TreeMap<String, Object[]>();
////        data.put("1", new Object[] {"ID", "NAME", "LASTNAME"});
////        data.put("2", new Object[] {1, "Amit", "Shukla"});
////        data.put("3", new Object[] {2, "Lokesh", "Gupta"});
////        data.put("4", new Object[] {3, "John", "Adwards"});
////        data.put("5", new Object[] {4, "Brian", "Schultz"});
////
////        //Iterate over data and write to sheet
////        Set<String> keyset = data.keySet();
////        int rownum = 0;
////        for (String key : keyset)
////        {
////            Row row = sheet.createRow(rownum++);
////            Object [] objArr = data.get(key);
////            int cellnum = 0;
////            for (Object obj : objArr)
////            {
////                Cell cell = row.createCell(cellnum++);
////                if(obj instanceof String)
////                    cell.setCellValue((String)obj);
////                else if(obj instanceof Integer)
////                    cell.setCellValue((Integer)obj);
////            }
////        }
////        try
////        {
////            //Write the workbook in file system
////            FileOutputStream out = new FileOutputStream(new File("Clients.xlsx"));
////            workbook.write(out);
////            out.close();
////            System.out.println("Clients.xlsx written successfully on disk.");
////        }
////        catch (Exception e)
////        {
////            e.printStackTrace();
////        }
////    }
////
//    @Override
//    protected void buildExcelDocument(Map<String, Object> stringObjectMap,
//       HSSFWorkbook hssfWorkbook, HttpServletRequest httpServletRequest,
//       HttpServletResponse httpServletResponse) throws Exception {
//
//
//
//
////    @Override
////    protected void buildExcelDocument(Map<String, Object> model,
////                                      Workbook workbook, HttpServletRequest request,
////                                      HttpServletResponse response) throws Exception {
//
//        Sheet sheet = hssfWorkbook.createSheet("sheet 1");
//
//        @SuppressWarnings("unchecked")
//        LinkedList<Client> articles = (LinkedList<Client>) stringObjectMap.get("articles");
//
//        Row row = null;
//        Cell cell = null;
//        int r = 0;
//        int c = 0;
//
//        //Style for header cell
//        CellStyle style = hssfWorkbook.createCellStyle();
//        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        style.setAlignment(CellStyle.ALIGN_CENTER);
//
//        //Create header cells
//        row = sheet.createRow(r++);
//
//        cell = row.createCell(c++);
//        cell.setCellStyle(style);
//        cell.setCellValue("Title");
//
//        cell = row.createCell(c++);
//        cell.setCellStyle(style);
//        cell.setCellValue("URL");
//
//        cell = row.createCell(c++);
//        cell.setCellStyle(style);
//        cell.setCellValue("Categories");
//
//        cell = row.createCell(c++);
//        cell.setCellStyle(style);
//        cell.setCellValue("Tags");
//
//        //Create data cell
//        for(Client article:articles){
//            row = sheet.createRow(r++);
//            c = 0;
//            row.createCell(c++).setCellValue(article.getClientId());
//            row.createCell(c++).setCellValue(article.getUuid());
//            row.createCell(c++).setCellValue(article.getClientTypeId());
//            row.createCell(c++).setCellValue(article.getCreatedOn());
//
//        }
//        for(int i = 0 ; i < 4; i++)
//            sheet.autoSizeColumn(i, true);
//
//    }
//
//
//
//
//
//
////        try {
////            FileOutputStream fileOut = new FileOutputStream("test.xls");
////            HSSFWorkbook workbook = new HSSFWorkbook();
////            HSSFSheet worksheet = workbook.createSheet("Worksheet");
////
////            HSSFRow row = worksheet.createRow((short) 0);
////
////            HSSFCell cellA1 = row.createCell(0);
////            cellA1.setCellValue("Hello");
////            HSSFCellStyle styleOfCell = workbook.createCellStyle();
////            styleOfCell.setFillForegroundColor(HSSFColor.AQUA.index);
////            styleOfCell.setFillPattern(HSSFCellStyle.BORDER_THIN);
////            cellA1.setCellStyle(styleOfCell);
////
////            HSSFCell cellB1 = row.createCell(1);
////            cellB1.setCellValue("World");
////            styleOfCell = workbook.createCellStyle();
////            styleOfCell.setFillForegroundColor(HSSFColor.AQUA.index);
////            styleOfCell.setFillPattern(HSSFCellStyle.BORDER_THIN);
////            cellB1.setCellStyle(styleOfCell);
////
////            HSSFCell cellC1 = row.createCell(2);
////            cellC1.setCellValue("Happy");
////            styleOfCell = workbook.createCellStyle();
////            styleOfCell.setFillForegroundColor(HSSFColor.AQUA.index);
////            styleOfCell.setFillPattern(HSSFCellStyle.BORDER_THIN);
////            cellC1.setCellStyle(styleOfCell);
////
////            HSSFCell cellD1 = row.createCell(3);
////            cellD1.setCellValue(new Date());
////            styleOfCell = workbook.createCellStyle();
////            styleOfCell.setDataFormat(HSSFDataFormat
////                    .getBuiltinFormat("m/d/yy h:mm"));
////            styleOfCell.setFillForegroundColor(HSSFColor.AQUA.index);
////            styleOfCell.setFillPattern(HSSFCellStyle.BORDER_THIN);
////            cellD1.setCellStyle(styleOfCell);
////
////            row = worksheet.createRow(1);
////            row.createCell(0).setCellValue(Calendar.getInstance().getTime().toString());
////            row.createCell(1).setCellValue("a string");
////            row.createCell(2).setCellValue(true);
////            row.createCell(3).setCellType(Cell.CELL_TYPE_ERROR);
////
////            workbook.write(fileOut);
////            fileOut.flush();
////            fileOut.close();
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////    }
//
//
//}
//
