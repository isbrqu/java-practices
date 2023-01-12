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

  private static final float RADIO = 10;
  private static final float DIAMETER = 2 * RADIO;

  public static void main(String[] args) {
    try {
      doc = DocumentBuilderFactory
        .newInstance()
        .newDocumentBuilder()
        .newDocument();
      createSvg();
      createTree();
      draw(4);
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

  private static Element createCircle(float cx, float cy, float r, String color) {
    Element circle = doc.createElementNS(SVG, "circle");
    circle.setAttribute("cx", Float.toString(cx));
    circle.setAttribute("cy", Float.toString(cy));
    circle.setAttribute("r", Float.toString(r));
    circle.setAttribute("fill", color);
    return circle;
  }

  private static void draw(int height) {
    float x = (float) Math.pow(2, height + 1) * RADIO;
    float y = DIAMETER;
    draw(height, x, y, "#fff");
  }

  private static void draw(int height, float x, float y, String color) {
    Element root, left, right;
    root = createCircle(x, y, RADIO, color);
    tree.appendChild(root);
    // if (true) return;
    if (height == 0) return;
    float margin = (float) Math.pow(2, height) * RADIO;
    float x1 = x - margin;
    float y1 = y;
    draw(height - 1, x1, y1, "#f00");
    float x2 = x + margin;
    float y2 = y;
    draw(height - 1, x2, y2, "#00f");
  }

  private static float calc(float h, float x2, float x1, float y2) {
    return ((float) Math.sqrt(Math.pow(h, 2) - Math.pow(x2 - x1, 2))) + y2;
  }

}
