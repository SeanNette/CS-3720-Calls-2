/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author Dariusz
 */
public class Interface extends JFrame {

    private static final long serialVersionUID = -9013118946938894527L;
    // Attributes
    private Container mainPane;
    private JButton mainButton;
    private JButton customerButton;
    CalendarWindow cw = new CalendarWindow();
  //  private Vector<JPanel> panelList;

    public Interface() {
   //     panelList = new Vector<JPanel>(4);
        
   //     panelList.add
        // fill panel list with JPanels associated with each button
        //	JPanel tempPanel = createMainPanel();
        //	panelList.add(tempPanel);
        
    //    JPanel tempPanel = createPanel("");
    //    panelList.add(tempPanel);
    }

    public void createWindow() {

        this.setBounds(200, 100, 800, 600); // (how far from - right,top, width,
        // how tall)
        this.setTitle("Calls Project");

        mainPane = this.getContentPane();
        mainPane.setLayout(new BorderLayout(5, 5));

        // Panel for the buttons located in the north section
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        Border buttonEdge = BorderFactory.createRaisedBevelBorder();

        // button for main window
        mainButton = new JButton("Calendar");
        mainButton.setBorder(buttonEdge);
        //	mainButton.addActionListener(new Interface.ButtonListener());
        buttonPanel.add(mainButton);

        // button for Customer window
        customerButton = new JButton("Physician");
        customerButton.setBorder(buttonEdge);
        //	customerButton.addActionListener(new Interface.ButtonListener());
        buttonPanel.add(customerButton);


        customerButton = new JButton("Settings");
        customerButton.setBorder(buttonEdge);
        //	customerButton.addActionListener(new ButtonListener());
        buttonPanel.add(customerButton);

        mainPane.add(buttonPanel, BorderLayout.NORTH);
        mainPane.add(cw.window(), BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JPanel createPanels(String panelName) {
        JPanel panel = new JPanel(new BorderLayout());
        Border panelEdge = BorderFactory.createRaisedBevelBorder();
        JPanel tempPanel = new JPanel();

        JLabel title = new JLabel("The" + panelName + " Screen",
                SwingConstants.CENTER);
        title.setForeground(Color.red);
        title.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 48));

        tempPanel.add(title);
        tempPanel.setBorder(panelEdge);

        /*customerPanel.add(tempPanel, BorderLayout.NORTH);
         customerPanel.add(leftPanel(), BorderLayout.CENTER);
         customerPanel.setBorder(panelEdge); */

        return panel;


    }
}
