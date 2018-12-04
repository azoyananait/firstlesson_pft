package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.RegisterData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactTests extends TestBase {
  private RegisterData registerData = new RegisterData().withName("Test1").withMiddle("Test2").withLast("Test3").withNick("Test4").withTitle("Test5").withCompany("Test6").withAddress("Test7").withHome("Test8").withMobile("Test9").withWork("Test10").withFax("Test11").withEmail("Test@mail.ru").withGroup("test").withPhoto(new File("src/test/resources/stru.png"));
  private RegisterData modificationData = new RegisterData().withName("ModifiedName").withMiddle("ModifiedMiddleName").withLast("ModifiedLastName").withNick("ModifiedNick").withTitle("ModifiedTitle").withCompany("ModifiedCompany").withAddress("ModifiedAddress").withHome("ModifiedHome").withMobile("ModifiedMobile").withWork("ModifiedWork").withFax("ModifiedFax").withEmail("ModifiedEmail@mail.ru").withGroup("ModifiedGroup");

  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().goToHomePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(registerData);
    }
  }
  @DataProvider
  public Iterator<Object[]> validContactsCsv() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    //open file for reading
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")))) {
      //BufferedReader allows to read the whole row from file (method readLine)
      String line = reader.readLine();
      while (line != null) {
        String[] split = line.split(";"); //to cut row by ;-separator
        list.add(new Object[]{new RegisterData().withName(split[0]).withLast(split[1]).withNick(split[2])
                .withCompany(split[3]).withAddress(split[4]).withMobile(split[5]).withEmail(split[6]).withGroup(split[7])});
        line = reader.readLine();
      }
      return list.iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(RegisterData.class);
      List<RegisterData> contacts = (List<RegisterData>) xStream.fromXML(xml);
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();

    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<RegisterData> contacts = gson.fromJson(json, new TypeToken<List<RegisterData>>() {
      }.getType());
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();

    }
  }

  @Test(dataProvider = "validContactsXml")
  public void testRegistration(RegisterData contact) {
    Contacts before = app.db().contacts();
    app.contact().gotoAddNewPage();
    app.contact().fillContactForm(registerData, true);
    app.contact().addContact();
    app.contact().goToHomePage();
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(registerData.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }


  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    RegisterData modifiedContact = before.iterator().next();
    modificationData.withId(modifiedContact.getId());
    app.contact().modify(modificationData);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(modificationData)));

  }

  @Test
  public void testContactDelete() {
    Contacts before = app.db().contacts();
    RegisterData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.db().contacts();
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