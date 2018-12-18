package ru.stqa.pft.rest;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class RestTests extends TestBase{

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    Assert.assertEquals(newIssues, oldIssues);
  }

  @Test
  public void testIssueStatus () {
    int issueId = 630;

    try {
      skipIfNotFixed(issueId);
      System.out.println("Issue is closed");
    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
    }
  }

}
