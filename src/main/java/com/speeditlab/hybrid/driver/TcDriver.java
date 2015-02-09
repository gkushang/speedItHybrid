package com.speeditlab.hybrid.driver;

import com.speeditlab.hybrid.browser.Browser;
import com.speeditlab.hybrid.browser.WebDriverFactory;
import com.speeditlab.hybrid.exception.EndOfTestCase;
import com.speeditlab.hybrid.exception.SpeedItException;
import com.speeditlab.hybrid.exception.ViewNotFound;
import com.speeditlab.hybrid.keywords.Keywords;
import com.speeditlab.hybrid.testcase.Repository;
import com.speeditlab.hybrid.testcase.TestCase;
import com.speeditlab.hybrid.utils.Keys;
import org.apache.commons.lang3.StringUtils;
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

        Browser browser = _launchBrowser(System.getProperty(Keys.BROWSER));

        Keywords keywords = new Keywords(browser);

        browser.navigate("https://www.paypal.com/signup/account");

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

                        keywords.process(repository.getView(fieldName), tc.getFieldValue(row));
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
            throw new SpeedItException(e);
        }
        finally
        {
            LOG.info("Quit Browser");

            browser.quit();
        }

        LOG.info("Tests ends. SpeedIt AGAIN...!");

    }

    private Browser _launchBrowser(String browser)
    {
        LOG.info("Launching '{}' browser", browser);

        final WebDriverFactory factory = WebDriverFactory.getDriverFromString(browser);
        return new Browser(factory.getLocalDriver());
    }

}
