package ru.stqa.pft.mantis.tests;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UsersData;

import java.util.List;

public class DBConnectionTests {
  private SessionFactory sessionFactory;

  @BeforeClass
  protected void setUp() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy( registry );
    }
  }

  @Test
  public void dbConnectionForUsers()  {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UsersData> result = session.createQuery("from mantis_user_table").list();

    for (UsersData users : result) {
      System.out.println(users);
    }

    session.getTransaction().commit();
    session.close();
  }

}
