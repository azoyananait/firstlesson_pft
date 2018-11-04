package ru.stqa.pft.addressbook.model;

public class RegisterData {
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

  public RegisterData(String name, String middle, String last, String nick, String title, String company, String address, String home, String mobile, String work, String fax, String email) {
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
}