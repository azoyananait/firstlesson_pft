package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

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

  public void selectGroup(GroupData registrationData) {
    createGroup(registrationData);
    click(By.name("selected[]"));
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
  private void createGroup(GroupData registrationData) {
    if(!isElementPresent(By.className("group"))){
      newGroup();
      fillGroupForm(registrationData);
      addGroup();
      returnToGroupPage();
    }
  }
}
