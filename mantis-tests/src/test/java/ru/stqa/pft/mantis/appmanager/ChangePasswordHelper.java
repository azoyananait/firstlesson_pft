package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UsersData;

public class ChangePasswordHelper extends HelperBase {
  public ChangePasswordHelper(ApplicationManager app) {

    super(app);
  }
  public void selectUser(UsersData userNumber) {
    selectUserById(userNumber.getId());
  }

  public void selectUserById(int id) {
    click(By.xpath("//a[@href='manage_user_edit_page.php?user_id=" + id + "']"));
  }

  public void resetPassword() {
    click(By.cssSelector("input[value='Reset Password']"));
  }

}
