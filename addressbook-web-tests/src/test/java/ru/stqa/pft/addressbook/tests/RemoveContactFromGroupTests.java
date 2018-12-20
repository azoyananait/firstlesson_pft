package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.RegisterData;

import java.io.File;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
      Groups groups = app.db().groups();
      Contacts contacts = app.db().contacts();

      if (groups.size() == 0) {
        app.goTo().GroupPage();
        app.group().create(new GroupData().withName("testName").withFooter("FooterTest").withHeader("HeaderTest"));
        groups = app.db().groups();
      }

      app.goTo().goToHomePage();
      if (contacts.size() == 0) {
        File photo = new File("src/test/resources/stru.png");
        app.contact().create(new RegisterData()
                .withName("Test1").withMiddle("Test2").withLast("Test3").withNick("Test4").withTitle("Test5").withCompany("Test6").withAddress("Test7").withHome("Test8").withMobile("Test9").withWork("Test10").withFax("Test11").withEmail("Test@mail.ru").withPhoto(photo));
        contacts = app.db().contacts();
      }

      GroupData group = groups.iterator().next();
      RegisterData contact = contacts.iterator().next();
      app.contact().selectContactById(contact.getId());
      app.contact().addInGroupById(group.getId());
    }

    @Test
    public void RemoveContactFromGroupTests() {
      Contacts contacts = app.db().contacts();
      Iterator<RegisterData> iteratorContacts = contacts.iterator();
      RegisterData contact = iteratorContacts.next();
      GroupData group = contact.getGroups().iterator().next();
      app.goTo().goToHomePage();

      do {
        if (contact.getGroups().size() > 0) {
          group = contact.getGroups().iterator().next();
          app.contact().findGroupById(group.getId());
          break;
        } else {
          contact = iteratorContacts.next();
        }
      } while (iteratorContacts.hasNext());
      app.contact().selectContactById(contact.getId());
      app.contact().removeContactFromGroup();
      app.goTo().selectGroupPage(group.getId());
      Groups groupsContactsAfter = app.db().contactById(contact.getId()).iterator().next().getGroups();

      assertThat(groupsContactsAfter, equalTo(contact.getGroups().without(group)));
    }
  }

