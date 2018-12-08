package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.RegisterData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static  void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<RegisterData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")){
      saveAsJson(contacts, new File(file));
    } else
      System.out.println("Unrecognized format " + format);
  }

  private void saveAsJson(List<RegisterData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<RegisterData> contacts, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(RegisterData.class);
    String xml = xStream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void saveAsCsv(List<RegisterData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (RegisterData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getName(), contact.getMiddle(), contact.getLast()
                , contact.getCompany(), contact.getAddress(), contact.getMobile(), contact.getEmail()));
      }
    }
  }

  private List<RegisterData> generateContacts(int count) {
    List<RegisterData> contacts = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      contacts.add(new RegisterData().
              withName(String.format("Azoyan %s", i)).withMiddle(String.format("tester %s", i))
              .withLast(String.format("testerov %s", i)).withCompany(String.format("Testerik %s", i)).withAddress(String.format("Test %s", i))
              .withMobile(String.format("+123456789%s", i)).withEmail(String.format("test%s@mail.com", i)));
    }
    return contacts;
  }
}
