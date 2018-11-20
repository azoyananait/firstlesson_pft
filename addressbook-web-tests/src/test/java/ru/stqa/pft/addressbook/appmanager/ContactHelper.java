package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.RegisterData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends NavigationHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  /**
   * Заполнение формы
   * @param registerData данные для регистрации
   * @param isRegistration флаг регистрации/модификации
   */
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

  /**
   *
   * @param i
   */
  public void editContact(int i) {
    wd.findElements(By.name("entry")).get(i).findElement(By.xpath("td[8]")).click();
  }

  /**
   *
   */
  public void addContact(){
    click(By.xpath("(//input[@value='Enter'])"));
  }

  /**
   *
   */
  public void updateContact(){
    click(By.xpath("//input[@value='Update']"));
  }

  /**
   *
   */
  public void deleteContact(){
    click(By.xpath("//input[@value='Delete']"));
  }

  /**
   *
   * @param index
   */
  public void selectContact(int index){
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  /**
   *
   */
  public void checkAlert(){
    Alert alert = wd.switchTo().alert();
    alert.accept();
  }

  /**
   *
   * @param locator
   * @param text
   */
  private void fill(By locator, String text){
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }
  /**
   * Создания контакта, если не существует ни одного
   * @param registerData данные для регистрации контакта
   */
  public void create(RegisterData registerData){
    gotoAddNewPage();
    fillContactForm(registerData, true);
    addContact();
    goToHomePage();
  }
  public void modifyContact(int index, RegisterData modificationData) {
   editContact(index);
   fillContactForm(modificationData, false);
    updateContact();
    goToHomePage();
  }
  public void delete(int index) {
    selectContact(index);
    deleteContact();
    checkAlert();
    goToHomePage();
  }

  /**
   *
   * @return
   */
  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  /**
   *
   * @return
   */
  public List<RegisterData> list() {
    List<RegisterData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element: elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      String lastName = element.findElement(By.xpath("td[2]")).getText();
      String firstName = element.findElement(By.xpath("td[3]")).getText();
      contacts.add(new RegisterData()
              .withId(id).withName("Test1").withMiddle("Test2").withLast("Test3").withNick("Test4").withTitle("Test5").withCompany("Test6").withAddress("Test7").withHome("Test8").withMobile("Test9").withWork("Test10").withFax("Test11").withEmail("Test@mail.ru").withGroup("test1"));
    }
    return contacts;
  }

}
