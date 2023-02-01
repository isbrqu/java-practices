package singleton;
import singleton.Singleton;

public class Main {

  public static void main(String[] args) {
    Singleton s = Singleton.getInstance();
    System.out.println(s.getName());
  }

}
