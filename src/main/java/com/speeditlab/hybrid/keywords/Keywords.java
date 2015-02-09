package com.speeditlab.hybrid.keywords;

import com.speeditlab.hybrid.browser.Browser;
import com.speeditlab.hybrid.locators.View;
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

    public void process(View view, String value)
    {
        if (isClick(value))
        {
            LOG.info("Click '{}' element", view.toString());
            browser.click(view);
        }
        else if (isOn(value))
        {
            LOG.info("Check ON the box '{}' element", view.toString());
            browser.activateCheckBox(view);
        }
        else if (isOff(value))
        {
            LOG.info("Check OFF the box '{}' element", view.toString());
            browser.deactivateCheckBox(view);
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
        }
        else
        {
            LOG.info("Type on '{}' element", view.toString());
            browser.clearAndType(view, value);
        }
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
