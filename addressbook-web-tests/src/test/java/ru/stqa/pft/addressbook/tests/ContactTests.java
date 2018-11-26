package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.RegisterData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactTests extends TestBase {
  private RegisterData registerData = new RegisterData().withName("Test1").withMiddle("Test2").withLast("Test3").withNick("Test4").withTitle("Test5").withCompany("Test6").withAddress("Test7").withHome("Test8").withMobile("Test9").withWork("Test10").withFax("Test11").withEmail("Test@mail.ru").withGroup("test1");
  private RegisterData modificationData = new RegisterData().withName("ModifiedName").withMiddle("ModifiedMiddleName").withLast("ModifiedLastName").withNick("ModifiedNick").withTitle("ModifiedTitle").withCompany("ModifiedCompany").withAddress("ModifiedAddress").withHome("ModifiedHome").withMobile("ModifiedMobile").withWork("ModifiedWork").withFax("ModifiedFax").withEmail("ModifiedEmail@mail.ru").withGroup("ModifiedGroup");

  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().goToHomePage();
    if (app.contact().count() == 0) {
      app.contact().create(registerData);
    }
  }

  @Test
  public void testRegistration() {
    Contacts before = app.contact().all();
    app.contact().gotoAddNewPage();
    app.contact().fillContactForm(registerData, true);
    app.contact().addContact();
    app.contact().goToHomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(registerData.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    RegisterData modifiedContact = before.iterator().next();

    modificationData.withId(modifiedContact.getId());
    app.contact().modify(modificationData);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(modificationData)));

  }

  @Test
  public void testContactDelete() {
    Contacts before = app.contact().all();
    RegisterData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);

    assertThat(after, equalTo(before.without(deletedContact)));
  }

  @Test
  public void testContactPhones() {
    Contacts before = app.contact().all();
    RegisterData modifiedContact = before.iterator().next();
    RegisterData contactInfoFromEditForm = app.contact().infoFromEditForm(modifiedContact);

    assertThat(modifiedContact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(RegisterData contact) {
    return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");

  }

  @Test
  public void testContactEmails() {
    // получаем список контактов
    Contacts before = app.contact().all();
    // получаем первый контакт из списка
    RegisterData modifiedContact = before.iterator().next();
    // заполняем контакт данными из формы редактирования
    RegisterData contactInfoFromEditForm = app.contact().infoFromEditForm(modifiedContact);
    assertThat(modifiedContact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(RegisterData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  @Test
  public void testContactAddresses() {
    Contacts before = app.contact().all();
    RegisterData modifiedContact = before.iterator().next();
    RegisterData contactInfoFromEditForm = app.contact().infoFromEditForm(modifiedContact);

    assertThat(modifiedContact.getAllAddresses(), equalTo(mergeAddresses(contactInfoFromEditForm)));
  }

  private String mergeAddresses(RegisterData contact) {
    return Arrays.asList(contact.getAddress(), contact.getSecondaryAddress())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }
}