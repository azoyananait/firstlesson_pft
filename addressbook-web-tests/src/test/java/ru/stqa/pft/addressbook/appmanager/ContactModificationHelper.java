package ru.stqa.pft.addressbook.appmanager;

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

}
