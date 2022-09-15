package model;

import java.util.Objects;

/**
 * Represents a class of a position with a column (x) and row (Y)
 * and contains to represent the position.
 */
public class Posn {

  private int x;
  private int y;

  /**
   * Constructs a position by taking in a column (x) and row (y).
   * @param x the column.
   * @param y the row.
   */
  public Posn(int x, int y) {
    if (x < 0) {
      throw new IllegalArgumentException("X should not be negative!");
    } if (y < 0) {
      throw new IllegalArgumentException("Y should not be negative!");
    }
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object that) {
    if (that == this) {
      return true;
    }
    if (!(that instanceof Posn)) {
      return false;
    }
    Posn other = (Posn)that;
    return (this.x == other.x)
            && (this.y == other.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  /**
   * Gets the x value in a position.
   * @return the x value.
   */
  public int getX() {
    return x;
  }

  /**
   * Gets the y value in a position.
   * @return the y value.
   */
  public int getY() {
    return y;
  }

  /**
   * Flips the x value in a position by subtracting the given value by x.
   * @param value a given number to flip the column (x).
   * @return a new position.
   */
  public Posn flipX(int value) {
    return new Posn(value - x, y);
  }

  /**
   * Flips the y value in a position by subtracting the given value by y.
   * @param value a given number to flip the row (y).
   * @return a new position.
   */
  public Posn flipY(int value) {
    return new Posn(x, value - y);
  }
}
