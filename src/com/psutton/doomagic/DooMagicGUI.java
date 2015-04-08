package com.psutton.doomagic;

import com.epicbot.api.rs3.methods.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DooMagicGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	//	private JTextField tfItemToUse;
	private JLabel lblTitle, lblSpellToCast, lblNumOfCasts;
	private JLabel lblPlaceholder;
	//	private JLabel lblItemToUse, lblItemID, lblItemIDVal;
	//	private JButton btnSearch;
	private JButton btnStart;
	private JTextField tfNumOfCasts;

	public DooMagicGUI() {
		int topLeftX = (Game.getWidth() - 400) / 2;
		int topLeftY = (Game.getHeight() - 250) / 2;

		DooMagicGlobal.frame = this;
		setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		setType(Type.UTILITY);
		getContentPane().setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(topLeftX, topLeftY, 400, 250);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 30, 30, 0};
		gridBagLayout.rowHeights = new int[]{0, 30, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		lblTitle = new JLabel(DooMagicGlobal.scriptName);
		lblTitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 22));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridheight = 2;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridwidth = 10;
		gbc_lblTitle.gridx = 5;
		gbc_lblTitle.gridy = 0;
		getContentPane().add(lblTitle, gbc_lblTitle);

		lblPlaceholder = new JLabel("");
		lblPlaceholder.setEnabled(false);
		GridBagConstraints gbc_lblPlaceholder = new GridBagConstraints();
		gbc_lblPlaceholder.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlaceholder.gridx = 3;
		gbc_lblPlaceholder.gridy = 1;
		getContentPane().add(lblPlaceholder, gbc_lblPlaceholder);

		final JComboBox<?> cbSpellToCast = new JComboBox<Object>(DooMagicGlobal.spellList);
		cbSpellToCast.setSelectedIndex(0);
		DooMagicGlobal.selectedSpell = DooMagicGlobal.availableSpells.get((cbSpellToCast.getSelectedItem().toString()));
		cbSpellToCast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedSpell = cbSpellToCast.getSelectedItem().toString();
				System.out.println("selectedSpell: "+selectedSpell);
				DooMagicGlobal.selectedSpell = DooMagicGlobal.availableSpells.get(selectedSpell);
				btnStart.setEnabled(true);
				//				if (AlchemistGlobal.selectedSpell.requiresAnItem()) {
				//					toggleItemToUse(true);
				//				}
				//				else {
				//					toggleItemToUse(false);
				//				}
			}
		});

		lblSpellToCast = new JLabel("Spell To Cast:");
		lblSpellToCast.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_lblSpellToCast = new GridBagConstraints();
		gbc_lblSpellToCast.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSpellToCast.gridwidth = 3;
		gbc_lblSpellToCast.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpellToCast.gridx = 5;
		gbc_lblSpellToCast.gridy = 2;
		getContentPane().add(lblSpellToCast, gbc_lblSpellToCast);
		lblSpellToCast.setLabelFor(cbSpellToCast);
		cbSpellToCast.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		cbSpellToCast.setMaximumRowCount(4);
		GridBagConstraints gbc_cbSpellToCast = new GridBagConstraints();
		gbc_cbSpellToCast.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbSpellToCast.gridwidth = 6;
		gbc_cbSpellToCast.insets = new Insets(0, 0, 5, 5);
		gbc_cbSpellToCast.gridx = 9;
		gbc_cbSpellToCast.gridy = 2;
		getContentPane().add(cbSpellToCast, gbc_cbSpellToCast);

		lblNumOfCasts = new JLabel("Num Of Casts:");
		lblNumOfCasts.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNumOfCasts = new GridBagConstraints();
		gbc_lblNumOfCasts.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumOfCasts.gridx = 5;
		gbc_lblNumOfCasts.gridy = 3;
		getContentPane().add(lblNumOfCasts, gbc_lblNumOfCasts);

		tfNumOfCasts = new JTextField();
		tfNumOfCasts.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				int numOfCasts;
				String input = tfNumOfCasts.getText();
				input.replace(',', ' ');
				System.out.println("Num of casts entered: " + input);
				try {
					numOfCasts = Integer.parseInt(input);
					DooMagicGlobal.numOfCasts = numOfCasts;
				} catch (NumberFormatException numFormatException) {
					System.out.println("Invalid entry for number of casts.");
					DooMagicGlobal.numOfCasts = -1;
				}
			}
		});

		//		tfNumOfCasts.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				int numOfCasts;
		//				String input = tfNumOfCasts.getText();
		//				input.replace(',', ' ');
		//				System.out.println("Num of casts entered: " + input);
		//				try {
		//					numOfCasts = Integer.parseInt(input);
		//					AlchemistGlobal.numOfCasts = numOfCasts;
		//				} catch (NumberFormatException numFormatException) {
		//					System.out.println("Invalid entry for number of casts.");
		//					AlchemistGlobal.numOfCasts = -1;
		//				}
		//			}
		//		});
		tfNumOfCasts.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		lblNumOfCasts.setLabelFor(tfNumOfCasts);
		GridBagConstraints gbc_tfNumOfCasts = new GridBagConstraints();
		gbc_tfNumOfCasts.gridwidth = 6;
		gbc_tfNumOfCasts.insets = new Insets(0, 0, 5, 5);
		gbc_tfNumOfCasts.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNumOfCasts.gridx = 9;
		gbc_tfNumOfCasts.gridy = 3;
		getContentPane().add(tfNumOfCasts, gbc_tfNumOfCasts);
		tfNumOfCasts.setColumns(10);

		//		lblItemToUse = new JLabel("Item To Use:");
		//		lblItemToUse.setEnabled(false);
		//		lblItemToUse.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		//		GridBagConstraints gbc_lblItemToUse = new GridBagConstraints();
		//		gbc_lblItemToUse.fill = GridBagConstraints.HORIZONTAL;
		//		gbc_lblItemToUse.gridwidth = 3;
		//		gbc_lblItemToUse.insets = new Insets(0, 0, 5, 5);
		//		gbc_lblItemToUse.gridx = 5;
		//		gbc_lblItemToUse.gridy = 4;
		//		getContentPane().add(lblItemToUse, gbc_lblItemToUse);
		//		lblItemToUse.setLabelFor(tfItemToUse);
		//		
		//		tfItemToUse = new JTextField();
		//		tfItemToUse.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		//		tfItemToUse.setEnabled(false);
		//		GridBagConstraints gbc_tfItemToUse = new GridBagConstraints();
		//		gbc_tfItemToUse.gridwidth = 6;
		//		gbc_tfItemToUse.insets = new Insets(0, 0, 5, 5);
		//		gbc_tfItemToUse.fill = GridBagConstraints.BOTH;
		//		gbc_tfItemToUse.gridx = 9;
		//		gbc_tfItemToUse.gridy = 4;
		//		getContentPane().add(tfItemToUse, gbc_tfItemToUse);
		//		tfItemToUse.setColumns(10);
		//		
		//		lblItemID = new JLabel("ID of Item:");
		//		lblItemID.setEnabled(false);
		//		lblItemID.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		//		lblItemID.setLabelFor(lblItemIDVal);
		//		GridBagConstraints gbc_lblItemID = new GridBagConstraints();
		//		gbc_lblItemID.fill = GridBagConstraints.HORIZONTAL;
		//		gbc_lblItemID.gridwidth = 2;
		//		gbc_lblItemID.insets = new Insets(0, 0, 5, 5);
		//		gbc_lblItemID.gridx = 5;
		//		gbc_lblItemID.gridy = 5;
		//		getContentPane().add(lblItemID, gbc_lblItemID);
		//		
		//		lblItemIDVal = new JLabel("N/A");
		//		lblItemIDVal.setEnabled(false);
		//		lblItemIDVal.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		//		GridBagConstraints gbc_lblItemIDVal = new GridBagConstraints();
		//		gbc_lblItemIDVal.gridwidth = 3;
		//		gbc_lblItemIDVal.fill = GridBagConstraints.HORIZONTAL;
		//		gbc_lblItemIDVal.insets = new Insets(0, 0, 5, 5);
		//		gbc_lblItemIDVal.gridx = 10;
		//		gbc_lblItemIDVal.gridy = 5;
		//		getContentPane().add(lblItemIDVal, gbc_lblItemIDVal);
		//		
		//		btnSearch = new JButton("Search");
		//		btnSearch.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		//		btnSearch.setEnabled(false);
		//		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		//		gbc_btnSearch.gridwidth = 4;
		//		gbc_btnSearch.fill = GridBagConstraints.HORIZONTAL;
		//		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		//		gbc_btnSearch.gridx = 10;
		//		gbc_btnSearch.gridy = 6;
		//		getContentPane().add(btnSearch, gbc_btnSearch);

		btnStart = new JButton("Start");
		btnStart.setEnabled(false);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DooMagicGUI frame = DooMagicGlobal.frame;
				frame.dispose();
			}
		});
		btnStart.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 14;
		gbc_btnStart.gridy = 6;
		getContentPane().add(btnStart, gbc_btnStart);

	}

	//	private void toggleItemToUse(boolean shouldEnabled) {
	//		lblItemToUse.setEnabled(shouldEnabled);
	//		tfItemToUse.setEnabled(shouldEnabled);
	//		btnSearch.setEnabled(shouldEnabled);
	//	}
}
