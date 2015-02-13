package com.psutton.alchemist;

import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.Font;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.SwingConstants;

import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlchemistFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField tfItemToUse;
	private JLabel lblTitle, lblItemToUse, lblItemID, lblSpellToCast, lblItemIDVal;
	private JButton btnSearch, btnStart;

	public AlchemistFrame() {
		setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		setType(Type.UTILITY);
		getContentPane().setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 400, 250);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0, 30, 30, 0};
		gridBagLayout.rowHeights = new int[]{0, 30, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		lblTitle = new JLabel("The Alchemist");
		lblTitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 22));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridwidth = 12;
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 0;
		getContentPane().add(lblTitle, gbc_lblTitle);
		
		lblSpellToCast = new JLabel("Spell To Cast:");
		lblSpellToCast.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_lblSpellToCast = new GridBagConstraints();
		gbc_lblSpellToCast.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSpellToCast.gridwidth = 6;
		gbc_lblSpellToCast.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpellToCast.gridx = 1;
		gbc_lblSpellToCast.gridy = 2;
		getContentPane().add(lblSpellToCast, gbc_lblSpellToCast);
		
		System.out.println("Num of items in spellList: "+AlchemistGlobal.spellList.length);
		final JComboBox<?> cbSpellToCast = new JComboBox<Object>(AlchemistGlobal.spellList);
		cbSpellToCast.setSelectedIndex(0);
		cbSpellToCast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedSpell = cbSpellToCast.getSelectedItem().toString();
				System.out.println("Selected Spell: "+ AlchemistGlobal.availableSpells.get((selectedSpell)));
				AlchemistGlobal.selectedSpell = AlchemistGlobal.availableSpells.get((selectedSpell));
				if (AlchemistGlobal.selectedSpell.requiresAnItem()) {
					toggleItemToUse(true);
				}
				else {
					toggleItemToUse(false);
				}
			}
		});
		lblSpellToCast.setLabelFor(cbSpellToCast);
		cbSpellToCast.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		cbSpellToCast.setMaximumRowCount(4);
		GridBagConstraints gbc_cbSpellToCast = new GridBagConstraints();
		gbc_cbSpellToCast.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbSpellToCast.gridwidth = 4;
		gbc_cbSpellToCast.insets = new Insets(0, 0, 5, 5);
		gbc_cbSpellToCast.gridx = 9;
		gbc_cbSpellToCast.gridy = 2;
		getContentPane().add(cbSpellToCast, gbc_cbSpellToCast);
		
		lblItemToUse = new JLabel("Item To Use:");
		lblItemToUse.setEnabled(false);
		lblItemToUse.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_lblItemToUse = new GridBagConstraints();
		gbc_lblItemToUse.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblItemToUse.gridwidth = 6;
		gbc_lblItemToUse.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemToUse.gridx = 1;
		gbc_lblItemToUse.gridy = 3;
		getContentPane().add(lblItemToUse, gbc_lblItemToUse);
		
		tfItemToUse = new JTextField();
		tfItemToUse.setEnabled(false);
		GridBagConstraints gbc_tfItemToUse = new GridBagConstraints();
		gbc_tfItemToUse.gridwidth = 4;
		gbc_tfItemToUse.insets = new Insets(0, 0, 5, 5);
		gbc_tfItemToUse.fill = GridBagConstraints.BOTH;
		gbc_tfItemToUse.gridx = 9;
		gbc_tfItemToUse.gridy = 3;
		getContentPane().add(tfItemToUse, gbc_tfItemToUse);
		tfItemToUse.setColumns(10);
		lblItemToUse.setLabelFor(tfItemToUse);
		
		lblItemID = new JLabel("ID of Item:");
		lblItemID.setEnabled(false);
		lblItemID.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_lblItemID = new GridBagConstraints();
		gbc_lblItemID.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblItemID.gridwidth = 5;
		gbc_lblItemID.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemID.gridx = 1;
		gbc_lblItemID.gridy = 4;
		getContentPane().add(lblItemID, gbc_lblItemID);
		
		lblItemIDVal = new JLabel("N/A");
		lblItemIDVal.setEnabled(false);
		lblItemIDVal.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_lblItemIDVal = new GridBagConstraints();
		gbc_lblItemIDVal.gridwidth = 3;
		gbc_lblItemIDVal.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblItemIDVal.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemIDVal.gridx = 8;
		gbc_lblItemIDVal.gridy = 4;
		getContentPane().add(lblItemIDVal, gbc_lblItemIDVal);
		lblItemID.setLabelFor(lblItemIDVal);
		
		btnSearch = new JButton("Search");
		btnSearch.setEnabled(false);
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 12;
		gbc_btnSearch.gridy = 4;
		getContentPane().add(btnSearch, gbc_btnSearch);
		
		btnStart = new JButton("Start");
		btnStart.setEnabled(false);
		btnStart.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStart.insets = new Insets(0, 0, 0, 5);
		gbc_btnStart.gridx = 12;
		gbc_btnStart.gridy = 6;
		getContentPane().add(btnStart, gbc_btnStart);
		
	}

	private void toggleItemToUse(boolean shouldEnabled) {
		lblItemToUse.setEnabled(shouldEnabled);
		tfItemToUse.setEnabled(shouldEnabled);
		btnSearch.setEnabled(shouldEnabled);
	}
}
