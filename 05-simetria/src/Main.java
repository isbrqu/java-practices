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
      Element element;
      doc = DocumentBuilderFactory
        .newInstance()
        .newDocumentBuilder()
        .newDocument();
      createSvg();
      createTree();
      element = createCircle(1, 1, 1);
      tree.appendChild(element);
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

  private static Element createCircle(int cx, int cy, int r) {
    Element circle = doc.createElementNS(SVG, "circle");
    circle.setAttribute("cx", Integer.toString(cx));
    circle.setAttribute("cy", Integer.toString(cy));
    circle.setAttribute("r", Integer.toString(r));
    circle.setAttribute("fill", "white");
    // circle.setAttribute("stroke", "rgb(240, 240, 240)");
    // circle.setAttribute("stroke-width", "0.5");
    return circle;
  }

}
