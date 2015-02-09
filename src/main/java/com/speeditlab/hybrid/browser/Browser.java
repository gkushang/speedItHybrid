package com.speeditlab.hybrid.browser;

import com.speeditlab.hybrid.locators.View;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by kugajjar on 2/8/15.
 */
public class Browser
{
    public static final String CSS = "css";
    public static final String ID = "id";
    public static final String XPATH = "xpath";
    public static final String NAME = "name";
    public static final String TAG = "tag";

    private final WebDriver driver;

    private static final Logger LOG = LoggerFactory.getLogger(Browser.class);

    public Browser(WebDriver driver)
    {
        this.driver = driver;
    }

    public void clearAndType(View view, String textToType)
    {
        WebElement element = findElement(view);

        element.clear();
        element.sendKeys(textToType);
    }

    public void navigate(String url)
    {
        LOG.info("Navigate to '{}' url", url);
        driver.get(url);
    }


    public String getTitle()
    {
        return driver.getTitle();
    }


    public void activateCheckBox(View view)
    {
        WebElement element = findElement(view);

        if (!element.isSelected())
            click(element);
    }


    public void deactivateCheckBox(View view)
    {
        WebElement element = findElement(view);

        if (element.isSelected())
            click(element);
    }

    public void click(View view)
    {
        findElement(view).click();
    }


    public void click(WebElement element)
    {
        element.click();
    }

    public void selectByText(View view, String text)
    {
        _genericSelect(view, false, text);
    }

    public void selectByValue(View view, String value)
    {
        _genericSelect(view, true, value);
    }

    private void _genericSelect(View view,
                                boolean isSelectByValue, String valueOrText)
    {
        Select select = new Select(findElement(view));
        if (isSelectByValue)
            select.selectByValue(valueOrText);
        else
            select.selectByVisibleText(valueOrText);
    }

    public WebElement findElement(View view)
    {
        if (view.getBy().equals(CSS))
        {
            return driver.findElement(By.cssSelector(view.getselector()));
        }
        else if (view.getBy().equals(ID))
        {
            return driver.findElement(By.id(view.getselector()));
        }
        else if (view.getBy().equals(XPATH))
        {
            return driver.findElement(By.xpath(view.getselector()));
        }
        else if (view.getBy().equals(NAME))
        {
            return driver.findElement(By.name(view.getselector()));
        }
        else if (view.getBy().equals(TAG))
        {
            return driver.findElement(By.tagName(view.getselector()));
        }

        throw new NoSuchElementException("element not found: " + view.toString());
    }

    public void quit()
    {
        driver.quit();
    }

    public String getVisibleText(View view)
    {
        return findElement(view).getAttribute("value");
    }
}

