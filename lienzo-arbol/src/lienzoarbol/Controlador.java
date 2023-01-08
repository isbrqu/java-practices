package lienzoarbol;

import logica.Arbol;

public class Controlador {

    private Lienzo objLienzo;
    private Arbol objArbol;

    public Controlador(Lienzo objLienzo, Arbol objArbol) {
        this.objLienzo = objLienzo;
        this.objArbol = objArbol;
    }
    
    public void iniciar() {
        objLienzo.setObjArbol(objArbol);
    }

}
