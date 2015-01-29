package alchemist;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class AlchemistOptionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JComboBox<String> spellSelector;
	public JFormattedTextField tbNumberOfCasts, tbItemToUse;
	public JButton btnFindItem, btnStart;
	private JLabel lblTitle, lblSpellSelection, lblNumberOfTimes, lblItemToUse, lblItemSelected;
	/**
	 * Create the panel.
	 */
	public AlchemistOptionPanel() {
		setBackground(Color.GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 144, 0, 71, 50, 0};
		gridBagLayout.rowHeights = new int[]{27, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblTitle = new JLabel("The Alchemist");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridheight = 2;
		gbc_lblTitle.gridwidth = 3;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 0;
		add(lblTitle, gbc_lblTitle);
		
		lblSpellSelection = new JLabel("Spell To Cast:");
		lblSpellSelection.setForeground(Color.WHITE);
		lblSpellSelection.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_lblSpellSelection = new GridBagConstraints();
		gbc_lblSpellSelection.anchor = GridBagConstraints.WEST;
		gbc_lblSpellSelection.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpellSelection.gridx = 1;
		gbc_lblSpellSelection.gridy = 2;
		add(lblSpellSelection, gbc_lblSpellSelection);
		
		spellSelector = new JComboBox<String>();
		spellSelector.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		spellSelector.setModel(new DefaultComboBoxModel<String>(new String[] {"Low Alchemy", "High Alchemy"}));
		spellSelector.setSelectedIndex(0);
		GridBagConstraints gbc_spellSelector = new GridBagConstraints();
		gbc_spellSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_spellSelector.insets = new Insets(0, 0, 5, 5);
		gbc_spellSelector.gridx = 3;
		gbc_spellSelector.gridy = 2;
		add(spellSelector, gbc_spellSelector);
		
		lblNumberOfTimes = new JLabel("Number of Times To Cast:");
		lblNumberOfTimes.setForeground(Color.WHITE);
		lblNumberOfTimes.setBackground(Color.WHITE);
		lblNumberOfTimes.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNumberOfTimes = new GridBagConstraints();
		gbc_lblNumberOfTimes.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfTimes.gridx = 1;
		gbc_lblNumberOfTimes.gridy = 3;
		add(lblNumberOfTimes, gbc_lblNumberOfTimes);
		
		tbNumberOfCasts = new JFormattedTextField();
		tbNumberOfCasts.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_tbNumberOfCasts = new GridBagConstraints();
		gbc_tbNumberOfCasts.insets = new Insets(0, 0, 5, 5);
		gbc_tbNumberOfCasts.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbNumberOfCasts.gridx = 3;
		gbc_tbNumberOfCasts.gridy = 3;
		add(tbNumberOfCasts, gbc_tbNumberOfCasts);
		
		lblItemToUse = new JLabel("Item To Use:");
		lblItemToUse.setForeground(Color.WHITE);
		lblItemToUse.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_lblItemToUse = new GridBagConstraints();
		gbc_lblItemToUse.anchor = GridBagConstraints.WEST;
		gbc_lblItemToUse.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemToUse.gridx = 1;
		gbc_lblItemToUse.gridy = 4;
		add(lblItemToUse, gbc_lblItemToUse);
		
		tbItemToUse = new JFormattedTextField();
		tbItemToUse.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_tbItemToUse = new GridBagConstraints();
		gbc_tbItemToUse.insets = new Insets(0, 0, 5, 5);
		gbc_tbItemToUse.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbItemToUse.gridx = 3;
		gbc_tbItemToUse.gridy = 4;
		add(tbItemToUse, gbc_tbItemToUse);
		
		lblItemSelected = new JLabel("");
		lblItemSelected.setForeground(Color.WHITE);
		lblItemSelected.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_lblItemSelected = new GridBagConstraints();
		gbc_lblItemSelected.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblItemSelected.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemSelected.gridx = 3;
		gbc_lblItemSelected.gridy = 5;
		add(lblItemSelected, gbc_lblItemSelected);
		
		btnFindItem = new JButton("Find Item");
		btnFindItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_btnFindItem = new GridBagConstraints();
		gbc_btnFindItem.insets = new Insets(0, 0, 5, 5);
		gbc_btnFindItem.gridx = 3;
		gbc_btnFindItem.gridy = 6;
		add(btnFindItem, gbc_btnFindItem);
		
		JButton btnStart = new JButton("Start Script");
		btnStart.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 0, 5);
		gbc_btnStart.gridx = 3;
		gbc_btnStart.gridy = 7;
		add(btnStart, gbc_btnStart);

	}
}
