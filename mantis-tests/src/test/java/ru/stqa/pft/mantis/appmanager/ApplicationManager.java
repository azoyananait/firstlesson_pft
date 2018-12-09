package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;


  private String browser;


  public ApplicationManager(String browser) {
    properties = new Properties();
    this.browser = browser;
  }


  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));



    if(browser.equals(BrowserType.SAFARI)){
      wd = new SafariDriver();
    }else if(browser.equals(BrowserType.OPERA_BLINK)){
      wd = new OperaDriver();
    }else if(browser.equals(BrowserType.FIREFOX)){
      wd = new FirefoxDriver();
    }
    else{
      wd = new ChromeDriver();
    }

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));


  }

  public void stop () { wd.quit(); }
  }
