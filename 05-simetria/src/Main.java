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

  private static final float RADIO = 1;
  private static final float DIAMETER = 2 * RADIO;
  private static final int HEIGHT = 4;

  public static void main(String[] args) {
    try {
      doc = DocumentBuilderFactory
        .newInstance()
        .newDocumentBuilder()
        .newDocument();
      createSvg();
      createTree();
      draw(HEIGHT);
      String cy = ((Element) tree.getLastChild()).getAttribute("cy");
      String cx = ((Element) tree.getLastChild()).getAttribute("cx");
      String minX = "0", minY = "0", width, height;
      width = Float.toString(Float.parseFloat(cx) + DIAMETER);
      height = Float.toString(Float.parseFloat(cy) + DIAMETER);
      String viewBox = minX + " " + minY + " " + width + " " + height;
      svg.setAttribute("viewBox", viewBox);
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
    draw(height, x, y);
  }

  private static void draw(int height, float x, float y) {
    String color = "#fff";
    Element root, left, right;
    root = createCircle(x, y, RADIO, color);
    tree.appendChild(root);
    if (height == 0) return;
    float margin = (float) Math.pow(2, height) * RADIO;
    float x1 = x - margin;
    float y1 = calculateY(height, x1, x, y);
    draw(height - 1, x1, y1);
    float x2 = x + margin;
    float y2 = calculateY(height, x2, x, y);
    draw(height - 1, x2, y2);
  }
  
  private static float calculateY(float h, float x1, float x2, float y2) {
    float hip = (float) Math.pow(2, h) * DIAMETER;
    float hip2 = (float) Math.pow(hip, 2);
    float cato = (float) Math.pow(x1 - x2, 2);
    float sqrt = (float) Math.sqrt(hip2 - cato);
    float y1 = sqrt + y2;
    return y1;
  }

}
