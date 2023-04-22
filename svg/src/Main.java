import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.lang.Math;

public class Main {

  private static final int NODE_RADIUS = 5;
  // private static final int NODE_SPACING = 50;
  private static final int NODE_FONT_SIZE = NODE_RADIUS + 1;
  private static Document doc;
  private static final String SVG = "http://www.w3.org/2000/svg";
  private static final String output = "out/tree.svg";
  private static Element svg;
  private static Element tree;

  public static void main(String[] args) {
    try {
      doc = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder().newDocument();
      // Crea el elemento raíz del documento
      svg = doc.createElementNS(SVG, "svg");
      svg.setAttribute("width", "1171px");
      svg.setAttribute("height", "829px");
      svg.setAttribute("viewBox", "-0.5 -0.5 1171 829");
      svg.setAttribute("style", "background-color: rgb(42, 42, 42);");
      doc.appendChild(svg);
      // Crea un elemento grupo para contener el árbol
      tree = doc.createElementNS(SVG, "g");
      // tree.setAttribute("transform", "translate(0, 50)");
      svg.appendChild(tree);
      // Dibuja el árbol
      drawTree(3);
      // Crea un transformador para escribir el documento SVG a un archivo
      Transformer transformer = TransformerFactory.newInstance()
        .newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(output);
      transformer.transform(source, result);
    } catch (ParserConfigurationException | TransformerException e) {
      e.printStackTrace();
    }
  }

  // TODO: Averiguar como quitar el valor - 1 de las potencias o que significa
  private static void drawTree(int height) {
      int margin = NODE_RADIUS + 1;
      int x = ((int) Math.pow(2, height) - 1) * NODE_RADIUS * 2 + margin;
      System.out.println(x);
      int y = margin;
      drawTree(1, x, y, height);
  }

  private static void drawTree(int value, int x, int y, int height) {
    // Crea un elemento círculo para representar el nodo
    Element circle = createCircle(x, y);
    tree.appendChild(circle);
    // Crea un elemento texto para mostrar el valor del nodo
    Element text = createText(x, y, value);
    tree.appendChild(text);
    // Si el nodo tiene hijos, dibuja los hijos
    if (height > 0) {
      int spacingX = ((int) Math.pow(2, height - 1)) * NODE_RADIUS * 2;
      int spacingY = NODE_RADIUS * 2 * 2;
      drawTree(value + 1, x - spacingX, y + spacingY, height - 1);
      drawTree(value + 1, x + spacingX, y + spacingY, height - 1);
    }
  }

  private static Element createCircle(int x, int y) {
    Element circle = doc.createElementNS("http://www.w3.org/2000/svg", "circle");
    circle.setAttribute("cx", Integer.toString(x));
    circle.setAttribute("cy", Integer.toString(y));
    circle.setAttribute("r", Integer.toString(NODE_RADIUS));
    circle.setAttribute("fill", "none");
    circle.setAttribute("stroke", "rgb(240, 240, 240)");
    circle.setAttribute("stroke-width", "0.5");
    return circle;
  }

  private static Element createText(int x, int y, int value) {
    Element text = doc.createElementNS("http://www.w3.org/2000/svg", "text");
    text.setAttribute("x", Integer.toString(x));
    text.setAttribute("y", Integer.toString(y + NODE_FONT_SIZE / 2));
    text.setAttribute("text-anchor", "middle");
    text.setAttribute("font-size", Integer.toString(NODE_FONT_SIZE));
    text.setAttribute("fill", "white");
    text.setTextContent(Integer.toString(value));
    return text;
  }

}
