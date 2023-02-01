import java.util.LinkedList;

class Main {

  public static void main(String[] args) {
    LinkedList<Integer> contador = new LinkedList<Integer>();
    int length = 3;
    int base = 3;
    contador.add(0);
    while (contador.size() <= length) {
      for (int i = 1; i <= base; i++) {
        System.out.println(contador);
        contador.set(0, i);
      }
      for (int i = 0; i < contador.size(); i++) {
        if (contador.get(i) == base) {
          contador.set(i, 0);
          int next = i + 1;
          if (next == contador.size())
            contador.add(0);
          else
            contador.set(next, contador.get(next) + 1);
        }
      }
    }
  }

}
