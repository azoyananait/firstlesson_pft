package ru.stqa.pft.addressbook.appmanager;

import jdk.internal.instrumentation.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactModificationHelper extends HelperBase {

  public ContactModificationHelper(WebDriver wd) {
    super(wd);
  }

  public void initContactModification() {
    click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void initContactSelect() {
    click(By.xpath("//input[@id='3']"));
  }

  public void initContactDelete() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void checkAlert() {
    Alert alert = wd.switchTo().alert();
    alert.accept();
  }
}
