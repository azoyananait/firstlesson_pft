package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@javax.persistence.Entity
@javax.persistence.Table(name = "group_list")

public class GroupData {
  @XStreamOmitField
  @Id
  @Column(name = "group_id")
  private  int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "group_name")
  private  String name;

  @Expose
  @Column(name = "group_header")
  @Type(type ="text")
  private  String header;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return id == groupData.id &&
            Objects.equals(name, groupData.name) &&
            Objects.equals(header, groupData.header) &&
            Objects.equals(footer, groupData.footer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, header, footer);
  }

  @Expose
  @Column(name = "group_footer")
  @Type(type =  "text")
  private  String footer;

  @ManyToMany(mappedBy = "groups")
  private Set<RegisterData> contacts = new HashSet<RegisterData>();

  public Set<RegisterData> getContacts() {
    return new Contacts(contacts);
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", header='" + header + '\'' +
            ", footer='" + footer + '\'' +
            '}';
  }

  public int getId() { return id; }

  public GroupData withId(int id) {
    this.id = id;
    return this;
  }
  public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }


  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }


}
