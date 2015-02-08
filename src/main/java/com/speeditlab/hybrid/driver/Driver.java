package com.speeditlab.hybrid.driver;

import java.io.IOException;

import com.speeditlab.hybrid.enums.Keys;
import com.speeditlab.hybrid.excel.Excel;


/**
 * Created by kugajjar on 2/6/15.
 */
public class Driver
{
    public void execute(String workBook, String workSheet) throws IOException
    {
        Excel tc = new Excel(workBook, workSheet);
        Integer row = Keys.TestCase.Rows.START_ROW;

        while (true)
        {
            while (!getKeyword(tc, row).equals(Keys.TestCase.END_TEST))
            {
                if (getKeyword(tc, row).equals(Keys.TestCase.START_TEST))
                {
                    break;
                }
            }

            if (getKeyword(tc, row).equals("START TEST"))
            {
                row = getKeyword(tc, row);
                row = getFieldNameAndValue(tc, row);
            }
            else if (tc.getCellData(row, Keys.TestCase.Columns.KEYWORD).equals("END TEST"))
            {
                return;
            }

            row++;
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
