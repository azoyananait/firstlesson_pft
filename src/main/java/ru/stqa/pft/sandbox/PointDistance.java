package ru.stqa.pft.sandbox;

public class PointDistance {
  public static void main (String[] args) {
    Point p1 = new Point (2, 8);
    Point p2 = new Point(4, 12);
    System.out.println("Расстояние между точками А с координатами (" + p1.x +"; "+ p1.y +") и В с координатами (" + p2.x + "; " + p2.y + ") равна " + p1.distance(p2));
  }
}
