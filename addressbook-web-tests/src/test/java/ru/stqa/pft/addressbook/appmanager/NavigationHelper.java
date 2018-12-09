package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void goToGroupPage() {
    click(By.linkText("groups"));
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void gotoAddNewPage() {
    click(By.linkText("add new"));
  }

  public void goToHomePage(
  ) {
    click(By.linkText("home"));
  }

  public void returnToMainPage() {
    click(By.linkText("home"));
    click(By.linkText("Logout"));
  }
  public void GroupPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("groups"));
  }
  public void selectGroupPage(int id){
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.cssSelector("a[href='./?group=" + id + "']"));
  }

}
