import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import css.CSSStyle;

public class Main {
  public static void main(String[] args) {
    try {
      Document document = DocumentBuilderFactory
        .newInstance()
        .newDocumentBuilder()
        .newDocument();
      String URI = "http://www.w3.org/1999/xhtml";
      Element html = document.createElementNS(URI, "html");
      Element head = document.createElement("head");
      Element style = document.createElement("style");
      Element body = document.createElement("body");
      Element name = document.createElement("h1");
      CSSStyle css = new CSSStyle("circle");
      css.addProperty("fill", "#000");
      style.setTextContent(css.toString());
      head.appendChild(style);
      URI = "http://www.w3.org/2000/svg";
      Element svg = document.createElementNS(URI, "svg");
      Element circle = document.createElement("circle");
      Element g = document.createElement("g");
      circle.setAttribute("r", "10");
      // circle.setAttribute("fill", "#000");
      g.appendChild(circle);
      svg.appendChild(g);
      name.setTextContent("Hola amigo");
      body.appendChild(name);
      body.appendChild(svg);
      html.appendChild(head);
      html.appendChild(body);
      document.appendChild(html);
      DOMSource source = new DOMSource(document);
      Transformer transformer = TransformerFactory
        .newInstance()
        .newTransformer();
      String filename = "out/documento.html";
      StreamResult result = new StreamResult(filename);
      transformer.transform(source, result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
