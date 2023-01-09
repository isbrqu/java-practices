import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;

public class Window extends JFrame {

  private JPanel panel;

  public Window() {
    Dimension dimension = new Dimension(500, 500);
    setTitle("My program");
    setSize(dimension);
    setMinimumSize(dimension);
    setLocationRelativeTo(null);
    // getContentPane().setBackground(Color.BLUE);
    // setLocation(100, 200);
    // setBounds(100, 200, 500, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    initComponents();
  }

  private void initComponents() {
    addPanels();
    // addLabels();
    // addButtons();
    addRadioButtons();
  }

  private void addPanels() {
    panel = new JPanel();
    // panel.setBackground(Color.GREEN);
    panel.setLayout(null);
    getContentPane().add(panel);
  }

  private void addLabels() {
    JLabel label = new JLabel();
    label.setText("Patimomo!");
    // label.setBounds(10, 10, 100, 100);
    // label.setForeground(Color.WHITE);
    // label.setBackground(Color.BLACK);
    // label.setOpaque(true);
    // label.setHorizontalAlignment(SwingConstants.CENTER);
    Font font = new Font("Time New Roman", Font.ITALIC, 30);
    label.setFont(font);
    ImageIcon imageIcon = new ImageIcon("momo.jpg");
    Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    imageIcon = new ImageIcon(image);
    JLabel imageLabel = new JLabel();
    imageLabel.setIcon(imageIcon);
    panel.add(label);
    panel.add(imageLabel);
  }

  private void addButtons() {
    // text button
    JButton btn1 = new JButton();
    btn1.setText("HOLA");
    btn1.setBounds(100, 100, 100, 40);
    btn1.setEnabled(true);
    btn1.setMnemonic('a');
    panel.add(btn1);
    // picture button
    JButton btn2 = new JButton();
    // btn2.setBackground(Color.BLUE);
    btn2.setBounds(100, 200, 100, 40);
    ImageIcon imageIcon = new ImageIcon("jojo.jpg");
    Image image = imageIcon.getImage()
      .getScaledInstance(btn2.getWidth(), btn2.getHeight(), Image.SCALE_SMOOTH);
    imageIcon = new ImageIcon(image);
    btn2.setIcon(imageIcon);
    panel.add(btn2);
  }

  private void addRadioButtons() {
    Border border = BorderFactory.createLineBorder(Color.BLACK);
    JRadioButton radioBtn1 = new JRadioButton("Opción 1", true);
    radioBtn1.setBounds(100, 100, 100, 50);
    JRadioButton radioBtn2 = new JRadioButton("Opción 2", false);
    radioBtn2.setBounds(100, 150, 100, 50);
    JRadioButton radioBtn3 = new JRadioButton("Opción 3", false);
    radioBtn3.setBounds(100, 200, 100, 50);
    ButtonGroup radioBtnGroup = new ButtonGroup();
    radioBtnGroup.add(radioBtn1);
    radioBtnGroup.add(radioBtn2);
    radioBtnGroup.add(radioBtn3);
    panel.add(radioBtn1);
    panel.add(radioBtn2);
    panel.add(radioBtn3);
    JLabel test = new JLabel("test");
    test.setBounds(100, 250, 100, 50);
    JPanel panelInterno = new JPanel();
    // Agregamos un borde sólido de 1 pixel de grosor al panel interno
    panelInterno.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    // Establecemos el tamaño y la posición del panel interno
    panelInterno.setBounds(100, 100, 100, 50);

    JRadioButton radioButton = new JRadioButton("Opcion");

    panelInterno.add(radioButton);
    panel.setBorder(border);
  }

}
