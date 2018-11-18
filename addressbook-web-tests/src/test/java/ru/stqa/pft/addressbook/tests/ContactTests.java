package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.RegisterData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactTests extends TestBase {
  private final RegisterData registerData = new RegisterData("Test1", "Test2", "Test3", "Test4", "Test5", "Test6", "Test7", "Test8", "Test9", "Test10", "Test11", "Test@mail.ru", "test1");
  private final RegisterData modificationData = new RegisterData("Test10", "Test12", "Test3", "Test4", "Test5", "Test6", "Test7", "Test8", "Test9", "Test10", "Test11", "Test@mail.ru", "test1");

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.getContactHelper().getContactCount() == 0){
      app.getContactHelper().createContact(registerData);
    }
  }

  @Test
  public void testRegistration() {
    app.getContactHelper().goToHomePage();
    List<RegisterData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoAddNewPage();
    RegisterData contact = new RegisterData("Test1", "Test2", "Test3", "Test4", "Test5", "Test6", "Test7", "Test8", "Test9", "Test10", "Test11", "Test@mail.ru", "test1");
    app.getContactHelper().fillContactForm(registerData, true);
    app.getContactHelper().addContact();
    app.getContactHelper().goToHomePage();
    List<RegisterData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((Comparator<RegisterData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

  @Test
  public void testContactModification() {
    app.getContactHelper().goToHomePage();
    List<RegisterData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    app.getContactHelper().editContact(index);
    modificationData.setId(before.get(index).getId());
    app.getContactHelper().fillContactForm(modificationData, false);
    app.getContactHelper().updateContact();
    app.getContactHelper().goToHomePage();
    List<RegisterData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(modificationData);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

  @Test
  public void testContactDelete() {
    app.getContactHelper().goToHomePage();
    List<RegisterData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().checkAlert();
    app.getContactHelper().goToHomePage();
    List<RegisterData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before .size()- 1);

    before.remove(before .size() - 1);
    Assert.assertEquals(before, after);
  }

}