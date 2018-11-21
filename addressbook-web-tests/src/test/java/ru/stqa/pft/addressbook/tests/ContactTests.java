package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.RegisterData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactTests extends TestBase {
  private final RegisterData registerData = new RegisterData().withName("Test1").withMiddle("Test2").withLast("Test3").withNick("Test4").withTitle("Test5").withCompany("Test6").withAddress("Test7").withHome("Test8").withMobile("Test9").withWork("Test10").withFax("Test11").withEmail("Test@mail.ru").withGroup("test1");
  private final RegisterData modificationData = new RegisterData().withName("Test10").withMiddle("Test12").withLast("Test3").withNick("Test4").withTitle("Test5").withCompany("Test6").withAddress("Test7").withHome("Test8").withMobile("Test9").withWork("Test10").withFax("Test11").withEmail("Test@mail.ru").withGroup("test1");

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.contact().count() == 0){
      app.contact().create(registerData);
    }
  }

  @Test
  public void testRegistration() {
    app.contact().goToHomePage();
    Contacts before = app.contact().all();
    app.contact().gotoAddNewPage();
    RegisterData contact = new RegisterData().
            withName("Test1").withMiddle("Test2").withLast("Test3").withNick("Test4").withTitle("Test5").withCompany("Test6").withAddress("Test7").withHome("Test8").withMobile("Test9").withWork("Test10").withFax("Test11").withEmail("Test@mail.ru").withGroup("test1");
    app.contact().fillContactForm(registerData, true);
    app.contact().addContact();
    app.contact().goToHomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test
  public void testContactModification() {
    app.contact().goToHomePage();
    Contacts before = app.contact().all();
    RegisterData modifiedContact = before.iterator().next();
    modificationData.withId(modifiedContact.getId());
    app.contact().modify(modificationData);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(modifiedContact)));

  }

  @Test
  public void testContactDelete() {
    app.contact().goToHomePage();
    Contacts before = app.contact().all();
    RegisterData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before .size()- 1);

    assertThat(after, equalTo(before.without(deletedContact)));
  }
}