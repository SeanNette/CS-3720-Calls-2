package Interface;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * @author Dariusz
 * @version 1.0
 * 
 */
public class CalendarWindow extends Interface{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9013118946938894527L;
	// Attributes
        private String panelName = "Calendar";
	private Container mainPane;
	private JButton mainButton;
	private JButton customerButton;
	private Vector<JPanel> panelList;

	// Constructor
	public CalendarWindow() {

	}
        
        public JPanel window()
        {
            return createPanels(panelName);
        }
        
/*
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
                
                
//		customerButton = new JButton("Settings");
//		customerButton.setBorder(buttonEdge);
//		customerButton.addActionListener(new ButtonListener());
//		buttonPanel.add(customerButton);

		mainPane.add(buttonPanel, BorderLayout.NORTH);
		mainPane.add(panelList.get(0), BorderLayout.CENTER);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
*/
	/*private JPanel createMainPanel() {


		// A border that puts 10 extra pixels at the sides and
		// bottom of each pane.
		Border paneEdge = BorderFactory.createEmptyBorder(0, 10, 10, 10);


		// First pane: simple borders
		JPanel simpleBorders = new JPanel();
		simpleBorders.setBorder(paneEdge);
		simpleBorders.setLayout(new BoxLayout(simpleBorders, BoxLayout.Y_AXIS));

		JPanel mainPanel = new JPanel();

		JLabel mainL = new JLabel("The Calendar Screen", SwingConstants.CENTER);
		mainL.setForeground(Color.blue);
		mainL.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 48));
		mainPanel.add(mainL);

		return mainPanel;
	} */

	/***************************** INNER CLASSES *****************************/

	private class ButtonListener implements ActionListener {
		// Attributes
		private JPanel tp;

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < panelList.size(); i++) {
				tp = panelList.get(i);
				mainPane.remove(tp);
				tp.setVisible(false);
			}

			if (e.getSource() == mainButton) {
				tp = panelList.get(0);
				mainPane.add(tp, BorderLayout.CENTER);
				tp.setVisible(true);

			}

			if (e.getSource() == customerButton) {
				tp = panelList.get(1);
				mainPane.add(tp, BorderLayout.CENTER);
				tp.setVisible(true);

			}
		}

	}
}
