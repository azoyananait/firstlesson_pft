package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupTests extends TestBase {
  private final GroupData registrationData = new GroupData("test1", null, null);
  private final GroupData modificationData = new GroupData("test1", "test2", "test3");

  @Test
  public void testGroupCreation() {
    app.getGroupHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().newGroup();
    app.getGroupHelper().fillGroupForm(registrationData);
    app.getGroupHelper().addGroup();
    app.getGroupHelper().returnToGroupPage();
   int after = app.getGroupHelper().getGroupCount();
   Assert.assertEquals(after, before + 1);
  }

  @Test
  public void testGroupModification() {
    app.getGroupHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if(!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(registrationData);
    }
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().editGroup();
    app.getGroupHelper().fillGroupForm(modificationData);
    app.getGroupHelper().updateGroup();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);
  }

  @Test
  public void testGroupDeletionTests() {
    app.getGroupHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if(!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(registrationData);
    }
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before - 1);
  }

}
