import java.util.LinkedList;
import java.util.Arrays;

class Main {

  public static void main(String[] args) {
    String[] alphabet = {
      "1", "0"
    };
    Object[] variations = variations(alphabet, 1);
    for (Object variation : variations) {
      String[] v = (String[]) variation;
      System.out.println(Arrays.toString(v));
    }
  }

  public static void variations() {
    LinkedList<Integer> contador = new LinkedList<Integer>();
    int length = 4;
    int base = 3;
    int i = 0;
    int v = 0;
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
  
  public static Object[] variations(String[] alphabet, int length) {
    LinkedList<Integer> contador = new LinkedList<Integer>();
    LinkedList<String[]> result = new LinkedList<String[]>();
    String[] variation;
    int base = alphabet.length;
    int i = 0;
    int v = 0;
    while (contador.size() <= length) {
      if (i == contador.size()) {
        contador.add(0);
        if (contador.size() <= length) {
          variation = new String[contador.size()];
          for (int j = 0; j < contador.size(); j++)
            variation[j] = alphabet[contador.get(j)];
          result.add(variation);
        }
        i = 0;
      } else {
        v = contador.get(i) + 1;
        if (v < base) {
          contador.set(i, v);
          variation = new String[contador.size()];
          for (int j = 0; j < contador.size(); j++)
            variation[j] = alphabet[contador.get(j)];
          result.add(variation);
          if (i > 0)
            i = 0;
        } else {
          contador.set(i, 0);
          i++;
        }
      }
    }
    return result.toArray();
  }

}
