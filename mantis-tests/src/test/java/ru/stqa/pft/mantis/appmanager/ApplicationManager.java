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
  private WebDriver wd;
  private String browser;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private DbHelper dbHelper;
  private SessionHelper sessionHelper;
  private JamesHelper jamesHelper;
  private ChangePasswordHelper changePasswordHelper;

  public ApplicationManager(String browser) {
    properties = new Properties();
    this.browser = browser;
  }

  public WebDriver getDriver () {
    if (wd == null) {
      if (browser.equals(BrowserType.SAFARI)) {
        wd = new SafariDriver();
      } else if (browser.equals(BrowserType.OPERA_BLINK)) {
        wd = new OperaDriver();
      } else if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else {
        wd = new ChromeDriver();
      }
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

  public void stop () {
    if (wd != null){
      wd.quit();
    }
  }

  public HttpSession newSession () {
      return new HttpSession(this);
    }

  public String getProperty (String key){
      return properties.getProperty(key);
  }

  public RegistrationHelper registration () {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public FtpHelper ftp() {
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public SessionHelper session() {
    if (sessionHelper == null) {
      sessionHelper = new SessionHelper(this);
    }
    return sessionHelper;
  }

  public DbHelper db() {
    if(dbHelper == null){
      dbHelper = new DbHelper();
    }
    return dbHelper;
  }

  public ChangePasswordHelper changepass() {
    if (changePasswordHelper == null) {
      changePasswordHelper = new ChangePasswordHelper(this);
    }
    return changePasswordHelper;
  }

  public MailHelper mail(){
    if(mailHelper == null){
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public JamesHelper james() {
    if (jamesHelper == null) {
      jamesHelper = new JamesHelper(this);
    }
    return jamesHelper;
  }

}
