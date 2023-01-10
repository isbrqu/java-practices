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

  private static Document doc;
  private static Element svg;
  private static Element tree;
  private static final String SVG = "http://www.w3.org/2000/svg";
  private static final String output = "out/tree.svg";

  public static void main(String[] args) {
    try {
      doc = DocumentBuilderFactory
        .newInstance()
        .newDocumentBuilder()
        .newDocument();
      createSvg();
      createTree();
      draw();
      Transformer transformer = TransformerFactory
        .newInstance()
        .newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(output);
      transformer.transform(source, result);
    } catch (ParserConfigurationException 
        | TransformerException e) {
      e.printStackTrace();
    }
  }

  private static void createSvg() {
    svg = doc.createElementNS(SVG, "svg");
    svg.setAttribute("style", "background-color: rgb(42, 42, 42);");
    doc.appendChild(svg);
  }
  
  private static void createTree() {
    tree = doc.createElementNS(SVG, "g");
    svg.appendChild(tree);
  }

  private static Element createCircle(float cx, float cy, float r) {
    Element circle = doc.createElementNS(SVG, "circle");
    circle.setAttribute("cx", Float.toString(cx));
    circle.setAttribute("cy", Float.toString(cy));
    circle.setAttribute("r", Float.toString(r));
    circle.setAttribute("fill", "white");
    return circle;
  }

  private static void draw() {
      Element a, b, c;
      float RADIO = 10;
      float DIAMETER = 2 * RADIO;
      float DISTANCE = 2 * DIAMETER;
      float y;
      float x2 = DISTANCE, y2 = DIAMETER;
      float x1 = x2 - RADIO * 2;
      float x3 = x2 + RADIO * 2;
      a = createCircle(x2, y2, RADIO);
      y = calc(DISTANCE, x2, x1, y2);
      b = createCircle(x1, y, RADIO);
      c = createCircle(x3, y, RADIO);
      tree.appendChild(a);
      tree.appendChild(b);
      tree.appendChild(c);
  }

  private static float calc(float h, float x2, float x1, float y2) {
    return ((float) Math.sqrt(Math.pow(h, 2) - Math.pow(x2 - x1, 2))) + y2;
  }

}
