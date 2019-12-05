package com.itheima.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;

public class POITest {
    //使用POI读取Excel文件中的数据
    @Test
    public void test1() throws IOException {
        XSSFWorkbook excel=new XSSFWorkbook(new FileInputStream(new File("e://poi.xlsx")));
        //读取Excel文件中第一个sheet标签页
        XSSFSheet sheet=excel.getSheetAt(0);
        //遍历sheet标签页，获得每一行数据
        for (Row row : sheet) {
            //遍历行，获得每个单元格对象
            for (Cell cell : row) {
                System.out.println(cell.getStringCellValue());
            }
        }
        //关闭资源
        excel.close();
    }

    @Test
    public void test2() throws IOException {
        XSSFWorkbook excel=new XSSFWorkbook(new FileInputStream(new File("e://poi.xlsx")));
        //读取Excel文件中第一个sheet标签页
        XSSFSheet sheet=excel.getSheetAt(0);
        //获得最后一个行号，行号从0开始的
        int lastRowNum = sheet.getLastRowNum();
        System.out.println(lastRowNum);
        for (int i=0;i<=lastRowNum;i++){
            //根据行号获得每一行
            XSSFRow row = sheet.getRow(i);
            //获得当前行最后一个最后一个单元格索引
            short lastCellNum = row.getLastCellNum();
            System.out.println(lastCellNum);
            for (int j=0;j<lastCellNum;j++){
                //根据单元格对象获取单元格对象
                XSSFCell cell = row.getCell(j);
                System.out.println(cell.getStringCellValue());
            }


        }        //关闭资源
        excel.close();
    }


    //使用POI向Excel文件写入数据，并且通过输出流将创建的Excel文件保存到本地磁盘
    @Test
    public void test3() throws  Exception{
        //在内存中创建一个Excel文件（工作簿）
        XSSFWorkbook excel=new XSSFWorkbook();
        //创建一个工作表对象
        XSSFSheet sheet=excel.createSheet("传智播客");
        //在工作表中创建行对象
        XSSFRow title = sheet.createRow(0);
        //在行中，创建单元格对象
        title.createCell(0).setCellValue("姓名");
        title.createCell(1).setCellValue("地址");
        title.createCell(2).setCellValue("年龄");

        XSSFRow dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue("小明");
        dataRow.createCell(1).setCellValue("北京");
        dataRow.createCell(2).setCellValue(20);

        //创建一个输出流，通过输出流将内存中的Excel文件写到磁盘
        FileOutputStream fileOutputStream = new FileOutputStream(new File("e://hello.xlsx"));
        excel.write(fileOutputStream);

        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
