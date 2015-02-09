package com.speeditlab.hybrid.driver;

import com.speeditlab.hybrid.exception.EndOfTestCase;
import com.speeditlab.hybrid.exception.ViewNotFound;
import com.speeditlab.hybrid.browser.Browser;
import com.speeditlab.hybrid.testcase.Repository;
import com.speeditlab.hybrid.testcase.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by kugajjar on 2/6/15.
 */
public class TcDriver
{

    private static final Logger LOG = LoggerFactory.getLogger(TcDriver.class);

    private Repository repository;

    public void execute(String workBook, String workSheet) throws ViewNotFound
    {
        TestCase tc = new TestCase(workBook, workSheet);

        final DesiredCapabilities caps = DesiredCapabilities.firefox();
        WebDriver driver = new FirefoxDriver(caps);
        driver.navigate().to("https://accounts.google.com/SignUp");
        Browser browser = new Browser(driver);

        try
        {
            int row = tc.getStartTestRow();
            while (!tc.isEnd(row))
            {
                String keyword = tc.getKeyword(row);
                if (StringUtils.isNotEmpty(keyword))
                {
                    LOG.info("Initializing '{}' repository", keyword);
                    repository = new Repository(keyword);
                }
                else
                {
                    String fieldName = tc.getFieldName(row);
                    if (StringUtils.isNotEmpty(fieldName))
                    {
                        LOG.info("Executing '{}' field", fieldName);
                        browser.clearAndType(repository.getSelector(fieldName), tc.getFieldValue(row));
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
