package ru.stqa.pft.sandbox;


public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;

  }

  public double distance(Point p2) {
    double x = ((this.x - p2.x) * (this.x - p2.x));
    double y = ((this.y - p2.y) * (this.y - p2.y));
    return Math.sqrt(x + y);

  }
}
