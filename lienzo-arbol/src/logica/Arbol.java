package logica;

public class Arbol<T> {

  private Nodo raiz;

  public void insertar(T elemento) {
    if (raiz == null)
      raiz = new Nodo(elemento);
    else
      raiz = raiz.insertar(raiz, elemento);
  }

  public Nodo getRaiz() {
    return raiz;
  }

}
