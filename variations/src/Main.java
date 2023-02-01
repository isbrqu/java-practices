import java.util.LinkedList;

class Main {

  public static void main(String[] args) {
    LinkedList<Integer> contador = new LinkedList<Integer>();
    int length = 4;
    int base = 3;
    int i = 0;
    int v = 0;
    boolean flag = true;
    while (contador.size() <= length) {
      if (i == contador.size()) {
        contador.add(0);
        if (contador.size() <= length)
          System.out.println(contador);
        i = 0;
      } else {
        v = contador.get(i) + 1;
        if (v < base) {
          contador.set(i, v);
          System.out.println(contador);
          if (i > 0)
            i = 0;
        } else {
          contador.set(i, 0);
          i++;
        }
      }
    }
  }

}
