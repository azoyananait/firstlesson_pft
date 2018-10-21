package ru.stqa.pft.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;

public class PointDistance {

  @Test
  public void Distance() {
    Point p1 = new Point (2, 8);
    Point p2 = new Point(4, 12);
    Assert.assertEquals(p1.distance(p2), 4.47213595499958);
  }
}
