package com.company;

import javax.xml.crypto.Data;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DataBase {
    private Connection connection;
    private PreparedStatement statement;
    private String dbPassword;
    private String dbUser;
    private String url;
    private final int KEY = 5;
    private final String inputStream = "C://Users//Maks//home//_java//gui-tut//resources//config.properties";

    DataBase() throws Exception{
        FileInputStream fis = new FileInputStream(inputStream);
        Properties prop = new Properties();

        prop.load(fis);

        dbPassword = prop.getProperty("DatabasePassword");
        dbUser = prop.getProperty("DatabaseUsername");
        url = prop.getProperty("url");

    }

    public void createUser(String username, String password){
        try {
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
            statement = connection.prepareStatement("INSERT INTO user (username, password) VALUES (?, ?)");
            DataBase db = new DataBase();
            String encryptedPassword = encryption(password);
            statement.setString(1, username);
            statement.setString(2, encryptedPassword);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean checkIfUserExists(String username, String password){
        try {
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
            statement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                String correctPassword = rs.getString("password");
                String decryptedPassword = decryption(correctPassword);
                return checkIfPasswordFits(String.valueOf(password), decryptedPassword);
            }
            else{
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkIfPasswordFits(String passedPassword, String correctPassword){
        return passedPassword.equals(correctPassword);
    }

    private String encryption(String toEncrypt){

        char[] charToEncrypt = toEncrypt.toCharArray();
        char[] encrypted = new char[toEncrypt.length()];
        int i = 0;

        for(char c: charToEncrypt){
            c += KEY;
            encrypted[i] += c;
            i++;
        }
        return String.valueOf(encrypted);
    }

    private String decryption(String toDecrypt){

        char[] encrypted = toDecrypt.toCharArray();
        char[] decrypted = new char[toDecrypt.length()];
        int i = 0;

        for(char c: encrypted){
            c -= KEY;
            decrypted[i] += c;
            i++;
        }
        return String.valueOf(decrypted);
    }
}
