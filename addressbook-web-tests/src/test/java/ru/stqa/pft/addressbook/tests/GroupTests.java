package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    app.group().cleanCache();
    app.group().goToGroupPage();
    Groups before = app.group().all();
    app.group().create(registrationData);
    Groups after = app.group().all();
    assertEquals(after.size(), before.size() + 1);

    assertThat(after, equalTo(
            before.withAdded(registrationData.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testGroupModification() {
    app.group().cleanCache();
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
    app.group().modify(group);
    Groups after = app.group().all();
    assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }

  @Test
  public void testGroupDeletionTests() {
    app.group().cleanCache();
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Groups after = app.group().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedGroup)));
  }

}
