package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class RegisterData {
  private int id;
  private final String name;
  private final String middle;
  private final String last;
  private final String nick;
  private final String title;
  private final String company;
  private final String address;
  private final String home;
  private final String mobile;
  private final String work;
  private final String fax;
  private final String email;
  private String group;


  public RegisterData(int id,String name, String middle, String last, String nick, String title, String company, String address, String home, String mobile, String work, String fax, String email, String group) {
    this.id = id;
    this.name = name;
    this.middle = middle;
    this.last = last;
    this.nick = nick;
    this.title = title;
    this.company = company;
    this.address = address;
    this.home = home;
    this.mobile = mobile;
    this.work = work;
    this.fax = fax;
    this.email = email;
    this.group = group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RegisterData that = (RegisterData) o;
    return id == that.id &&
            Objects.equals(name, that.name) &&
            Objects.equals(last, that.last);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, last);
  }

  public RegisterData(String name, String middle, String last, String nick, String title, String company, String address, String home, String mobile, String work, String fax, String email, String group) {
    this.id = 0;
    this.name = name;
    this.middle = middle;
    this.last = last;
    this.nick = nick;
    this.title = title;
    this.company = company;
    this.address = address;
    this.home = home;
    this.mobile = mobile;
    this.work = work;
    this.fax = fax;
    this.email = email;
    this.group = group;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "RegisterData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", last='" + last + '\'' +
            '}';
  }

  public String getName() {
    return name;
  }

  public String getMiddle() {
    return middle;
  }

  public String getLast() {
    return last;
  }

  public String getNick() {
    return nick;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHome() {
    return home;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWork() {
    return work;
  }

  public String getFax() {
    return fax;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  public void setId(int id) {
    this.id = id;
  }
}
