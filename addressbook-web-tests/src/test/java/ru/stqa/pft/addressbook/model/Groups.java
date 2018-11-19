package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData>{

  private Set<GroupData> delegate;

  public Groups(Groups groups) {
    this.delegate = new HashSet<GroupData>(groups.delegate);
  }

  public Groups() {
    this.delegate = new HashSet<GroupData>();
  }

  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }
  public Groups withAdded(GroupData registrationData){
    Groups groups = new Groups(this);
    groups.add(registrationData);
    return groups;
    
  }
  public Groups without(GroupData registrationData){
    Groups groups = new Groups(this);
    groups.remove(registrationData);
    return groups;

  }
}
