package com.speeditlab.hybrid.driver;

import java.io.IOException;

import com.speeditlab.hybrid.excel.Excel;
import com.speeditlab.hybrid.exception.EndOfTestCase;
import com.speeditlab.hybrid.locators.Locator;
import com.speeditlab.hybrid.testcase.Repository;
import com.speeditlab.hybrid.testcase.TestCase;
import com.speeditlab.hybrid.utils.Keys;


/**
 * Created by kugajjar on 2/6/15.
 */
public class TcDriver
{
    public void execute(String workBook, String workSheet) throws IOException
    {
        TestCase tc = new TestCase(workBook, workSheet);

        try
        {
            int row = tc.getStartTestRow();
            Locator locator = new Repository("login").getLocator();
            System.out.println("JSON: " + locator.toString());

        }
        catch (EndOfTestCase endOfTestCase)
        {
            endOfTestCase.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private Integer getKeyword(Excel tc, Integer row)
    {
        row++;

        System.out.println(tc.getCellData(row, Keys.TestCase.Columns.KEYWORD));
        return row;
    }

    private Integer getFieldNameAndValue(Excel tc, Integer row)
    {
        row++;
        System.out.println(tc.getCellData(row, Keys.TestCase.Columns.FIELD_NAME));
        System.out.println(tc.getCellData(row, Keys.TestCase.Columns.VALUE));
        return row;
    }
}
