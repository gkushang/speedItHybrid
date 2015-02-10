package com.speeditlab.hybrid.keywords;

import com.speeditlab.hybrid.browser.Browser;
import com.speeditlab.hybrid.exception.ViewNotFound;
import com.speeditlab.hybrid.locators.View;
import com.speeditlab.hybrid.testcase.Repository;
import com.speeditlab.hybrid.utils.Keys;
import com.speeditlab.hybrid.utils.SpeedItUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by kugajjar on 2/9/15.
 */
public class Keywords implements Kw
{
    private static final Logger LOG = LoggerFactory.getLogger(Keywords.class);

    private final Browser browser;

    public Keywords(Browser browser)
    {
        this.browser = browser;
    }

    public String process(Repository repository, String fieldName, String value) throws ViewNotFound
    {
        if (fieldName.equals(NAVIGATE))
        {
            browser.navigate(value);
            SpeedItUtils.sleep(5000);
        }
        else
        {
            View view = repository.getView(fieldName);

            if (isClick(value))
            {
                LOG.info("Click '{}' element", view.toString());
                browser.click(view);
            }
            else if (isVerify(value))
            {
                LOG.info("Verify the text for '{}' element", view.toString());

                value = removeKeyword(value);

                if (isContains(value))
                {
                    LOG.info("Verify the in-string text for '{}' element", view.toString());
                    assertThat("Expected text did not contain actual value", browser.getVisibleText(view),
                               containsString(removeKeyword(value)));
                }
                else
                {
                    assertThat("Expected text did not match", browser.getVisibleText(view),
                               is(removeKeyword(value)));
                }

                return Keys.Result.PASS;
            }
            else
            {
                if (browser.getTag(view).equals(Browser.SELECT))
                {
                    LOG.info("Select a value '{}' element", view.toString());
                    browser.selectByText(view, value);
                }
                else if (browser.getType(view).equals(Browser.CHECK_BOX))
                {
                    LOG.info("Checkbox '{}' element", view.toString());

                    if (value.equalsIgnoreCase(Kw.ON))
                    {
                        browser.activateCheckBox(view);

                    }
                    else if (value.equalsIgnoreCase(Kw.OFF))
                    {
                        browser.deactivateCheckBox(view);
                    }

                }

                else
                {
                    LOG.info("Type on '{}' element", view.toString());
                    browser.clearAndType(view, value);
                }
            }
        }

        return StringUtils.EMPTY;
    }

    private boolean isOff(String value)
    {
        return value.equals(OFF);
    }

    private boolean isOn(String value)
    {
        return value.equals(ON);
    }

    private boolean isClick(String value)
    {
        return value.equals(CLICK);
    }

    private boolean isVerify(String value)
    {
        return value.substring(0, 1).equals(VERIFY_BEGINS) &&
                value.substring(value.length() - 1, value.length()).equals(VERIFY_ENDS);
    }

    private boolean isContains(String value)
    {
        return value.substring(0, 1).equals(CONTAINS) &&
                value.substring(value.length() - 1, value.length()).equals(CONTAINS);
    }

    private String removeKeyword(String value)
    {
        if (isVerify(value))
        {
            return value.substring(1, value.length() - 1);
        }
        else if (isContains(value))
        {
            return value.substring(1, value.length() - 1);
        }

        return value;
    }
}
