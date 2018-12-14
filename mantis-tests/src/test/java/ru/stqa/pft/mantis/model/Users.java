package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UsersData> {

  private Set<UsersData> delegate;

  public Users(Collection<UsersData> users) {
    this.delegate = new HashSet<UsersData>(users);
  }

  @Override
  protected Set<UsersData> delegate() {
    return delegate;
  }

  public Users(Users users) {
    this.delegate = new HashSet<UsersData>(users.delegate);
  }
}