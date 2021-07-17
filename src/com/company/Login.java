package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login {

    private JLabel mainLabel;
    protected JDialog loginDialog;
    private JPanel loginPanel;
    protected boolean isActive;

    public Login(){
        loginDialog = new JDialog();
        loginPanel = new JPanel();

        loginDialog.setModal(true);

        loginDialog.add(loginPanel, BorderLayout.CENTER);
        loginDialog.setSize(300, 200);
        loginDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Login window closed");
                isActive = false;
            }
        });


        loginDialog.setVisible(true);

    }

}
