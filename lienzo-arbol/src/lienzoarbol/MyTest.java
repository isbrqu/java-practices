package lienzoarbol;

import java.awt.Graphics;
import javax.swing.JPanel;

public class MyTest extends JPanel {

  // public static final int DIAMETRO = 30;
  // public static final int RADIO = DIAMETRO / 2;
  // public static final int ANCHO = 50;

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    graphics.drawOval(0, 0, 10, 10);
  }

}
