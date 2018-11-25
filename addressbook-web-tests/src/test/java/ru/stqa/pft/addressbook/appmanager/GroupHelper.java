package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends NavigationHelper {
  private Groups groupCache;

  public GroupHelper(WebDriver wd) {
    super(wd);
    groupCache = null;
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void newGroup() {
    click(By.name("new"));
  }

  public void addGroup() {
    click(By.name("submit"));
  }

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void editGroup() {
    click(By.name("edit"));
  }

  public void updateGroup() {
    click(By.name("update"));
  }

  public void deleteGroup() {
    click(By.name("delete"));
  }


  /**
   * Создание группы, если не существует ни одной
   * @param registrationData данные для регистрации контакта
   */
  public void create(GroupData registrationData) {
      newGroup();
      fillGroupForm(registrationData);
      addGroup();
      returnToGroupPage();
      cleanCache();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    editGroup();
    fillGroupForm(group);
    updateGroup();
    returnToGroupPage();
    cleanCache();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteGroup();
    returnToGroupPage();
    cleanCache();
  }
  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Groups all() {
    if (groupCache != null){
      return new Groups(groupCache);
    }
    groupCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element: elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groupCache.add(new GroupData().withId(id).withName(name));
    }
    return new Groups(groupCache);
  }

  public void cleanCache(){
    groupCache = null;
  }

}
