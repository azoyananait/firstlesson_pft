package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;

public class HelperBase {
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  protected void click(By locator) {
    try {
      wd.findElement(locator).click();
    }catch (WebDriverException e){
      JavascriptExecutor executor = (JavascriptExecutor) wd;
      executor.executeScript(locator.toString() + ".click()", wd.findElement(locator));
    }
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
