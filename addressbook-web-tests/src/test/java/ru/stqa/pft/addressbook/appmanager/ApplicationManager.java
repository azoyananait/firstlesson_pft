package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;


import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class ApplicationManager{
  WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private RegistrationHelper registrationHelper;
  private ContactModificationHelper contactModificationHelper;
  private String browser;

  public ApplicationManager(String browser) {

    this.browser = browser;
  }

  public void init() {
      if (browser == CHROME){
      wd = new ChromeDriver();
    }else{
      if (browser == BrowserType.SAFARI){
        wd = new SafariDriver();
      }
    }
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/index.php");

    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
    registrationHelper = new RegistrationHelper(wd);
    contactModificationHelper = new ContactModificationHelper(wd);
  }

  public void stop () {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public RegistrationHelper getRegistrationHelper() {
    return registrationHelper;
  }

  public ContactModificationHelper getContactModificationHelper() {
    return contactModificationHelper;
  }
}
