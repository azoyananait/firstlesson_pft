package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args){ //массив с набором строк
    String[] langs = {"Java", "C#", "Python", "PHP"};

    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP"); // List<> - списки

    for (String l : languages) {
      System.out.println("Я хочу выучить" + l);
    }
  }
}
