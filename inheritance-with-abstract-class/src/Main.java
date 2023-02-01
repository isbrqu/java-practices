import java.lang.Math;
import Secu.Foo;
import Secu.Bar;
import Secu.Nor;

class Main {

  public static void main(String[] args) {
    Foo nor1 = new Nor();
    Bar nor2 = (Bar) nor1;
    System.out.println(nor2.hola());
  }

}
