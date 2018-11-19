package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GroupHelper extends NavigationHelper {

  public GroupHelper(WebDriver wd) {
    super(wd);
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

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
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
  }

  public void modify(int index, GroupData group) {
    selectGroup(index);
    editGroup();
    fillGroupForm(group);
    updateGroup();
    returnToGroupPage();
  }


  public void delete(int index) {
    selectGroup(index);
    deleteGroup();
    returnToGroupPage();
  }

  /**
   *
   * @return
   */
  public boolean isThereAGroup(){
    return isElementPresent(By.className("group"));
  }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element: elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;
  }
}
