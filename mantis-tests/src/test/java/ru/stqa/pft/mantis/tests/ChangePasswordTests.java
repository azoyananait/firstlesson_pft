package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.UsersData;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {
  WebDriver wd;
  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }
  @Test
  public void changePasswordTests() throws IOException {
    String password = "password";
    Users atStart = app.db().users();
    UsersData passwordResetedUser = atStart.iterator().next();
    UsersData userNumber = new UsersData().withId(passwordResetedUser.getId()).withEmail(passwordResetedUser.getEmail()).withUsername(passwordResetedUser.getUsername());

    app.session().loginToMantis();
    app.session().goToUsersPage();
    app.changepass().selectUser(userNumber);
    app.changepass().resetPassword();

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, userNumber.getEmail());
    app.session().finishRestor(confirmationLink, password);
    assertTrue(app.newSession().login(userNumber.getUsername(), password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}
