package com.speeditlab.hybrid.driver;

import com.speeditlab.hybrid.exception.EndOfTestCase;
import com.speeditlab.hybrid.exception.ViewNotFound;
import com.speeditlab.hybrid.testcase.Repository;
import com.speeditlab.hybrid.testcase.TestCase;
import org.apache.commons.lang3.StringUtils;


/**
 * Created by kugajjar on 2/6/15.
 */
public class TcDriver
{

    private Repository repository;

    public void execute(String workBook, String workSheet) throws ViewNotFound
    {
        TestCase tc = new TestCase(workBook, workSheet);

        try
        {
            int row = tc.getStartTestRow();
            while (!tc.isEnd(row))
            {
                String keyword = tc.getKeyword(row);
                if (StringUtils.isNotEmpty(keyword))
                {
                    repository = new Repository(keyword);
                }
                else
                {
                    String fieldName = tc.getFieldName(row);
                    if (StringUtils.isNotEmpty(fieldName))
                    {
                        System.out.println("VIEW: \n" + repository.getSelector(fieldName));
                    }
                }

                row++;
            }
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

}
