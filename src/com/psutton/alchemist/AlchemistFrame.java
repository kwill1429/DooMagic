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

public class AlchemistFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField tfItemToUse;

	public AlchemistFrame() {
		setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		setType(Type.UTILITY);
		getContentPane().setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 300, 250);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 30, 30, 30, 0};
		gridBagLayout.rowHeights = new int[]{0, 30, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("The Alchemist");
		lblNewLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridwidth = 8;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblSpellToCast = new JLabel("Spell To Cast:");
		lblSpellToCast.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_lblSpellToCast = new GridBagConstraints();
		gbc_lblSpellToCast.anchor = GridBagConstraints.WEST;
		gbc_lblSpellToCast.gridwidth = 5;
		gbc_lblSpellToCast.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpellToCast.gridx = 1;
		gbc_lblSpellToCast.gridy = 2;
		getContentPane().add(lblSpellToCast, gbc_lblSpellToCast);
		
		System.out.println("Spell List Size: "+AlchemistGlobal.spellList.length);
		JComboBox<String> cbSpellToCast = new JComboBox<String>(AlchemistGlobal.spellList);
		lblSpellToCast.setLabelFor(cbSpellToCast);
		cbSpellToCast.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		cbSpellToCast.setMaximumRowCount(4);
		GridBagConstraints gbc_cbSpellToCast = new GridBagConstraints();
		gbc_cbSpellToCast.gridwidth = 3;
		gbc_cbSpellToCast.insets = new Insets(0, 0, 5, 5);
		gbc_cbSpellToCast.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbSpellToCast.gridx = 6;
		gbc_cbSpellToCast.gridy = 2;
		getContentPane().add(cbSpellToCast, gbc_cbSpellToCast);
		
		JLabel lblItemToUse = new JLabel("Item To Use:");
		lblItemToUse.setEnabled(false);
		lblItemToUse.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_lblItemToUse = new GridBagConstraints();
		gbc_lblItemToUse.anchor = GridBagConstraints.WEST;
		gbc_lblItemToUse.gridwidth = 4;
		gbc_lblItemToUse.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemToUse.gridx = 1;
		gbc_lblItemToUse.gridy = 3;
		getContentPane().add(lblItemToUse, gbc_lblItemToUse);
		
		tfItemToUse = new JTextField();
		tfItemToUse.setEnabled(false);
		GridBagConstraints gbc_tfItemToUse = new GridBagConstraints();
		gbc_tfItemToUse.gridwidth = 3;
		gbc_tfItemToUse.insets = new Insets(0, 0, 5, 5);
		gbc_tfItemToUse.fill = GridBagConstraints.BOTH;
		gbc_tfItemToUse.gridx = 6;
		gbc_tfItemToUse.gridy = 3;
		getContentPane().add(tfItemToUse, gbc_tfItemToUse);
		tfItemToUse.setColumns(10);
		lblItemToUse.setLabelFor(tfItemToUse);
		
		JLabel lblItemID = new JLabel("ID of Item:");
		lblItemID.setEnabled(false);
		lblItemID.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_lblItemID = new GridBagConstraints();
		gbc_lblItemID.anchor = GridBagConstraints.WEST;
		gbc_lblItemID.gridwidth = 4;
		gbc_lblItemID.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemID.gridx = 1;
		gbc_lblItemID.gridy = 4;
		getContentPane().add(lblItemID, gbc_lblItemID);
		
		JLabel lblItemIDVal = new JLabel("N/A");
		lblItemIDVal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItemIDVal.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_lblItemIDVal = new GridBagConstraints();
		gbc_lblItemIDVal.gridwidth = 2;
		gbc_lblItemIDVal.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblItemIDVal.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemIDVal.gridx = 7;
		gbc_lblItemIDVal.gridy = 4;
		getContentPane().add(lblItemIDVal, gbc_lblItemIDVal);
		lblItemID.setLabelFor(lblItemIDVal);
		
		JButton btnStart = new JButton("Start");
		btnStart.setEnabled(false);
		btnStart.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStart.insets = new Insets(0, 0, 0, 5);
		gbc_btnStart.gridx = 8;
		gbc_btnStart.gridy = 6;
		getContentPane().add(btnStart, gbc_btnStart);
		
	}

}
