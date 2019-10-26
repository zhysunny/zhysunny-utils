package com.zhysunny.io.excel;

import com.zhysunny.io.BaseWriter;
import com.zhysunny.io.excel.writer.MapToExcel;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * Excel输出类
 * @author 章云
 * @date 2019/7/25 18:10
 */
public class ExcelWriter extends BaseWriter {

    public ExcelWriter(File file) throws Exception {
        super(file);
        if (!file.getName().endsWith(".xls")) {
            throw new Exception("目前输出文件只支持xls");
        }
    }

    public ExcelWriter(String path) throws Exception {
        super(path);
        if (!file.getName().endsWith(".xls")) {
            throw new Exception("目前输出文件只支持xls");
        }
    }

    /**
     * 写入多个shell，数据集List<Map<String, String>>占用内存较大
     * @param dataMap
     * @throws Exception
     */
    public void writeMap(Map<String, List<Map<String, String>>> dataMap) throws Exception {
        new MapToExcel(this).write(dataMap, file);
    }

    /**
     * 写入一个shell，数据集List<Map<String, String>>占用内存较大
     * @param dataList
     * @throws Exception
     */
    public void writeMap(List<Map<String, String>> dataList) throws Exception {
        Map<String, List<Map<String, String>>> dataMap = new LinkedHashMap<String, List<Map<String, String>>>(1);
        dataMap.put("Sheet1", dataList);
        writeMap(dataMap);
    }

    /**
     * 写入多个shell，数据集Map<String, List<String>>占用内存较小
     * @param dataMap
     * @throws Exception
     */
    public void writeLessMap(Map<String, Map<String, List<String>>> dataMap) throws Exception {
        new MapToExcel(this).writeLess(dataMap, file);
    }

    /**
     * 写入一个shell，数据集Map<String, List<String>>占用内存较小
     * @param childMap
     * @throws Exception
     */
    public void writeLessMapOne(Map<String, List<String>> childMap) throws Exception {
        Map<String, Map<String, List<String>>> dataMap = new LinkedHashMap<String, Map<String, List<String>>>(1);
        dataMap.put("Sheet1", childMap);
        writeLessMap(dataMap);
    }


    /**
     * 标题样式
     * @param wb
     * @return
     */
    public HSSFCellStyle getTitleStyle(HSSFWorkbook wb) {
        HSSFCellStyle style = wb.createCellStyle();
        getCommonStyle(style);
        // 字体加粗
        HSSFFont font = wb.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    /**
     * 正文样式
     * @param wb
     * @return
     */
    public HSSFCellStyle getTextStyle(HSSFWorkbook wb) {
        HSSFCellStyle style = wb.createCellStyle();
        getCommonStyle(style);
        return style;
    }

    /**
     * 公共样式
     * @param style
     */
    private void getCommonStyle(HSSFCellStyle style) {
        // 左右上下居中
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 边框
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        // 自动换行
        style.setWrapText(true);
    }

}
