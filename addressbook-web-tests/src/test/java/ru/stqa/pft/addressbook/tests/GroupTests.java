package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupTests extends TestBase {
  private final GroupData registrationData = new GroupData("test1", null, null);

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


   int max = 0;
   for (GroupData g: after){
     if (g.getId() > max){
       max = g.getId();
     }
   }
    before.add(registrationData);
   registrationData.setId(max);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
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
    GroupData group = new GroupData(before.get(before.size() - 1).getId(),"test1", "test2", "test3");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().updateGroup();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
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

    before.remove(before.size() - 1);
      Assert.assertEquals (before, after);
  }

}
