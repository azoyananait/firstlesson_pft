package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.UsersData;

import java.util.List;

public class DbHelper {
    private final org.hibernate.SessionFactory sessionFactory;

    public DbHelper() {
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    public Users users() {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<UsersData> result = session.createQuery("from mantis_user_table").list();
      session.getTransaction().commit();
      session.close();
      return new Users(result);
    }
  }

