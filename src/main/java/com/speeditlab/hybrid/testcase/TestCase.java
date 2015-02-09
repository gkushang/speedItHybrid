package com.speeditlab.hybrid.testcase;

import com.speeditlab.hybrid.utils.Keys;
import com.speeditlab.hybrid.excel.Excel;
import com.speeditlab.hybrid.exception.EndOfTestCase;


/**
 * Created by kugajjar on 2/8/15.
 */
public class TestCase extends Excel
{
    public TestCase(String workbook, String worksheet)
    {
        super(workbook, worksheet);
    }

    public int getStartTestRow() throws EndOfTestCase
    {
        int row = Keys.TestCase.Rows.START_ROW;

        while (true)
        {
            if (_isEndTestFound(row))
            {
                throw new EndOfTestCase("Test Case End");
            }
            else if (_isStartTestFound(row))
            {
                return ++row;
            }

            row++;
        }
    }

    private boolean _isEndTestFound(int row)
    {
        return _getKeyword(row).equals(Keys.TestCase.END_TEST);
    }

    private boolean _isStartTestFound(int row)
    {
        return _getKeyword(row).equals(Keys.TestCase.START_TEST);
    }

    private String _getKeyword(Integer row)
    {
        return getCellData(row, Keys.TestCase.Columns.KEYWORD);
    }
}
