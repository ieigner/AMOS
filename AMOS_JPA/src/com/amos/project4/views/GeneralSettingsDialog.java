package com.amos.project4.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.UIManager;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import com.amos.project4.controllers.UserController;
import com.amos.project4.models.User;

public class GeneralSettingsDialog extends JDialog implements AbstractControlledView{

	private final JPanel contentPanel = new JPanel();
	private JTextField f_usernameTextField;
	private JPasswordField f_passwordField;
	private JTextField t_usernameTextField;
	private JPasswordField t_passwordField;
	private JLabel t_lbl_username;
	private JLabel t_lbl_password;
	private JTextField x_usernameTextField;
	private JLabel x_lbl_username;
	private JPasswordField x_passwordField;
	private JLabel x_lbl_password;
	private JTextField l_usernameTextField;
	private JLabel l_lbl_username;
	private JPasswordField l_passwordField;
	private JLabel l_lbl_password;
	private JButton btnConnect;
	
	private UserViewModel viewModel;
	private UserController user_controller;
	
	

	
	public GeneralSettingsDialog(UserController user_controller,UserViewModel viewModel) {
		super();
		init();
		this.user_controller = user_controller;
		this.viewModel = viewModel;
		this.user_controller.addView(this);
		this.updateView(this.user_controller.getCurrent_user());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void init(){
		setTitle("AMOS Project 4 General settings");
		setBounds(100, 100, 600, 260);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 2, 10, 10));
		{
			JPanel FacebookPanel = new JPanel();
			FacebookPanel.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null), "Facebook", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(FacebookPanel);
			SpringLayout sl_FacebookPanel = new SpringLayout();
			FacebookPanel.setLayout(sl_FacebookPanel);
			
			JLabel lblUsername = new JLabel("Username :");
			sl_FacebookPanel.putConstraint(SpringLayout.NORTH, lblUsername, 10, SpringLayout.NORTH, FacebookPanel);
			sl_FacebookPanel.putConstraint(SpringLayout.WEST, lblUsername, 10, SpringLayout.WEST, FacebookPanel);
			FacebookPanel.add(lblUsername);
			
			f_usernameTextField = new JTextField();
			sl_FacebookPanel.putConstraint(SpringLayout.WEST, f_usernameTextField, 20, SpringLayout.EAST, lblUsername);
			sl_FacebookPanel.putConstraint(SpringLayout.SOUTH, f_usernameTextField, 0, SpringLayout.SOUTH, lblUsername);
			FacebookPanel.add(f_usernameTextField);
			f_usernameTextField.setColumns(15);
			
			JLabel lblPassword = new JLabel("Password :");
			sl_FacebookPanel.putConstraint(SpringLayout.NORTH, lblPassword, 12, SpringLayout.SOUTH, lblUsername);
			sl_FacebookPanel.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, lblUsername);
			sl_FacebookPanel.putConstraint(SpringLayout.EAST, lblPassword, 0, SpringLayout.EAST, lblUsername);
			FacebookPanel.add(lblPassword);
			
			f_passwordField = new JPasswordField();
			sl_FacebookPanel.putConstraint(SpringLayout.WEST, f_passwordField, 0, SpringLayout.WEST, f_usernameTextField);
			sl_FacebookPanel.putConstraint(SpringLayout.SOUTH, f_passwordField, 0, SpringLayout.SOUTH, lblPassword);
			sl_FacebookPanel.putConstraint(SpringLayout.EAST, f_passwordField, 0, SpringLayout.EAST, f_usernameTextField);
			FacebookPanel.add(f_passwordField);
		}
		{
			JPanel TwitterPanel = new JPanel();
			TwitterPanel.setBorder(new TitledBorder(null, "Twitter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(TwitterPanel);
			SpringLayout sl_TwitterPanel = new SpringLayout();
			TwitterPanel.setLayout(sl_TwitterPanel);
			{
				t_lbl_username = new JLabel("Username :");
				sl_TwitterPanel.putConstraint(SpringLayout.NORTH, t_lbl_username, 10, SpringLayout.NORTH, TwitterPanel);
				sl_TwitterPanel.putConstraint(SpringLayout.WEST, t_lbl_username, 10, SpringLayout.WEST, TwitterPanel);
				TwitterPanel.add(t_lbl_username);
			}
			{
				t_usernameTextField = new JTextField();
				sl_TwitterPanel.putConstraint(SpringLayout.WEST, t_usernameTextField, 20, SpringLayout.EAST, t_lbl_username);
				sl_TwitterPanel.putConstraint(SpringLayout.SOUTH, t_usernameTextField, 0, SpringLayout.SOUTH, t_lbl_username);
				t_usernameTextField.setColumns(15);
				TwitterPanel.add(t_usernameTextField);
			}
			{
				t_lbl_password = new JLabel("Password :");
				sl_TwitterPanel.putConstraint(SpringLayout.NORTH, t_lbl_password, 12, SpringLayout.SOUTH, t_lbl_username);
				sl_TwitterPanel.putConstraint(SpringLayout.WEST, t_lbl_password, 0, SpringLayout.WEST, t_lbl_username);
				TwitterPanel.add(t_lbl_password);
			}
			{
				t_passwordField = new JPasswordField();
				sl_TwitterPanel.putConstraint(SpringLayout.WEST, t_passwordField, 0, SpringLayout.WEST, t_usernameTextField);
				sl_TwitterPanel.putConstraint(SpringLayout.SOUTH, t_passwordField, 0, SpringLayout.SOUTH, t_lbl_password);
				sl_TwitterPanel.putConstraint(SpringLayout.EAST, t_passwordField, 0, SpringLayout.EAST, t_usernameTextField);
				TwitterPanel.add(t_passwordField);
			}
		}
		{
			JPanel XingPanel = new JPanel();
			XingPanel.setBorder(new TitledBorder(null, "Xing", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(XingPanel);
			SpringLayout sl_XingPanel = new SpringLayout();
			XingPanel.setLayout(sl_XingPanel);
			{
				x_lbl_username = new JLabel("Username :");
				sl_XingPanel.putConstraint(SpringLayout.NORTH, x_lbl_username, 10, SpringLayout.NORTH, XingPanel);
				sl_XingPanel.putConstraint(SpringLayout.WEST, x_lbl_username, 10, SpringLayout.WEST, XingPanel);
				XingPanel.add(x_lbl_username);
			}
			{
				x_usernameTextField = new JTextField();
				sl_XingPanel.putConstraint(SpringLayout.WEST, x_usernameTextField, 20, SpringLayout.EAST, x_lbl_username);
				sl_XingPanel.putConstraint(SpringLayout.SOUTH, x_usernameTextField, 0, SpringLayout.SOUTH, x_lbl_username);
				x_usernameTextField.setColumns(15);
				XingPanel.add(x_usernameTextField);
			}
			{
				x_lbl_password = new JLabel("Password :");
				sl_XingPanel.putConstraint(SpringLayout.NORTH, x_lbl_password, 12, SpringLayout.SOUTH, x_lbl_username);
				sl_XingPanel.putConstraint(SpringLayout.WEST, x_lbl_password, 0, SpringLayout.WEST, x_lbl_username);
				sl_XingPanel.putConstraint(SpringLayout.EAST, x_lbl_password, 0, SpringLayout.EAST, x_lbl_username);
				XingPanel.add(x_lbl_password);
			}
			{
				x_passwordField = new JPasswordField();
				sl_XingPanel.putConstraint(SpringLayout.WEST, x_passwordField, 0, SpringLayout.WEST, x_usernameTextField);
				sl_XingPanel.putConstraint(SpringLayout.SOUTH, x_passwordField, 0, SpringLayout.SOUTH, x_lbl_password);
				sl_XingPanel.putConstraint(SpringLayout.EAST, x_passwordField, 0, SpringLayout.EAST, x_usernameTextField);
				XingPanel.add(x_passwordField);
			}
		}
		{
			JPanel LinkedInPanel = new JPanel();
			LinkedInPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "LinkedIn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(LinkedInPanel);
			SpringLayout sl_LinkedInPanel = new SpringLayout();
			LinkedInPanel.setLayout(sl_LinkedInPanel);
			{
				l_lbl_username = new JLabel("Username :");
				sl_LinkedInPanel.putConstraint(SpringLayout.NORTH, l_lbl_username, 10, SpringLayout.NORTH, LinkedInPanel);
				sl_LinkedInPanel.putConstraint(SpringLayout.WEST, l_lbl_username, 10, SpringLayout.WEST, LinkedInPanel);
				LinkedInPanel.add(l_lbl_username);
			}
			{
				l_usernameTextField = new JTextField();
				sl_LinkedInPanel.putConstraint(SpringLayout.WEST, l_usernameTextField, 20, SpringLayout.EAST, l_lbl_username);
				sl_LinkedInPanel.putConstraint(SpringLayout.SOUTH, l_usernameTextField, 0, SpringLayout.SOUTH, l_lbl_username);
				l_usernameTextField.setColumns(15);
				LinkedInPanel.add(l_usernameTextField);
			}
			{
				l_lbl_password = new JLabel("Password :");
				sl_LinkedInPanel.putConstraint(SpringLayout.NORTH, l_lbl_password, 12, SpringLayout.SOUTH, l_lbl_username);
				sl_LinkedInPanel.putConstraint(SpringLayout.WEST, l_lbl_password, 0, SpringLayout.WEST, l_lbl_username);
				sl_LinkedInPanel.putConstraint(SpringLayout.EAST, l_lbl_password, 0, SpringLayout.EAST, l_lbl_username);
				LinkedInPanel.add(l_lbl_password);
			}
			{
				l_passwordField = new JPasswordField();
				sl_LinkedInPanel.putConstraint(SpringLayout.WEST, l_passwordField, 0, SpringLayout.WEST, l_usernameTextField);
				sl_LinkedInPanel.putConstraint(SpringLayout.SOUTH, l_passwordField, 0, SpringLayout.SOUTH, l_lbl_password);
				sl_LinkedInPanel.putConstraint(SpringLayout.EAST, l_passwordField, 0, SpringLayout.EAST, l_usernameTextField);
				LinkedInPanel.add(l_passwordField);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnConnect = new JButton("Connect");
				buttonPane.add(btnConnect);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener( new OKAction());
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new CancelAction());
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}


	@Override
	public void modelPropertyChange(Observable o, Object arg) {
		User _user = user_controller.getCurrent_user();
		updateView(_user);
	}
	
	private void updateView(User user){
		this.f_usernameTextField.setText(user.getF_username());
		this.f_passwordField.setText(user.getF_userpass());
		this.t_usernameTextField.setText(user.getT_username());
		this.t_passwordField.setText(user.getT_userpass());
		this.x_usernameTextField.setText(user.getX_username());
		this.x_passwordField.setText(user.getX_userpass());
		this.l_usernameTextField.setText(user.getL_username());
		this.l_passwordField.setText(user.getL_userpass());
	}
	
	private class OKAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			viewModel.setSocialMediaDatas(
					f_usernameTextField.getText(), 
					new String(f_passwordField.getPassword()),
					t_usernameTextField.getText(), 
					new String(t_passwordField.getPassword()),
					x_usernameTextField.getText(), 
					new String(x_passwordField.getPassword()),
					l_usernameTextField.getText(), 
					new String(l_passwordField.getPassword()));
			dispose();
		}
		
	}
	
	private class CancelAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();		
		}		
	}
}