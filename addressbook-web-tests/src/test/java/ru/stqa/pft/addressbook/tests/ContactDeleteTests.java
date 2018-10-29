package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;


public class ContactDeleteTests extends TestBase {


  @Test

  public void testContactDelete() {
    app.getNavigationHelper().goToHomePage();
    app.getContactModificationHelper().initContactSelect();
    app.getContactModificationHelper().initContactDelete();
    app.getContactModificationHelper().checkAlert();
    app.getNavigationHelper().returnToMainPage();
  }
}