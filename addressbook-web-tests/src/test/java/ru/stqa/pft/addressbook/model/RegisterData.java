package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name  = "addressbook")
public class RegisterData {
  @XStreamOmitField()
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Column(name = "firstname")
  private  String name;

  @Column(name = "lastname")
  private  String last;

  @Column(name = "middlename")
  private  String middle;

  @Column(name = "nickname")
  private  String nick;

  @Column(name = "title")
  private  String title;

  @Column(name = "company")
  private  String company;

  @Column(name = "address")
  @Type(type =  "text")
  private  String address;

  @Column(name = "addr_long")
  @Type(type =  "text")
  private  String address2;

  @Column(name = "home")
  @Type(type =  "text")
  private  String home;

  @Column(name = "mobile")
  @Type(type =  "text")
  private  String mobile;

  @Column(name = "work")
  @Type(type =  "text")
  private  String work;

  @Column(name = "fax")
  @Type(type =  "text")
  private  String fax;

  @Column(name = "email")
  @Type(type =  "text")
  private  String email;

  @Column(name = "email2")
  @Type(type =  "text")
  private  String contactEmail2;

  @Column(name = "email3")
  @Type(type =  "text")
  private  String contactEmail3;

  @Column(name = "photo")
  @Type(type =  "text")
  private  String photo;


  @Transient
  private  String allAddresses;
  @Transient
  private  String allPhones;
  @Transient
  private  String allEmails;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<>();



  public int getId() {
    return id;
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

  public String getSecondaryAddress() { return address2;}

  public String getHome() { return home; }

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

  public String getEmail2() { return contactEmail2; }

  public String getEmail3() { return contactEmail3; }

  public String getAllEmails() { return allEmails; }

  public String getAllPhones() { return allPhones; }

  public String getAllAddresses() { return allAddresses;}

  public Groups getGroups() {
    return new Groups(groups);
  }

  public File getPhoto() { return new File(photo); }


  public RegisterData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
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

  public RegisterData withSecondaryAddress(String address2){
    this.address2 = address2;
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

  public RegisterData withContactEmail2(String contactEmail2) {
    this.contactEmail2 = contactEmail2;
    return this;
  }

  public RegisterData withContactEmail3(String contactEmail3) {
    this.contactEmail3 = contactEmail3;
    return this;
  }

  public RegisterData setAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public RegisterData withAllAddresses(String allAddresses) {
    this.allAddresses = allAddresses;
    return this;
  }
  public RegisterData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RegisterData that = (RegisterData) o;
    return id == that.id &&
            Objects.equals(name, that.name) &&
            Objects.equals(last, that.last) &&
            Objects.equals(middle, that.middle) &&
            Objects.equals(nick, that.nick) &&
            Objects.equals(title, that.title) &&
            Objects.equals(company, that.company) &&
            Objects.equals(address, that.address) &&
            Objects.equals(address2, that.address2) &&
            Objects.equals(home, that.home) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(work, that.work) &&
            Objects.equals(fax, that.fax) &&
            Objects.equals(email, that.email) &&
            Objects.equals(contactEmail2, that.contactEmail2) &&
            Objects.equals(contactEmail3, that.contactEmail3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, last, middle, nick, title, company, address, address2, home, mobile, work, fax, email, contactEmail2, contactEmail3);
  }

  @Override
  public String toString() {
    return "RegisterData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", last='" + last + '\'' +
            ", middle='" + middle + '\'' +
            ", nick='" + nick + '\'' +
            ", title='" + title + '\'' +
            ", company='" + company + '\'' +
            ", address='" + address + '\'' +
            ", address2='" + address2 + '\'' +
            ", home='" + home + '\'' +
            ", mobile='" + mobile + '\'' +
            ", work='" + work + '\'' +
            ", fax='" + fax + '\'' +
            ", email='" + email + '\'' +
            ", contactEmail2='" + contactEmail2 + '\'' +
            ", contactEmail3='" + contactEmail3 + '\'' +
            '}';
  }

  public RegisterData inGroup (GroupData group){
    groups.add(group);
    return this;
  }

}
