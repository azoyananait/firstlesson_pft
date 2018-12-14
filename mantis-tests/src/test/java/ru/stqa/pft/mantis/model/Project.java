package ru.stqa.pft.mantis.model;

public class Project {
  private int id;
  private String name;

  public int getId() {
    return id;
  }


  public String getName() {
    return name;
  }

  public Project withName(String name) {
    this.name = name;
    return this;
  }

  public Project withId(int id) {
    this.id = id;
    return this;
  }
}
