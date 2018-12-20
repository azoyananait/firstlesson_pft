package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class HelperBase {
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
    this.wd.manage().window().maximize();
  }

  protected void click(By locator) {
    WebElement element = wd.findElement(locator);
    Actions actions = new Actions(wd);
    actions.moveToElement(element).click().perform();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingTest = wd.findElement(locator).getAttribute("value");
      if (! text.equals(existingTest)){
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }
  public boolean isAlertPresent () {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e){
      return false;
    }
  }

  protected boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
