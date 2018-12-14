package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase{

  //@BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException {
    long now = System.currentTimeMillis();
    String username = String.format("user%s", now);
    String password = "password";
    String email = String.format("user%s@localhost", now);
    app.james().createUser(username,password);
    app.registration().start("user1", email);
   // List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    List<MailMessage> mailMessages = app.james().waitForMail(username,password,90000);
    String confrmationLink = findConfrmationLink(mailMessages, email);
    app.registration().finish(confrmationLink, password);
    assertTrue(app.newSession().login(username,password));
  }

  private String  findConfrmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  //@AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }

}
