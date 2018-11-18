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
    if(app.contact().count() == 0){
      app.contact().create(registerData);
    }
  }

  @Test
  public void testRegistration() {
    app.contact().goToHomePage();
    List<RegisterData> before = app.contact().list();
    app.contact().gotoAddNewPage();
    RegisterData contact = new RegisterData("Test1", "Test2", "Test3", "Test4", "Test5", "Test6", "Test7", "Test8", "Test9", "Test10", "Test11", "Test@mail.ru", "test1");
    app.contact().fillContactForm(registerData, true);
    app.contact().addContact();
    app.contact().goToHomePage();
    List<RegisterData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((Comparator<RegisterData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

  @Test
  public void testContactModification() {
    app.contact().goToHomePage();
    List<RegisterData> before = app.contact().list();
    int index = before.size() - 1;
    modificationData.setId(before.get(index).getId());
    app.contact().modifyContact(index, modificationData);
    List<RegisterData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(modificationData);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }

  @Test
  public void testContactDelete() {
    app.contact().goToHomePage();
    List<RegisterData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<RegisterData> after = app.contact().list();
    Assert.assertEquals(after.size(), before .size()- 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }
  

}