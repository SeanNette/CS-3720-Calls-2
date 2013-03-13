/**
 * Created on April 10, 2009
 *
 * Project: assignment2BaseGUI
 */
package Interface;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

//Import Exceptions

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.*;
import Controller.PhysicianController;

/**
 * @author dwatson
 * @version 1.0
 * 
 */
public class PhysicianWindow extends Interface{
	// Attribute

	// Fields
        private String panelName = "Physician";
	private JTextField clientid_TF;
	private JTextField firstname_TF;
	private JTextField lastname_TF;
	private JTextField address_TF;
	private JTextField birthdate_TF;
        private JTextField startdate_TF;
        private JTextField enddate_TF;
	private JTextField phonenumber_TF;
	// Buttons
	private JButton clearB;
	private JButton addB;
	private JButton delB;
	private JButton clearSearchB;
	// Radio Buttons
	private JRadioButton clientIdRB;
	private JRadioButton lastNameRB;
	private JRadioButton phoneNumberRB;
	// Else
	private Vector<?> cVector;
	private JList cList;
	//private ArrayList<Customer> customerArray;
	private JScrollPane customerSP;
	private ButtonGroup bgroup;

	// Constructor
	public PhysicianWindow() {
		// default constructor
            createPanels(panelName);
	}

	// Operational Methods
	/*
	 * Create a customer windows that will Search Edit and Delete the data from
	 * Array.
	 * 
	 * @return When active, returns the data to the user.
	 */
	/*public JPanel createCustomerPanel() {
		JPanel customerPanel = new JPanel(new BorderLayout());
		Border panelEdge = BorderFactory.createRaisedBevelBorder();
		JPanel tempPanel = new JPanel();

		JLabel customerL = new JLabel("The Customer Screen",
				SwingConstants.CENTER);
		customerL.setForeground(Color.red);
		customerL.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 48));

		tempPanel.add(customerL);
		tempPanel.setBorder(panelEdge);

		customerPanel.add(tempPanel, BorderLayout.NORTH);
		customerPanel.add(customerLowerPanel(), BorderLayout.CENTER);
		customerPanel.setBorder(panelEdge);

		return customerPanel;

	} */
        

	/****** INNTER CLASSES ******/
	/**
	 * Create the lower part of GUI, which is divided in two panels.
	 * 
	 * @return shows the lower panel.
	 */

	private JPanel customerLowerPanel() {
		JPanel customerLowerPanel = new JPanel(new GridLayout(1, 2));

		customerLowerPanel.add(customerLeftPanel());
		customerLowerPanel.add(customerRightPanel());

		return customerLowerPanel;
	}

	/**
	 * Create the left panel of GUI. Divide it in two parts
	 * 
	 * @returns return the left panel.
	 */

	private JPanel customerLeftPanel() {
		JPanel customerLeft = new JPanel();

		customerLeft.setLayout(new GridLayout(2, 1));
	//	customerLeft.add(customerLeftUpperPanel());
		customerLeft.add(customerLeftLowerPanel());

		return customerLeft;
	}

	/**
	 * Create the left upper panel of GUI.
	 * 
	 * @returns return the left upper panel with a customerSearch method.
	 */
/*
	private JPanel customerLeftUpperPanel() {
		JPanel customerLeftUpperPanel = new JPanel(new BorderLayout());

		Border panelEdge = BorderFactory.createEtchedBorder();

		customerLeftUpperPanel.setBorder(panelEdge);
		customerLeftUpperPanel.add(customerSearch(), BorderLayout.CENTER);

		return customerLeftUpperPanel;
	}*/

	/**
	 * Creates left lower panel. Displays the search result.
	 * 
	 * @return returns the lower left panel as well search window.
	 */

	private JPanel customerLeftLowerPanel() {
		JPanel customerLeftLowerPanel = new JPanel();

		Border panelEdge = BorderFactory.createEtchedBorder();

		customerLeftLowerPanel.setBorder(panelEdge);

		cList = new JList(); // Create a cList window
		String width = "1234567890123456789012345678901234567890";
		cList.setPrototypeCellValue(width);
		cList.setFont(new Font("Calibri", Font.PLAIN, 11));
		cList.setVisibleRowCount(12); // change
		//cList.addListSelectionListener(new ListListener());
		customerSP = new JScrollPane(cList);
		cVector = new Vector();

		customerLeftLowerPanel.add(customerSP);

		return customerLeftLowerPanel;
	}

	/**
	 * Create the search panel as well as three different radio listeners Radio
	 * listeners will search for either client ID, phone number or last name
	 * 
	 * @return returns the information about the customer
	 */

	private JPanel customerSearch() {
		JPanel customerSearchPanel = new JPanel(new GridLayout(5, 1)); // divide
		// the
		// window

		JLabel customerSearchLabel = new JLabel(
				"Select the type of search that " + "you would like to perfom",
				SwingConstants.CENTER);

		customerSearchPanel.add(customerSearchLabel); // add the created
		// SearchLabel

		bgroup = new ButtonGroup();

		// row1 - column 1
		clientIdRB = new JRadioButton("Client ID"); // First radio button -
		// assign name
		//clientIdRB.addActionListener(new RadioButtonListener()); // assign the
		// action to
		// the Radio
		// Button
		bgroup.add(clientIdRB); // group
		customerSearchPanel.add(clientIdRB); // place in a right panel
		// row2 - column 1
		lastNameRB = new JRadioButton("Last Name");
		//lastNameRB.addActionListener(new RadioButtonListener());
		bgroup.add(lastNameRB);
		customerSearchPanel.add(lastNameRB);
		// row3 - column 1
		phoneNumberRB = new JRadioButton("Phone Number");
		//phoneNumberRB.addActionListener(new RadioButtonListener());
		bgroup.add(phoneNumberRB);
		customerSearchPanel.add(phoneNumberRB);

		// row1 - column 2 - create a Clear Button
		JPanel row2r = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		clearSearchB = new JButton("Clear");
		clearSearchB.setBorder(buttonEdge);
		clearSearchB.addActionListener(new ClearSearchButtonListener());
		row2r.add(clearSearchB);
		customerSearchPanel.add(row2r);

		return customerSearchPanel;
	}

	/**
	 * Create the right panel. with border layout of 10 and 10
	 * 
	 * @return returns right panel for GUI.
	 */
	private JPanel customerRightPanel() {
		JPanel customerRightPanel = new JPanel(new BorderLayout(10, 10));
		Border panelEdge = BorderFactory.createEtchedBorder();
		JLabel customerRightLabel = new JLabel("Cilent Information",
				SwingConstants.CENTER);

		customerRightPanel.add(customerRightLabel, BorderLayout.NORTH);
		customerRightPanel.add(customerInformationPanel(), BorderLayout.CENTER);
		customerRightPanel.add(customerInformationPanelButtons(),
				BorderLayout.SOUTH);
		customerRightPanel.setBorder(panelEdge);

		return customerRightPanel;
	}

	/**
	 * Create the information panel. The panel will display the ID Number, first
	 * name, last name, address, postal code, phone number and the Client Type.
	 * 
	 * @return returns the customer panel
	 */

	private JPanel customerInformationPanel() {
		JPanel customerInfoPanel = new JPanel(new GridLayout(7, 2));

		// Create a text field with text box called "ClientId"
		JPanel customerIdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel clientId = new JLabel("Client ID: ", SwingConstants.RIGHT);
		customerIdPanel.add(clientId);
		customerInfoPanel.add(customerIdPanel);
		JPanel clientIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		clientid_TF = new JTextField(16);
		clientid_TF.setEditable(false);
		clientIdPanel.add(clientid_TF);
		customerInfoPanel.add(clientIdPanel);

		// Create a text field with text box called "First Name"
		JPanel customerFNPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // FirstName
		// =
		// FN
		JLabel firstName = new JLabel("First Name: ", SwingConstants.RIGHT);
		customerFNPanel.add(firstName);
		customerInfoPanel.add(customerFNPanel);
		JPanel firstNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		firstname_TF = new JTextField(16);
		firstNamePanel.add(firstname_TF);
		customerInfoPanel.add(firstNamePanel);

		// Create a text field with text box called "Last Name"
		JPanel customerLNPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // LastName
		// =
		// LN
		JLabel lastName = new JLabel("Last Name: ", SwingConstants.RIGHT);
		customerLNPanel.add(lastName);
		customerInfoPanel.add(customerLNPanel);
		JPanel lastNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lastname_TF = new JTextField(16);
		lastNamePanel.add(lastname_TF);
		customerInfoPanel.add(lastNamePanel);

                // Create a text field with text box called "Address"
		JPanel customerAddressPanel = new JPanel(new FlowLayout(
				FlowLayout.RIGHT));
		JLabel address = new JLabel("Address: ", SwingConstants.RIGHT);
		customerAddressPanel.add(address);
		customerInfoPanel.add(customerAddressPanel);
		JPanel addressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		address_TF = new JTextField(16);
		addressPanel.add(address_TF);
		customerInfoPanel.add(addressPanel);
                
                // Create a text field with text box called "Address"
		JPanel customerStartDatePanel = new JPanel(new FlowLayout(
				FlowLayout.RIGHT));
		JLabel sDate = new JLabel("Start Date: ", SwingConstants.RIGHT);
		customerStartDatePanel.add(sDate);
		customerInfoPanel.add(customerStartDatePanel);
		JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		startdate_TF = new JTextField(16);
		datePanel.add(startdate_TF);
		customerInfoPanel.add(datePanel);

		// Create a text field with text box called "Postal Code"
		JPanel customerPCPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Postal
		// Code
		// =
		// PC
		JLabel birthDate = new JLabel("Birth Date: ", SwingConstants.RIGHT);
		customerPCPanel.add(birthDate);
		customerInfoPanel.add(customerPCPanel);
		JPanel porstalCodePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		birthdate_TF = new JTextField(16);
		porstalCodePanel.add(birthdate_TF);
		customerInfoPanel.add(porstalCodePanel);

		// Create a text field with text box called "Phone Number"
		JPanel customerPNPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Phone
		// Number
		// =
		// PN
		JLabel phone_number = new JLabel("Phone Number: ", SwingConstants.RIGHT);
		customerPNPanel.add(phone_number);
		customerInfoPanel.add(customerPNPanel);
		JPanel phoneNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		phonenumber_TF = new JTextField(16);
		phoneNumberPanel.add(phonenumber_TF);
		customerInfoPanel.add(phoneNumberPanel);

		return customerInfoPanel; // finally, return the text fields

	}

	/**
	 * Create the buttons for GUI. Add, Clear, Delete.
	 * 
	 * @return returns the buttons
	 */
	private JPanel customerInformationPanelButtons() {
		// Preferences
		JPanel customerInformationPanelButtons = new JPanel(new FlowLayout(
				FlowLayout.CENTER));
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();

		// Add Button
		addB = new JButton("Submit/Add");
		addB.setBorder(buttonEdge);
		addB.addActionListener(new AddButtonListener());
		customerInformationPanelButtons.add(addB);
		// Delete Button
		delB = new JButton("Delete");
		delB.setBorder(buttonEdge);
	//	delB.addActionListener(new DeleteButtonListener());
		customerInformationPanelButtons.add(delB);
		// Clear Button
		clearB = new JButton("Clear");
		clearB.setBorder(buttonEdge);
	//	clearB.addActionListener(new ClearButtonListener());
		customerInformationPanelButtons.add(clearB);

		return customerInformationPanelButtons;

	}

	/******************** ACTION LISTERNERS ********************/
	/**
	 * This is a class for radio buttons. It responses when you click the radio
	 * buttons.
	 */
/*	private class RadioButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == clientIdRB) {
				String input = JOptionPane
						.showInputDialog("Enter Client ID Number: ");
				CustomerBroker cb = null;
				try {
					cb = CustomerBroker.getBroker();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "File Not Found!");
				}

				customerArray = (ArrayList) cb.search(input, "id");
				Vector<String> vector = new Vector<String>();

				if (customerArray.isEmpty()) {
					JOptionPane
							.showMessageDialog(null, "Cannot find the data!");
					System.out.println("Cannot find.");
				} else {
					for (Customer c : customerArray) {
						vector.add(c.getId() + " " + c.getFirstName() + " "
								+ c.getLastName() + " " + c.getPhoneNumber());
						cList.setListData(vector);
					}
				}
			} else if (e.getSource() == lastNameRB) {
				String input = JOptionPane.showInputDialog("Enter Last Name: ");
				CustomerBroker cb = null;
				try {
					cb = CustomerBroker.getBroker();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "");
				}
				Vector<String> vector;
				customerArray = (ArrayList) cb.search(input, "name");
				vector = new Vector<String>();

				if (customerArray.isEmpty()) {
					JOptionPane
							.showMessageDialog(null, "Cannot find the data!");
					System.out.println("Cannot find.");
				} else {
					for (Customer c : customerArray) {
						vector.add(c.getId() + " " + c.getFirstName() + " "
								+ c.getLastName() + " " + c.getPhoneNumber());
						cList.setListData(vector);
					}
				}
			} else if (e.getSource() == phoneNumberRB) {
				String input = JOptionPane
						.showInputDialog("Enter Phone Number: (xxx-yyy-zzzz)");
				CustomerBroker cb = null;
				try {
					cb = CustomerBroker.getBroker();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "File Not Found!");
				}
				customerArray = (ArrayList) cb.search(input, "phone");
				Vector<String> vector = new Vector<String>();

				if (customerArray.isEmpty()) {
					JOptionPane
							.showMessageDialog(null, "Cannot find the data!");
					System.out.println("Cannot find.");
				} else {
					for (Customer c : customerArray) {
						vector.add(c.getId() + " " + c.getFirstName() + " "
								+ c.getLastName() + " " + c.getPhoneNumber());
						cList.setListData(vector);
					}
				}
				// cb.closeBroker(); // for test
			}
		}
	}

	/**
	 * This is a class for clear button on the right panel. It responses when
	 * you click "Clear" button, clearing the content in the right panel.
	 */
	private class ClearSearchButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == clearSearchB) {
				cList.setListData(cVector);
			}

		}
	}

	/**
	 * This is a class for deleting information. It responses when you click
	 * "Delete" button - deletes the information
	 */
/*	private class DeleteButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == delB) {
				CustomerBroker cb = null;
				try {
					cb = CustomerBroker.getBroker();
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String cid = clientid_TF.getText();
				String fname = firstname_TF.getText();
				String lname = lastname_TF.getText();
				String address = address_TF.getText();
				String pcode = postalcode_TF.getText();
				String phone = phonenumber_TF.getText();

				try {
					Customer c = new Customer(Long.parseLong(cid), fname,
							lname, address, pcode, phone);
					if (cb.remove(c)) {
						System.out.println("deleted customer");
						JOptionPane.showMessageDialog(null,
								"Deleted succefully.");
					} else {
						System.out.println("didn't delete customer");
					}
				} catch (InvalidPhoneNumberException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidPostalCodeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cb.closeBroker();
			}
			clientid_TF.setText(null);
			firstname_TF.setText(null);
			lastname_TF.setText(null);
			address_TF.setText(null);
			postalcode_TF.setText(null);
			phonenumber_TF.setText(null);
		}
	}

	/**
	 * This is a class for modifying existing information or adding new one. It
	 * responses when you click this button, updating the information.
	 */
	private class AddButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
                    
			PhysicianController phys = new PhysicianController();
                        
			if (e.getSource() == addB) {
				// long cid = 0;
				//String cid = clientid_TF.getText();
				String fname = firstname_TF.getText();
				String lname = lastname_TF.getText();
                                String bdate = birthdate_TF.getText();
//                                String sdate = startdate_TF.getText();
 //                               String edate = enddate_TF.getText();
				String address = address_TF.getText();
				String phone = phonenumber_TF.getText();
                                int choice = 1;
                                
                 //      boolean didit = phys.workPhysician(choice,fname, lname, bdate, "2011-01-01", "2011-01-01", address, phone);
//                        System.out.print(didit);
                        
                        }
                }
        }
}

				// customerArray = (ArrayList) cb.search(client_idTF.getText(),
				// "id");

			/*	if (!(fname == null || lname == null || address == null
						|| pcode == null || phone == null)) {
					Customer c = null;
					try {
						// c = new
						// Customer(Integer.parseInt(cid),fname,lname,address,pcode,phone);
						// System.out.println(Integer.parseInt(cid));
						if (cid.equalsIgnoreCase("")) {
							c = new Customer(0, fname, lname, address, pcode,
									phone);
						} else {
							c = new Customer(Long.parseLong(cid), fname, lname,
									address, pcode, phone);
						}
						if (cb.persist(c)) {
							JOptionPane.showMessageDialog(null,
									"Added customer succefully.");
							System.out.println("added customer");
						} else {
							JOptionPane.showMessageDialog(null,
									"Didn't add customer.");
							System.out.println("didn't add customer");
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidPhoneNumberException e1) {
						// phone =
						// JOptionPane.showInputDialog("This is wrong number. Please check again.");
						JOptionPane
								.showMessageDialog(null,
										"The phone number is wrong. Please check again.");
					} catch (InvalidPostalCodeException e1) {
						// pcode =
						// JOptionPane.showInputDialog("This is wrong number. Please check again.");
						JOptionPane
								.showMessageDialog(null,
										"The postal code is wrong. Please check again.");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Check it out! You have empty item(s).");
					System.out.println("Check it out! You have empty item(s).");
				}
			}
		}
	}

	/**
	 * This is a class for clear button on the right panel. It responses when
	 * you click "Clear" button, clearing the search box.
	 */
/*	private class ClearButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == clearB) {
				clientid_TF.setText(null);
				firstname_TF.setText(null);
				lastname_TF.setText(null);
				address_TF.setText(null);
				postalcode_TF.setText(null);
				phonenumber_TF.setText(null);
				cList.setListData(cVector);
			}
		}
	}

	/**
	 * This is a class for showing the information regarding a game to right
	 * panel.
	 */
/*	public class ListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			{
				int index = cList.getSelectedIndex();
				if (index >= 0) {
					Customer cust = customerArray.get(index);
					clientid_TF.setText(Long.toString(cust.getId()));
					firstname_TF.setText(cust.getFirstName());
					lastname_TF.setText(cust.getLastName());
					address_TF.setText(cust.getAddress());
					postalcode_TF.setText(cust.getPostalCode());
					phonenumber_TF.setText(cust.getPhoneNumber());
				}
			}
		}
	*/
