package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.*;

public class GroupTests extends TestBase {
  private final GroupData registrationData = new GroupData().withName("test4");

  @BeforeMethod
  public void ensurePreconditions(){
    app.group().goToGroupPage();
    if(app.group().all().size() == 0) {
      app.group().create(registrationData);
    }
  }

  @Test
  public void testGroupCreation() {
    app.group().goToGroupPage();
    Set<GroupData> before = app.group().all();
    app.group().newGroup();
    app.group().fillGroupForm(registrationData);
    app.group().addGroup();
    app.group().returnToGroupPage();
    Set<GroupData> after = app.group().all();
   Assert.assertEquals(after.size(), before.size() + 1);

   registrationData.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
   before.add(registrationData);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testGroupModification() {
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
    app.group().modify(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testGroupDeletionTests() {
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedGroup);
      Assert.assertEquals (before, after);
  }

}
