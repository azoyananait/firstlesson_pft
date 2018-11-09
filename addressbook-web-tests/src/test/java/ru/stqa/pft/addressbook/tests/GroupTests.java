package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupTests extends TestBase {
  private final GroupData registrationData = new GroupData("test1", null, null);
  private final GroupData modificationData = new GroupData("test1", "test2", "test3");

  @Test
  public void testGroupCreation() {
    app.getGroupHelper().gotoGroupPage();
    app.getGroupHelper().newGroup();
    app.getGroupHelper().fillGroupForm(registrationData);
    app.getGroupHelper().addGroup();
    app.getGroupHelper().returnToGroupPage();
  }

  @Test
  public void testGroupModification() {
    app.getGroupHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup(registrationData);
    app.getGroupHelper().editGroup();
    app.getGroupHelper().fillGroupForm(modificationData);
    app.getGroupHelper().updateGroup();
    app.getGroupHelper().returnToGroupPage();
  }

  @Test
  public void testGroupDeletionTests() {
    app.getGroupHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup(registrationData);
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupPage();
  }

}
