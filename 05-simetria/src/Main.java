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
import java.util.HashMap;

public class Main {

  private static Document doc;
  private static Element svg;
  private static Element tree;
  private static final String SVG = "http://www.w3.org/2000/svg";
  private static final String output = "out/tree.svg";

  private static final float RADIO = .5f;
  private static final float DIAMETER = 2 * RADIO;
  private static final int HEIGHT = 3;

  public static void
  main(String[] args) {
    try {
      doc = DocumentBuilderFactory
        .newInstance()
        .newDocumentBuilder()
        .newDocument();
      createSvg();
      createTree();
      float x = (float) Math.pow(2, HEIGHT + 1) * RADIO;
      float y = DIAMETER;
      HashMap<String,String> coordinates
        = calculateCoordinates(HEIGHT, x, y);
      calculateCoordinates(HEIGHT, x, y);
      draw(HEIGHT, x, y);
      Element node = (Element) tree.getLastChild().getPreviousSibling();
      String cx = coordinates.get("cx");
      String cy = coordinates.get("cy");
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

  private static void
  createSvg() {
    svg = doc.createElementNS(SVG, "svg");
    svg.setAttribute("style", "background-color: rgb(42, 42, 42);");
    doc.appendChild(svg);
  }
  
  private static void
  createTree() {
    tree = doc.createElementNS(SVG, "g");
    svg.appendChild(tree);
  }

  private static Element
  createCircle(float cx, float cy, float r, String color) {
    Element circle = doc.createElementNS(SVG, "circle");
    circle.setAttribute("cx", Float.toString(cx));
    circle.setAttribute("cy", Float.toString(cy));
    circle.setAttribute("r", Float.toString(r));
    circle.setAttribute("fill", color);
    return circle;
  }

  private static Element
  createText(float cx, float cy, int num) {
    String color = "#000";
    String fontSize = Float.toString(RADIO + RADIO / 2);
    Element text = doc.createElementNS(SVG, "text");
    text.setTextContent(Integer.toString(num));
    text.setAttribute("x", Float.toString(cx));
    text.setAttribute("y", Float.toString(cy + RADIO / 2 ));
    text.setAttribute("text-anchor", "middle");
    // text.setAttribute("alignment-baseline", "middle");
    text.setAttribute("font-size", fontSize);
    text.setAttribute("fill", color);
    return text;
  }

  private static void
  draw(int height, float x, float y) {
    String color = "#fff";
    Element root = createCircle(x, y, RADIO, color);
    System.out.println("height: " + height);
    Element text = createText(x, y, height);
    tree.appendChild(root);
    tree.appendChild(text);
    if (height == 0) return;
    float margin = (float) Math.pow(2, height) * RADIO;
    float x1 = x - margin;
    float y1 = calculateY(height, x1, x, y);
    draw(height - 1, x1, y1);
    float x2 = x + margin;
    float y2 = calculateY(height, x2, x, y);
    draw(height - 1, x2, y2);
  }
  
  private static float
  calculateY(float h, float x1, float x2, float y2) {
    float hypotenuse = (float) Math.pow(2, h) * DIAMETER;
    float opposite = x1 - x2;
    float adjacent = calculateLeg(hypotenuse, opposite);
    float y1 = adjacent + y2;
    return y1;
  }

  private static float
  calculateLeg(float hypotenuse, float leg) {
    float hypotenuse2 = (float) Math.pow(hypotenuse, 2);
    float leg2 = (float) Math.pow(leg, 2);
    float result = (float) Math.sqrt(hypotenuse2 - leg2);
    return result;
  }

  private static HashMap
  calculateCoordinates(float height, float x2, float y2) {
    HashMap<String,String> coordinates = new HashMap<String,String>();
    float nextPower = (float) Math.pow(2, height + 1);
    float x1 = (nextPower - 1) * DIAMETER;
    float hypotenuse = (nextPower - 2) * DIAMETER;
    float opposite = x1 - x2;
    float adjacent = calculateLeg(hypotenuse, opposite);
    float y1 = adjacent + y2;
    coordinates.put("cx", Float.toString(x1));
    coordinates.put("cy", Float.toString(y1));
    return coordinates;
  }

  public static Element
  createLine(float x1, float y1, float x2, float y2) {
    Element line = doc.createElementNS(SVG, "line");
    line.setAttribute("x1", Float.toString(x1));
    line.setAttribute("y1", Float.toString(y1));
    line.setAttribute("x2", Float.toString(x2));
    line.setAttribute("y2", Float.toString(y2));
    line.setAttribute("stroke", "#000");
    line.setAttribute("stroke-width", "1");
    return line;
  }

}
