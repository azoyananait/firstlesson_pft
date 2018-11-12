package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupTests extends TestBase {
  private final GroupData registrationData = new GroupData("test1", null, null);
  private final GroupData modificationData = new GroupData("test1", "test2", "test3");

  @Test
  public void testGroupCreation() {
    app.getGroupHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().newGroup();
    app.getGroupHelper().fillGroupForm(registrationData);
    app.getGroupHelper().addGroup();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
   Assert.assertEquals(after.size(), before.size() + 1);
  }

  @Test
  public void testGroupModification() {
    app.getGroupHelper().gotoGroupPage();
    if(!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(registrationData);
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().editGroup();
    app.getGroupHelper().fillGroupForm(modificationData);
    app.getGroupHelper().updateGroup();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());
  }

  @Test
  public void testGroupDeletionTests() {
    app.getGroupHelper().gotoGroupPage();
    if(!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(registrationData);
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }

}
