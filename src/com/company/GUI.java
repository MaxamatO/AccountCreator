package com.company;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;



public class GUI{



    private final JLabel mainLabel;
    private final JFrame frame;
    private final JPanel panel;
    final private JButton registerButton;
    final private JButton loginButton;

    public GUI() {
        frame = new JFrame();

        panel = new JPanel();
//          LABEL
        mainLabel = new JLabel("Create new account or Log In");
        mainLabel.setBounds(80, 60, 300, 50);
        mainLabel.setFont(mainLabel.getFont().deriveFont(16.0f));
//          BUTTONS
        registerButton = new JButton("Register");
        registerButton.setBounds(100, 115, 90, 30);
        registerButton.setBorderPainted(true);
        registerButton.setFocusPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.addActionListener(e -> {
            Register register = new Register();
        });

        loginButton = new JButton("Login");
        loginButton.setBounds(190, 115, 90, 30);
        loginButton.setBorderPainted(true);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.addActionListener(e -> {
            Login login = new Login();
        });
//          WINDOW PARAMETERS

        panel.add(registerButton, new GridBagConstraints());
        panel.add(mainLabel);
        panel.add(loginButton, new GridBagConstraints());
        panel.setBorder(new EmptyBorder(30, 30, 10, 30));
        panel.setLayout(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);


    }
    public static void main (String[]args){
        new GUI();
    }

}
