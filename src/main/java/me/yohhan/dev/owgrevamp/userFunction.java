package me.yohhan.dev.owgrevamp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import owgdata.ConnectionDB;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class userFunction implements Initializable {





    //GLOBAL DATE DELCARATION

    public static String globalupdateID;
    private Alert alert;

    public static String name;
    public static String phone;
    public static String email;
    public static String lastname;
    public static String birthday;
    public static String address;

    // LOGIN FXMLs

    @FXML
    private AnchorPane login_fxml;

    @FXML
    private Button login_loginButton;

    @FXML
    private Label login_loginLabel;

    @FXML
    private PasswordField login_passwordField;

    @FXML
    private Button login_registerButton;

    @FXML
    private TextField login_usernameField;

    @FXML
    private ImageView loseFocus;


    // REGISTER FXMLs

    @FXML
    private TextField register_addressField;

    @FXML
    private DatePicker register_birthday;

    @FXML
    private TextField register_emailFieldRegister;

    @FXML
    private Label register_errorMessageRegister;

    @FXML
    private TextField register_firstnameField;

    @FXML
    private AnchorPane register_fxml;

    @FXML
    private TextField register_lastnameField;

    @FXML
    private Button register_loginButton;

    @FXML
    private PasswordField register_passwordFieldRegister;

    @FXML
    private TextField register_phoneField;

    @FXML
    private Button register_registerButton;

    @FXML
    private TextField register_usernameFieldRegister;




    @FXML
    public void loseFocus(MouseEvent event) {
        login_usernameField.getParent().requestFocus();
        event.consume();
    }

    public void login_registerButtonAction() {
        register_fxml.setVisible(true);
        login_fxml.setVisible(false);
        login_loginLabel.setText("");
      //  admin_fxml.setVisible(false);
        login_loginLabel.setText("");
        login_usernameField.clear();
        login_passwordField.clear();
    }

    public void initialize() {
        login_usernameField.setOnKeyPressed(this::handleKeyPress);
        login_passwordField.setOnKeyPressed(this::handleKeyPress);
        //      inventory_addType.setItems(FXCollections.observableArrayList("Meal", "Coffee", "Pastries"));
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            login_loginButtonAction();
            event.consume();
        }
    }

    public void login_loginButtonAction() {
        PreparedStatement owgData = null;
        ResultSet owgResult = null;
        PreparedStatement creating = null;
        Connection owgConnection = ConnectionDB.getConnection();

        try {
            owgData = owgConnection.prepareStatement("SELECT * FROM owgCoffee WHERE (EMAIL = ? OR USERNAME = ?) AND BINARY aes_decrypt(PASSWORD, 'key1234') = ?");
            owgData.setString(1, login_usernameField.getText());
            owgData.setString(2, login_usernameField.getText());
            owgData.setString(3, login_passwordField.getText());
            owgResult = owgData.executeQuery();

            if (owgResult.next()) {
                // Drop and recreate the owgTransaction table
                try {
                    owgData = owgConnection.prepareStatement("DROP TABLE IF EXISTS owgTransaction");
                    int affectedRows = owgData.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Table owgTransaction dropped successfully.");
                    } else {
                        System.out.println("Table owgTransaction did not exist.");
                    }

                    creating = owgConnection.prepareStatement("CREATE TABLE owgTransaction (" +
                            "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                            "customer_id INT(100) NOT NULL, " +
                            "prod_name VARCHAR(100) NOT NULL, " +
                            "customer_username VARCHAR(100) NOT NULL, " +
                            "prod_amount INT(100) NOT NULL, " +
                            "prod_type VARCHAR(100) NOT NULL, " +
                            "prod_price DOUBLE NOT NULL)");
                    creating.executeUpdate();
                    System.out.println("Created the table owgTransaction again.");
                } catch (SQLException e) {
                    System.out.println("Failed to drop or create the table.");
                    e.printStackTrace();
                } finally {
                    if (creating != null) {
                        creating.close();
                    }
                }

                String type = owgResult.getString("TYPE");
                boolean isAdmin = type.equalsIgnoreCase("admin");
                name = owgResult.getString("FIRSTNAME");
                globalupdateID = String.valueOf(owgResult.getInt("ID"));
                phone = owgResult.getString("PHONE");
                email = owgResult.getString("EMAIL");
                lastname = owgResult.getString("LASTNAME");
                birthday = owgResult.getString("BIRTHDAY");
                address = owgResult.getString("ADDRESS");

                System.out.println(globalupdateID);

                if (isAdmin) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminUI.fxml"));
                        Parent root = fxmlLoader.load();

                        Stage stage = (Stage) login_loginButton.getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setFullScreen(false);
                        stage.show();
                        System.out.println("iwent admin");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuUI.fxml"));
                        Parent root = fxmlLoader.load();

                        Stage stage = (Stage) login_loginButton.getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setFullScreen(false);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                handleLoginFailure();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources(owgData, owgResult, owgConnection);
        }
    }

    private void handleLoginFailure() {
        if (login_usernameField.getText().isBlank() && login_passwordField.getText().isBlank()) {
            login_loginLabel.setStyle("-fx-text-fill: red;");
            login_loginLabel.setText("Please input your username or email and password");
        } else if (login_usernameField.getText().isBlank() && !login_passwordField.getText().isBlank()) {
            login_loginLabel.setStyle("-fx-text-fill: red;");
            login_loginLabel.setText("Please input your username or email");
        } else if (!login_usernameField.getText().isBlank() && login_passwordField.getText().isBlank()) {
            login_loginLabel.setStyle("-fx-text-fill: red;");
            login_loginLabel.setText("Please input your password");
        } else {
            login_loginLabel.setStyle("-fx-text-fill: red;");
            login_loginLabel.setText("Incorrect credentials. Please try again.");
        }
    }

    private void closeResources(PreparedStatement owgData, ResultSet owgResult, Connection owgConnection) {
        try {
            if (owgResult != null) {
                owgResult.close();
            }
            if (owgData != null) {
                owgData.close();
            }
            if (owgConnection != null) {
                owgConnection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    //REGISTER FUNCTIONS

    public void register_loginButtonActionRegister() {
        login_fxml.setVisible(true);
        register_fxml.setVisible(false);
      //  admin_fxml.setVisible(false);
        login_loginLabel.setText("");
        login_usernameField.clear();
        login_passwordField.clear();
    }

    public void register_registerButtonActionRegister() {
        PreparedStatement owgData = null;
        ResultSet owgResult = null;
        Connection owgConnection = null;
        try {
            owgConnection = ConnectionDB.getConnection();

            owgData = owgConnection.prepareStatement("SELECT * FROM owgCoffee WHERE EMAIL = ? OR USERNAME = ?");
            owgData.setString(1, register_emailFieldRegister.getText());
            owgData.setString(2, register_usernameFieldRegister.getText());
            owgResult = owgData.executeQuery();

            if (owgResult.next()) {
                register_errorMessageRegister.setStyle("-fx-text-fill: red;");
                register_errorMessageRegister.setText("Email or username already exists.");
            } else if (register_emailFieldRegister.getText().isBlank() || register_usernameFieldRegister.getText().isBlank() || register_passwordFieldRegister.getText().isBlank() || register_firstnameField.getText().isBlank() || register_lastnameField.getText().isBlank() || register_phoneField.getText().isBlank() || register_addressField.getText().isBlank()) {
                register_errorMessageRegister.setStyle("-fx-text-fill: red;");
                register_errorMessageRegister.setText("Please fill out all your credentials.");
            } else if (register_passwordFieldRegister.getLength() < 5) {
                register_errorMessageRegister.setStyle("-fx-text-fill: red;");
                register_errorMessageRegister.setText("Your password must atleast 5 digits/letter longer.");
            } else {
                String sql = "INSERT INTO owgCoffee (EMAIL, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, PHONE, ADDRESS, BIRTHDAY, PURCHASE, TYPE) " +
                        "VALUES (?, ?, aes_encrypt(?, 'key1234'), ?, ?, ?, ?, ?, 0, 'client')";

                owgData = owgConnection.prepareStatement(sql);
                owgData.setString(1, register_emailFieldRegister.getText());
                owgData.setString(2, register_usernameFieldRegister.getText());
                owgData.setString(3, register_passwordFieldRegister.getText());
                owgData.setString(4, register_firstnameField.getText());
                owgData.setString(5, register_lastnameField.getText());
                owgData.setString(6, register_phoneField.getText());
                owgData.setString(7, register_addressField.getText());
                LocalDate selectedDate = register_birthday.getValue();
                if (selectedDate != null) {
                    owgData.setString(8, selectedDate.toString());
                }
                else{
                    owgData.setNull(8, java.sql.Types.NULL);
                }
                //owgData.setString(8, birthday.getValue().toString());
                int rowsAffected = owgData.executeUpdate();
                if (rowsAffected > 0) {
                    register_fxml.setVisible(false);
                    login_fxml.setVisible(true);
                    //admin_fxml.setVisible(false);
                    login_loginLabel.setStyle("-fx-text-fill: #01FF13;");
                    //Color paint = new Color(0.0, 0.97, 0.1663, 1.0);
                    login_loginLabel.setText("Registration Successful");
                    login_usernameField.clear();
                    login_passwordField.clear();
                    register_emailFieldRegister.clear();
                    register_usernameFieldRegister.clear();
                    register_passwordFieldRegister.clear();
                    register_firstnameField.clear();
                    register_lastnameField.clear();
                    register_phoneField.clear();
                    register_addressField.clear();
                    register_birthday.setValue(null);
                //    listM = ConnectionDB.getDatausers();
                 //   admin_table_users.setItems(listM);
                } else {
                    register_errorMessageRegister.setStyle("-fx-text-fill: red;");
                    register_errorMessageRegister.setText("Registration Failed. Please try again.");
                }
            }
        } catch (SQLException ex) {
            register_errorMessageRegister.setStyle("-fx-text-fill: red;");
            register_errorMessageRegister.setText("update ang mysql any guba");
            ex.printStackTrace();
        } finally {
            try {
                if (owgResult != null) owgResult.close();
                if (owgData != null)
                    owgData.close();
                if (owgConnection != null) owgConnection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void register_birthdayRegister(ActionEvent event){
        LocalDate birthdate = register_birthday.getValue();
        //  System.out.println(birthdate.toString());
    }

    /* public static String getGlobalupdateID() {
        System.out.println(globalupdateID);
        return globalupdateID;
    }

     */






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

 /*       try {

            Connection owgConnection = ConnectionDB.getConnection();
            PreparedStatement owgData = owgConnection.prepareStatement("CREATE TABLE owgTransaction (" +
                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "customer_id INT(100) NOT NULL, " +
                    "prod_name VARCHAR(100) NOT NULL, " +
                    "customer_username VARCHAR(100) NOT NULL, " +
                    "prod_amount INT(100) NOT NULL, " +
                    "prod_price DOUBLE NOT NULL)");

            ResultSet owgResult = null;
            owgResult = owgData.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

  */

    }
}
