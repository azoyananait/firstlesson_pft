package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.RegisterData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  private void editContactById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
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

  public void selectContactById(int id){
    wd.findElement(By.xpath("//table[@id='maintable']//input[@value='" + id + "']")).click();;
  }

  public void checkAlert(){
    Alert alert = wd.switchTo().alert();
    alert.accept();
  }

  private void fill(By locator, String text){
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  public void create(RegisterData registerData){
    gotoAddNewPage();
    fillContactForm(registerData, true);
    addContact();
    goToHomePage();
  }
  public void modify(RegisterData modificationData) {
   editContactById(modificationData.getId());
   fillContactForm(modificationData, false);
    updateContact();
    goToHomePage();
  }

  public void delete(RegisterData contact) {
    selectContactById(contact.getId());
    deleteContact();
    checkAlert();
    goToHomePage();

  }

  public int count() {

    return wd.findElements(By.name("selected[]")).size();
  }


  public Contacts all() {
    Contacts contacts = new Contacts();
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
