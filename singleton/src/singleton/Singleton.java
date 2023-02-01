package singleton;

public class Singleton {

  private static Singleton instance;

  private String name;

  public Singleton(String name) {
    this.name = name;
  }

  public static Singleton getInstance() {
    if (instance == null) {
      instance = new Singleton("Itis");
    }
    return instance;
  }

  public String getName() {
    return this.name;
  }

}
