package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.RegisterData;

public class ContactTests extends TestBase {
  private final RegisterData registerData = new RegisterData("Test1", "Test2", "Test3", "Test4", "Test5", "Test6", "Test7", "Test8", "Test9", "Test10", "Test11", "Test@mail.ru", "test1");
  private final RegisterData modificationData = new RegisterData("Test10", "Test12", "Test3", "Test4", "Test5", "Test6", "Test7", "Test8", "Test9", "Test10", "Test11", "Test@mail.ru", "test1");

  @Test
  public void testRegistration(){
    app.getContactHelper().gotoAddNewPage();
    app.getContactHelper().fillContactForm(registerData,true);
    app.getContactHelper().addContact();
    app.getContactHelper().returnToMainPage();
  }

  @Test
  public void testContactModification() {
    app.getContactHelper().goToHomePage();
    app.getContactHelper().editContact(registerData);
    app.getContactHelper().fillContactForm(modificationData, false);
    app.getContactHelper().updateContact();
    app.getContactHelper().returnToMainPage();
  }

  @Test
  public void testContactDelete() {
    app.getContactHelper().goToHomePage();
    app.getContactHelper().selectContact(registerData);
    app.getContactHelper().deleteContact();
    app.getContactHelper().checkAlert();
    app.getContactHelper().returnToMainPage();
  }

}