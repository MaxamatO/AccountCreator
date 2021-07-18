package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.text.MessageFormat;
import java.util.Arrays;

public class Register{

    protected JDialog registerDialog;
    protected boolean isActive;
    private final JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JLabel passwordLabel;
    private JLabel repeatPasswordLabel;
    private String username;
    private JPasswordField repeatPasswordField;
    private char[] password;
    private char[] repeatPassword;


    public Register(){
        registerDialog = new JDialog();
        JPanel registerPanel = new JPanel();
        JLabel mainLabel = new JLabel("Register");
        usernameTextField = new JTextField();
        passwordTextField = new JPasswordField();
        JLabel usernameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");
        repeatPasswordLabel = new JLabel();
        JButton submitButton = new JButton("Submit");
        repeatPasswordField = new JPasswordField();

        registerDialog.setModal(true);
        registerDialog.setResizable(false);

        mainLabel.setFont(mainLabel.getFont().deriveFont(Font.BOLD, 19.0f));
        mainLabel.setBounds(100, 10, 200, 40);

        usernameLabel.setBounds(10, 60, 100, 20);
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(Font.PLAIN, 16.0f));

        usernameTextField.setBounds(100, 62, 150, 20);

        passwordLabel.setBounds(10, 90, 100, 20);
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(Font.PLAIN, 16.0f));

        passwordTextField.setBounds(100, 92, 150, 20);

        repeatPasswordLabel.setBounds(10, 120, 100, 50);
        repeatPasswordLabel.setText("<html>"+"Repeat your password:"+"</html>");
        repeatPasswordLabel.setFont(repeatPasswordLabel.getFont().deriveFont(Font.PLAIN, 16.0f));

        repeatPasswordField.setBounds(100, 140, 150, 20);

        submitButton.setBounds(100, 220, 80, 20);
        submitButton.setBorderPainted(true);
        submitButton.setFocusPainted(false);
        submitButton.setContentAreaFilled(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUsername(usernameTextField.getText());
                setPassword(passwordTextField.getPassword());
                setRepeatPassword(repeatPasswordField.getPassword());

                System.out.println(getUsername());
                System.out.println(getPassword());
                System.out.println(getRepeatPassword());

                if(checkPasswords()){
                    saveUser();
                    // registerDialog.dispose();
                    // new Login();
                }

            }
        });

        registerDialog.add(registerPanel, BorderLayout.CENTER);
        registerDialog.setSize(300, 300);
        registerDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isActive = false;
            }
        });

        registerPanel.setLayout(null);
        registerPanel.add(submitButton);
        registerPanel.add(usernameLabel);
        registerPanel.add(mainLabel);
        registerPanel.add(usernameTextField);
        registerPanel.add(passwordLabel);
        registerPanel.add(passwordTextField);
        registerPanel.add(repeatPasswordLabel);
        registerPanel.add(repeatPasswordField);
        registerDialog.setVisible(true);

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public char[] getPassword() {
        return password;
    }

    public char[] getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(char[] repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public boolean checkIfUsernameValid(){
        return username.length() >= 4 && username.length() <= 16;
    }

    public boolean checkIfPasswordIsIdentical(){
        return Arrays.equals(password, repeatPassword);
    }

    public boolean checkPasswordLenValidation(){
        return password.length <= 16 && password.length >=4;
    }

    private boolean checkPasswords(){
        if(checkIfPasswordIsIdentical() && checkIfUsernameValid()){
            if(checkPasswordLenValidation()){
                JOptionPane.showMessageDialog(registerDialog, "User successfully registered");
                passwordTextField.setText("");
                repeatPasswordField.setText("");
                return true;
            }
            else {
                JOptionPane.showMessageDialog(registerDialog, "Password too long or too short");
                passwordTextField.setText("");
                repeatPasswordField.setText("");
                return false;
            }
        }
        else if(checkIfPasswordIsIdentical() && !checkIfUsernameValid()){
            JOptionPane.showMessageDialog(registerDialog, "Username too short or too long");
            usernameTextField.setText("");
            passwordTextField.setText("");
            repeatPasswordField.setText("");
            return false;
        }
        else{
            JOptionPane.showMessageDialog(registerDialog, "Passwords are not identical");
            passwordTextField.setText("");
            repeatPasswordField.setText("");
            return false;
        }
    }

    private void saveUser(){
        DataBase db = new DataBase();
        db.createUser(username, String.valueOf(password));
    }
}
