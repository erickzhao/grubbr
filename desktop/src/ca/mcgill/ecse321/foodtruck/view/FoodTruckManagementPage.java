package ca.mcgill.ecse321.foodtruck.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import ca.mcgill.ecse321.foodtruck.controller.FoodTruckController;
import ca.mcgill.ecse321.foodtruck.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruck.model.Equipment;
import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.model.MenuItem;
import ca.mcgill.ecse321.foodtruck.model.Supply;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class FoodTruckManagementPage {

	private JFrame frame;
	private JTextField txtItemName;
	private JTextField txtItemPrice;
	private JLabel lblMenu;
	private JTextField txtSupplyName;
	private JTextField txtSupplyQty;
	private JLabel lblSupplies;
	private JComboBox<String> supplyList;
	private JTextField txtEquipmentName;
	private JTextField txtEquipmentQty;
	private JLabel lblEquipment;
	private JComboBox<String> equipmentList;
	
	//data elements
	private String error = null;
	private Integer selectedSupply = -1;
	private HashMap<Integer, Supply> supplies;
	private Integer selectedEquipment = -1;
	private HashMap<Integer, Equipment> equipment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodTruckManagementPage window = new FoodTruckManagementPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FoodTruckManagementPage() {
		initialize();
		refreshData();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 469, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JSplitPane menuPane = new JSplitPane();
		tabbedPane.addTab("Menu", null, menuPane, null);
		
		JPanel panel = new JPanel();
		menuPane.setLeftComponent(panel);
		
		JLabel lblItemName = new JLabel("Item Name");
		
		txtItemName = new JTextField();
		txtItemName.setColumns(10);
		
		JLabel lblItemPrice = new JLabel("Item Price");
		
		txtItemPrice = new JTextField();
		txtItemPrice.setColumns(10);
		
		JButton btnAddToMenu = new JButton("Add to menu");
		btnAddToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMenuItemButtonActionPerformed(e);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblItemName)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtItemName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblItemPrice)
							.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
							.addComponent(txtItemPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAddToMenu))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtItemName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblItemName))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblItemPrice)
						.addComponent(txtItemPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnAddToMenu)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		menuPane.setRightComponent(scrollPane);
		
		lblMenu = new JLabel("");
		scrollPane.setViewportView(lblMenu);
		
		JSplitPane supplyPane = new JSplitPane();
		tabbedPane.addTab("Supplies", null, supplyPane, null);
		
		JPanel panel_1 = new JPanel();
		supplyPane.setLeftComponent(panel_1);
		
		JLabel lblAddSupply = new JLabel("Add Supply");
		
		JLabel lblEditSupply = new JLabel("Edit Quantity");
		
		JButton btnAddSupply = new JButton("Add");
		btnAddSupply.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addSupplyButtonActionPerformed(evt);
			}
		});
		
		JLabel lblSupplyName = new JLabel("Name");
		
		txtSupplyName = new JTextField();
		txtSupplyName.setColumns(10);
		
		JLabel lblSupplyQty = new JLabel("Quantity");
		
		txtSupplyQty = new JTextField();
		txtSupplyQty.setColumns(10);
		
		JButton btnEditSupply = new JButton("Edit");
		btnEditSupply.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				editSupplyButtonActionPerformed(evt);
			}
		});
		
		supplyList = new JComboBox<String>();
		supplyList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSupply = cb.getSelectedIndex();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addComponent(lblAddSupply)
									.addComponent(lblEditSupply))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(lblSupplyQty)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtSupplyQty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addComponent(btnEditSupply, Alignment.TRAILING))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnAddSupply)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(lblSupplyName)
										.addGap(18)
										.addComponent(txtSupplyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addComponent(supplyList, Alignment.TRAILING, 0, 196, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(21)
					.addComponent(lblAddSupply)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupplyName)
						.addComponent(txtSupplyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddSupply)
					.addGap(18)
					.addComponent(lblEditSupply)
					.addGap(5)
					.addComponent(supplyList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupplyQty)
						.addComponent(txtSupplyQty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEditSupply)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		supplyPane.setRightComponent(scrollPane_2);
		
		lblSupplies = new JLabel("<html>");
		scrollPane_2.setViewportView(lblSupplies);
		
		JSplitPane equipmentPane = new JSplitPane();
		tabbedPane.addTab("Equipment", null, equipmentPane, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		equipmentPane.setRightComponent(scrollPane_1);
		
		lblEquipment = new JLabel("<html>");
		scrollPane_1.setRowHeaderView(lblEquipment);
		
		JPanel panel_2 = new JPanel();
		equipmentPane.setLeftComponent(panel_2);
		
		JLabel lblAddEquipment = new JLabel("Add Equipment");
		
		JLabel lblEquipmentName = new JLabel("Name");
		
		txtEquipmentName = new JTextField();
		txtEquipmentName.setColumns(10);
		
		JButton btnAddEquipment = new JButton("Add");
		btnAddEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEquipmentButtonActionPerformed(e);
			}
		});
		
		JLabel lblEditEquipment = new JLabel("Edit Quantity");
		
		JLabel lblEquipmentQty = new JLabel("Quantity");
		
		txtEquipmentQty = new JTextField();
		txtEquipmentQty.setColumns(10);
		
		JButton btnEditEquipment = new JButton("Edit");
		btnEditEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editEquipmentButtonActionPerformed(e);
			}
		});
		
		equipmentList = new JComboBox<String>();
		equipmentList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedEquipment = cb.getSelectedIndex();
			}
		});
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
									.addComponent(lblAddEquipment)
									.addComponent(lblEditEquipment))
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_2.createSequentialGroup()
										.addComponent(lblEquipmentQty)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtEquipmentQty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addComponent(btnEditEquipment, Alignment.TRAILING))
								.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnAddEquipment)
									.addGroup(gl_panel_2.createSequentialGroup()
										.addComponent(lblEquipmentName)
										.addGap(18)
										.addComponent(txtEquipmentName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addComponent(equipmentList, Alignment.TRAILING, 0, 196, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(21)
					.addComponent(lblAddEquipment)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEquipmentName)
						.addComponent(txtEquipmentName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddEquipment)
					.addGap(18)
					.addComponent(lblEditEquipment)
					.addGap(5)
					.addComponent(equipmentList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEquipmentQty)
						.addComponent(txtEquipmentQty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEditEquipment)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
	}
	
	/**
	 * Refresh data to update the view
	 */
	
	public void refreshData() {
		
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		
		if (error == null || error.length()==0) {

			//handle menu
			txtItemName.setText("");
			txtItemPrice.setText("");

			String menuText = "<html>";
			Iterator<MenuItem> menuIterator = ftms.getMenuItems().iterator();
			
			while (menuIterator.hasNext()) {
				MenuItem item = menuIterator.next();
				menuText += item.getName()+" - "+item.getPrice()+"<br>";
			}
			
			lblMenu.setText(menuText);

		} else {
			JOptionPane.showMessageDialog(null,"ERROR: "+error);
		}
		

		//handle inventory
		txtSupplyName.setText("");
		txtEquipmentName.setText("");
		txtSupplyQty.setText("");
		txtEquipmentQty.setText("");
		
		String supplyText ="<html>";
		
		supplies = new HashMap<Integer, Supply>();
		supplyList.removeAllItems();
		Iterator<Supply> sIt = ftms.getSupplies().iterator();
		Integer index = 0;
		
		while (sIt.hasNext()){
			Supply supply = sIt.next();
			supplyText += supply.getName()+" - x"+supply.getCount()+"<br>";
			supplies.put(index, supply);
			supplyList.addItem(supply.getName());
			index++;
		}
		
		lblSupplies.setText(supplyText);
		selectedSupply = -1;
		supplyList.setSelectedIndex(selectedSupply);
		
		String equipmentText ="<html>";
		
		equipment = new HashMap<Integer, Equipment>();
		equipmentList.removeAllItems();
		Iterator<Equipment> eIt = ftms.getEquipment().iterator();
		index = 0;
		while (eIt.hasNext()){
			Equipment equip = eIt.next();
			equipmentText += equip.getName()+" - x"+equip.getCount()+"<br>";
			equipment.put(index, equip);
			equipmentList.addItem(equip.getName());
			index++;
		}
		
		lblEquipment.setText(equipmentText);
		selectedEquipment = -1;
		equipmentList.setSelectedIndex(selectedEquipment);
	}
	
	public void addMenuItemButtonActionPerformed(ActionEvent event) {
		FoodTruckController ftc = new FoodTruckController();
		
		try {
		ftc.createMenuItem(txtItemName.getText(),txtItemPrice.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		refreshData();
	}
	
	public void addSupplyButtonActionPerformed(ActionEvent event) {
		FoodTruckController ftc = new FoodTruckController();
		try {
			ftc.createSupply(txtSupplyName.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		refreshData();
	}
	
	public void editSupplyButtonActionPerformed(ActionEvent event) {
		
		if (selectedSupply < 0) {
			error = "You need to select a supply!";
		} else {
			FoodTruckController ftc = new FoodTruckController();
			try {
				ftc.editSupplyQuantity(supplies.get(selectedSupply), txtSupplyQty.getText());
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}

		refreshData();
	}
	
	public void addEquipmentButtonActionPerformed(ActionEvent event) {
		FoodTruckController ftc = new FoodTruckController();
		try {
			ftc.createEquipment(txtEquipmentName.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		refreshData();
	}
	
	public void editEquipmentButtonActionPerformed(ActionEvent event) {
		
		if (selectedEquipment < 0) {
			error = "You need to select a piece of equipment!";
		} else {
			FoodTruckController ftc = new FoodTruckController();
			try {
				ftc.editEquipmentQuantity(equipment.get(selectedEquipment), txtEquipmentQty.getText());
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		
		refreshData();
		
	}
	
	
}
