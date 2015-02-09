package com.speeditlab.hybrid.lib;

import com.speeditlab.hybrid.locators.View;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Created by kugajjar on 2/8/15.
 */
public class Browser
{
    private final WebDriver driver;

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

    public WebElement findElement(View view)
    {
        if (view.getBy().equals("css"))
        {
            return driver.findElement(By.cssSelector(view.getselector()));
        }

        throw new NoSuchElementException("element not found: " + view.toString());
    }
}

