package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login {

    private JLabel mainLabel;
    protected JDialog loginDialog;
    private JPanel loginPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton submitButton;
    private String username;
    private char[] password;


    public Login(){
        loginDialog = new JDialog();
        loginPanel = new JPanel();
        mainLabel = new JLabel("Login");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        submitButton = new JButton("Submit");



        usernameLabel.setBounds(10, 60, 100, 20);
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(Font.PLAIN, 16.0f));

        submitButton.setBounds(105, 125, 80, 20);
        submitButton.setBorderPainted(true);
        submitButton.setFocusPainted(false);
        submitButton.setContentAreaFilled(false);
        submitButton.addActionListener( e ->{
            setUsername(usernameField.getText());
            setPassword(passwordField.getPassword());


            if(checkIfFilled()){
                DataBase db = new DataBase();
                if(db.checkIfUserExists(username, String.valueOf(password))){
                    JOptionPane.showMessageDialog(loginDialog, "You were logged in");
                    loginDialog.dispose();
                    System.exit(0);
                }
                else{
                    JOptionPane.showMessageDialog(loginDialog, "Wrong password or username");
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
            else{
                JOptionPane.showMessageDialog(loginDialog, "Fill in required");
            }

        });

        usernameField.setBounds(100, 62, 150, 20);

        passwordField.setBounds(100, 92, 150, 20);

        passwordLabel.setBounds(10, 90, 100, 20);
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(Font.PLAIN, 16.0f));

        mainLabel.setFont(mainLabel.getFont().deriveFont(Font.BOLD, 19.0f));
        mainLabel.setBounds(120, 10, 80, 30);

        loginDialog.setModal(true);
        loginDialog.setResizable(false);



        loginDialog.add(loginPanel, BorderLayout.CENTER);
        loginPanel.setLayout(null);
        loginDialog.setSize(300, 200);

        loginPanel.add(submitButton);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordField);
        loginPanel.add(passwordLabel);
        loginPanel.add(mainLabel);
        loginDialog.setVisible(true);

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

    public boolean checkIfFilled(){
        return !username.equals("") && !String.valueOf(password).equals("");
    }
}
