package com.speeditlab.hybrid.browser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


public enum WebDriverFactory
{


    FIREFOX("firefox")
            {
                @Override
                public WebDriver getLocalDriver()
                {
                    FirefoxProfile profile = new FirefoxProfile();
                    final DesiredCapabilities caps = DesiredCapabilities.firefox();
                    caps.setCapability(FirefoxDriver.PROFILE, profile);
                    return new FirefoxDriver(caps);
                }

                @Override
                public WebDriver getRemoteDriver(String hub)
                        throws MalformedURLException
                {
                    final FirefoxProfile profile = new FirefoxProfile();
                    profile.setEnableNativeEvents(false);
                    profile.setAlwaysLoadNoFocusLib(true);
                    final DesiredCapabilities caps = DesiredCapabilities.firefox();
                    caps.setCapability(FirefoxDriver.PROFILE, profile);
                    caps.setCapability("idle-timeout", 1000);
                    return getDriverWithCapabilities(new URL(hub), caps);
                }

            },


    CHROME("chrome")
            {
                @Override
                public WebDriver getLocalDriver()
                {
                    System.setProperty("webdriver.chrome.driver",
                                       System.getProperty("user.home") + "/chromedriver");
                    final WebDriver chrome = new ChromeDriver();
                    return chrome;
                }

                @Override
                public WebDriver getRemoteDriver(String hub)
                        throws MalformedURLException
                {
                    System.setProperty("webdriver.chrome.driver",
                                       System.getProperty("user.home") + "/chromedriver");
                    return getDriverWithCapabilities(new URL(hub),
                                                     DesiredCapabilities.chrome());
                }

            },


    SAFARI("safari")
            {
                @Override
                public WebDriver getLocalDriver()
                {
                    return new SafariDriver();
                }

                @Override
                public WebDriver getRemoteDriver(String hub)
                        throws MalformedURLException
                {
                    return getDriverWithCapabilities(new URL(hub),
                                                     DesiredCapabilities.safari());
                }

            },

    /**
     * The IE.
     */
    IE("internetExplorer")
            {
                @Override
                public WebDriver getLocalDriver()
                {
                    return new InternetExplorerDriver();
                }

                @Override
                public WebDriver getRemoteDriver(String hub)
                        throws MalformedURLException
                {
                    return getDriverWithCapabilities(new URL(hub),
                                                     DesiredCapabilities.internetExplorer());
                }

            };

    DesiredCapabilities capability;


    private static final Map<String, WebDriverFactory> stringMap = new HashMap<String, WebDriverFactory>();

    private String name;

    static
    {
        for (final WebDriverFactory fram : values())
        {
            stringMap.put(fram.toString(), fram);
        }
    }

    private WebDriverFactory(String name)
    {
        this.name = name;
    }

    private static WebDriver getDriverWithCapabilities(URL hub,
                                                       DesiredCapabilities capabilities)
    {
        final RemoteWebDriver driver = new ScreenShotRemoteDriver(hub,
                                                                  capabilities);
        driver.setFileDetector(new LocalFileDetector());
        return driver;
    }


    @Override
    public String toString()
    {
        return name;
    }

    public static WebDriverFactory getDriverFromString(String name)
    {
        return stringMap.get(name);
    }

    public abstract WebDriver getLocalDriver();

    public abstract WebDriver getRemoteDriver(String hub)
            throws MalformedURLException;

}

