package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase {
  private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    super(app);
    wd = app.getDriver();
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"),username);
    type(By.name("email"),email);
    click(By.cssSelector("input[value='Зарегистрироваться']"));
    }

  public void finish(String confrmationLink, String password) {
    wd.get(confrmationLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("button[type='submit']"));
  }
}
