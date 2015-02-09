package com.speeditlab.hybrid.utils;

import java.io.FileInputStream;

import com.speeditlab.hybrid.exception.SpeedItIOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * Created by kugajjar on 2/6/15.
 */


public class Excel
{
    protected XSSFWorkbook workbook;
    protected XSSFSheet worksheet;

    public Excel(String workbook, String worksheet)
    {
        this.workbook = getWorkBook(workbook);
        this.worksheet = this.workbook.getSheet(worksheet);
    }

    private XSSFWorkbook getWorkBook(String workbook)
    {
        try
        {
            return new XSSFWorkbook(new FileInputStream(workbook));

        }
        catch (Exception e)
        {
            throw new SpeedItIOException("Error in opening workbook", e);
        }
    }

    public String getCellData(int row, int col)
    {
        XSSFCell cell = getCell(row, col);

        if (cell != null)
        {
            return cell.getStringCellValue();
        }

        return StringUtils.EMPTY;
    }

    private XSSFCell getCell(int row, int col)
    {
        return this.worksheet.getRow((short) row).getCell(col);
    }
}
