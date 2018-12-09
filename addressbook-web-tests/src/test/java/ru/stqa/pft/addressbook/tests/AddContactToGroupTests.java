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

public class AddContactToGroupTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    Groups groups = app.db().groups();

    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("testName").withFooter("FooterTest").withHeader("HeaderTest"));
      groups = app.db().groups();
    }

    app.goTo().goToHomePage();
    if (app.db().contacts().size() == 0) {
      File photo = new File("src/test/resources/stru.png");
      app.contact().create(new RegisterData()
              .withName("Test1").withMiddle("Test2").withLast("Test3").withNick("Test4").withTitle("Test5").withCompany("Test6").withAddress("Test7").withHome("Test8").withMobile("Test9").withWork("Test10").withFax("Test11").withEmail("Test@mail.ru").withPhoto(photo));
    }
  }


  @Test
  public void testContactAddedToGroup() {

    Groups groupsInTheBeginning = app.db().groups();
    Contacts contactInTheBeginning = app.db().contacts();

    RegisterData selectedContact = contactInTheBeginning.iterator().next();
    Groups groupSelectedContact = selectedContact.getGroups();

    GroupData selectedGroup;
    Iterator<RegisterData> iteratorContacts = contactInTheBeginning.iterator();

    while (iteratorContacts.hasNext()) {
      if (groupSelectedContact.equals(groupsInTheBeginning)) {
        selectedContact = iteratorContacts.next();
        groupSelectedContact = selectedContact.getGroups();
      } else {
        break;
      }
    }
    if (groupSelectedContact.equals(groupsInTheBeginning)) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("testName"));
    }
    groupsInTheBeginning = app.db().groups();
    groupSelectedContact = selectedContact.getGroups();
    groupsInTheBeginning.removeAll(groupSelectedContact);

    if (groupsInTheBeginning.size() > 0) {
      selectedGroup = groupsInTheBeginning.iterator().next();
    } else {
      throw new RuntimeException("no groups");
    }

    app.goTo().goToHomePage();
    app.contact().selectContactById(selectedContact.getId());
    app.contact().addInGroupById(selectedGroup.getId());
    app.goTo().selectGroupPage(selectedGroup.getId());

    RegisterData contactAfter = app.db().contactById(selectedContact.getId()).iterator().next();
    Groups groupsContactAfter = contactAfter.getGroups();

    assertThat(groupsContactAfter, equalTo(groupSelectedContact.withAdded(selectedGroup)));
  }

}
