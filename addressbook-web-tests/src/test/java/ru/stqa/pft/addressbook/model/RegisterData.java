package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class RegisterData {
  private int id = Integer.MAX_VALUE;
  private  String name;
  private  String middle;
  private  String last;
  private  String nick;
  private  String title;
  private  String company;
  private  String address;
  private  String home;
  private  String mobile;
  private  String work;
  private  String fax;
  private  String email;
  private  String group;


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

  public RegisterData withId(int id) {
    this.id = id;
    return this;
  }

  public RegisterData withName(String name) {
    this.name = name;
    return this;
  }

  public RegisterData withMiddle(String middle) {
    this.middle = middle;
    return this;
  }

  public RegisterData withLast(String last) {
    this.last = last;
    return this;
  }

  public RegisterData withNick(String nick) {
    this.nick = nick;
    return this;
  }

  public RegisterData withTitle(String title) {
    this.title = title;
    return this;
  }

  public RegisterData withCompany(String company) {
    this.company = company;
    return this;
  }

  public RegisterData withAddress(String address) {
    this.address = address;
    return this;
  }

  public RegisterData withHome(String home) {
    this.home = home;
    return this;
  }

  public RegisterData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public RegisterData withWork(String work) {
    this.work = work;
    return this;
  }

  public RegisterData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public RegisterData withEmail(String email) {
    this.email = email;
    return this;
  }

  public RegisterData withGroup(String group) {
    this.group = group;
    return this;
  }
}
