package com.speeditlab.hybrid.testcase;

import com.speeditlab.hybrid.enums.Keys;
import com.speeditlab.hybrid.excel.Excel;


/**
 * Created by kugajjar on 2/8/15.
 */
public class TestCase extends Excel
{
    public TestCase(String workbook, String worksheet)
    {
        super(workbook, worksheet);
    }

    public int getStartTestRow()
    {
        int row = Keys.TestCase.Rows.START_ROW;

        while (true)
        {
            if (_isEndTestFound(row))
            {
                return 0;
            }
            else if (_isStartTestFound(row))
            {
                return row;
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
