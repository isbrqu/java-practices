package Secu;

public abstract class Bar extends Foo {

  private int y;

  public Bar(int y) {
    super(0);
    this.y = y;
  }

  public int getY() {
    return this.y;
  }

  public abstract int hola();

} 
