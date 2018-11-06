package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.RegisterData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    RegisterData registerData = new RegisterData("Test10", "Test12", "Test3", "Test4", "Test5", "Test6", "Test7", "Test8", "Test9", "Test10", "Test11", "Test@mail.ru", null);
    app.getNavigationHelper().goToHomePage();
    app.getContactModificationHelper().initContactModification();
    app.getRegistrationHelper().fillRegisterForm(registerData,false);
    app.getContactModificationHelper().submitContactModification();
    app.getNavigationHelper().returnToMainPage();
  }

}
