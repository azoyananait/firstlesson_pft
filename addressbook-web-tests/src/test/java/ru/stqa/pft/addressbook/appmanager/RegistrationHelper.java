package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.RegisterData;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper (WebDriver wd) {
    super(wd);
  }

  public void submit() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillRegisterForm(RegisterData registerData, boolean creation) {
    fill(By.name("firstname"), registerData.getName());
    fill(By.name("middlename"), registerData.getMiddle());
    fill(By.name("lastname"), registerData.getLast());
    fill(By.name("nickname"), registerData.getNick());
    fill(By.name("title"), registerData.getTitle());
    fill(By.name("company"), registerData.getCompany());
    fill(By.name("address"), registerData.getAddress());
    fill(By.name("home"), registerData.getHome());
    fill(By.name("mobile"), registerData.getMobile());
    fill(By.name("work"), registerData.getWork());
    fill(By.name("fax"), registerData.getFax());
    fill(By.name("email"), registerData.getEmail());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(registerData.getGroup());
    }else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  private void fill(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

}
