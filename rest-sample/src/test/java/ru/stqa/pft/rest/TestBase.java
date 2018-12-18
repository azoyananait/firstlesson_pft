package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {

  protected Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");
  }

  protected Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
  }

  protected int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public void skipIfNotFixed (int issueId){
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored actual issue " + issueId);
    }
  }

  private boolean isIssueOpen(int issueId){
    try {
      String url = "http://bugify.stqa.ru/api/issues/" + issueId + ".json";
      String json = getExecutor().execute(Request.Get(url)).returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonArray array = parsed.getAsJsonObject().get("issues").getAsJsonArray();

      if (array.size() != 1) {
        throw new IllegalArgumentException("Return more that 1 issue");
      }

      JsonElement elm = array.get(0);
      JsonElement state = elm.getAsJsonObject().get("state");
      String stringState = state.toString().replaceAll("^\"|\"$", "");

      if (stringState.equals("0")) {
        return true;
      }
      return false;
    }catch (IOException ex){
      return false;
    }
  }





}
