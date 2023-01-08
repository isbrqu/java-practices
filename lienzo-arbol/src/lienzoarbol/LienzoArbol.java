package lienzoarbol;

import javax.swing.JFrame;
import logica.Arbol;

public class LienzoArbol {

  public static void main(String[] args) {
    Arbol objArbol = new Arbol();
    Lienzo objLienzo = new Lienzo();
    Controlador objControlador = new Controlador(objLienzo, objArbol);
    MyTest test = new MyTest();

    objArbol.insertar(7);
    objArbol.insertar(2);
    objArbol.insertar(9);
    objArbol.insertar(5);
    objArbol.insertar(0);
    objArbol.insertar(10);
    objControlador.iniciar();

    JFrame window = new JFrame();
    window.getContentPane().add(test);
    window.getContentPane().add(objLienzo);
    window.setDefaultCloseOperation(3);
    window.setSize(600, 600);
    window.setVisible(true);
  }

}
