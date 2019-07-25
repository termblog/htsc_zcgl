package com.htsc.zcgl.util;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by K0180006 on 2017/7/11.
 * POI 导出相关的工具类
 */
public class PoiUtil {

    /**
     * 设置Excel表格Cell 前背景色
     * @param book
     * @param i
     * @return
     */
    public static CellStyle setFillForegroundColor(XSSFWorkbook book, short i){
        CellStyle cellStyle = book.createCellStyle();
        cellStyle.setFillForegroundColor(i);
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        XSSFFont font = book.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontHeight(10);
        cellStyle.setFont(font);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        return cellStyle;
    }

    /**
     * 设置边框
     * @param cellStyle
     * @return
     */
    public static CellStyle setBorder(CellStyle cellStyle){
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
        return cellStyle;
    }


}
