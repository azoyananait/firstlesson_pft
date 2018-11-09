package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.RegisterData;

public class ContactHelper extends NavigationHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(RegisterData registerData, boolean isRegistration) {
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

    if(isRegistration){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(registerData.getGroup());
    }else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void editContact(RegisterData registerData) {
    // проверяем если контакт существует, если нет - создаем
    // после чего нажимаем на редактирование
    createContact(registerData);
    click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a"));
  }

  public void addContact(){
    click(By.xpath("(//input[@value='Enter'])"));
  }

  public void updateContact(){
    click(By.xpath("//input[@value='Update']"));
  }

  public void deleteContact(){
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContact(RegisterData registerData){
    // проверяем если контакт существует, если нет - создаем
    // после чего выбираем контакт из таблицы
    createContact(registerData);
    click(By.xpath("//input[@type='checkbox']"));
  }

  public void checkAlert() {
    Alert alert = wd.switchTo().alert();
    alert.accept();
  }

  private void fill(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  /**
   * Создания контакта, если не существует ни одного
   * @param registerData данные для регистрации контакта
   */
  private void createContact(RegisterData registerData){
    if(!isElementPresent(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]"))) {
      gotoAddNewPage();
      fillContactForm(registerData, true);
      addContact();
      goToHomePage();
    }
  }

}
