
Element child = (Element) tree.getLastChild().getPreviousSibling();
int width = Integer.parseInt(child.getAttribute("cx")) + NODE_RADIUS;
int height = Integer.parseInt(child.getAttribute("cy")) + NODE_RADIUS;
String viewBox = "0 0" + Integer.toString(width / 2) + " " + Integer.toString(height / 2);
svg.setAttribute("width", Integer.toString(width));
svg.setAttribute("height", Integer.toString(height));
// svg.setAttribute("viewBox", viewBox);
