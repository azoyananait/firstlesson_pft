package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet<RegisterData> {

  private Set<RegisterData> delegate;

  public Contacts(Contacts contact) {
    this.delegate = new HashSet<>(contact.delegate);
  }
  public Contacts() { this.delegate = new HashSet<RegisterData>(); }
  public Contacts(Collection<RegisterData> contacts) {
    this.delegate = new HashSet<RegisterData>(contacts);
  }





  @Override
  protected Set<RegisterData> delegate() {
    return delegate;
  }

  public Contacts withAdded(RegisterData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }
  public Contacts without(RegisterData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
