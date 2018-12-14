package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;


public class SessionHelper  extends HelperBase {
  public SessionHelper(ApplicationManager app) {

    super(app);
  }

  public void loginToMantis() {
    String username = app.getProperty("web.adminLogin");
    String password = app.getProperty("web.adminPassword");
    wd.get(app.getProperty("web.baseUrl"));
    type(By.name("username"), username);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));

  }

  public void goToUsersPage() {
    wd.get(app.getProperty("web.userURL"));
  }

  public void finishRestor(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type='submit']"));
  }
}

