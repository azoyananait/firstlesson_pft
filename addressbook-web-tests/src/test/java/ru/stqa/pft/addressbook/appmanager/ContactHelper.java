package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.RegisterData;

import java.io.File;
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
    attach(By.name("photo"), registerData.getPhoto());

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
 private void attach(By locator, File file){
    wd.findElement(locator).sendKeys(file.getAbsolutePath());
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
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> columns = element.findElements(By.cssSelector("td"));
      String lastName = columns.get(1).getText();
      String firstName = columns.get(2).getText();
      String allAddresses = columns.get(3).getText();
      String allEmails = columns.get(4).getText();
      String allPhones = columns.get(5).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new RegisterData().withId(id).withName(firstName).withLast(lastName).withAllAddresses(allAddresses)
              .setAllEmails(allEmails).withAllPhones(allPhones));
    }
      return contacts;
  }

  public RegisterData infoFromEditForm(RegisterData modifiedContact){
    initContactModificationById(modifiedContact.getId());
    String name = wd.findElement(By.name("firstname")).getAttribute("value");
    String last = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String address2 = wd.findElement(By.name("address2")).getAttribute("value");
    wd.navigate().back();
    return new RegisterData().withId(modifiedContact.getId())
                            .withName(name)
                            .withLast(last)
                            .withHome(home)
                            .withMobile(mobile)
                            .withWork(work)
                            .withEmail(email)
                            .withContactEmail2(email2)
                            .withContactEmail3(email3)
                            .withAddress(address)
                            .withSecondaryAddress(address2);
  }


  private void initContactModificationById ( int id){
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

  }
}
