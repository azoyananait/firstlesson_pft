package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.RegisterData;

public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration(){
    RegisterData registerData = new RegisterData("Test1", "Test2", "Test3", "Test4", "Test5", "Test6", "Test7", "Test8", "Test9", "Test10", "Test11", "Test@mail.ru");
    app.getNavigationHelper().gotoAddNewPage();
    app.getRegistrationHelper().fillRegisterForm(registerData);
    app.getRegistrationHelper().submit();
    app.getNavigationHelper().returnToMainPage();
  }

}
