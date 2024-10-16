    package me.yohhan.dev.owgrevamp;
    
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.image.Image;
    import javafx.scene.input.MouseEvent;
    import javafx.scene.layout.AnchorPane;
    import javafx.scene.layout.GridPane;
    import javafx.scene.layout.StackPane;
    import javafx.scene.text.Text;
    import javafx.stage.Stage;
    import javafx.stage.StageStyle;
    import owgdata.*;
    
    import javax.swing.*;
    
    
    
    import java.io.IOException;
    import java.net.URL;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;
    import java.util.ResourceBundle;
    
    public class menuFunction implements Initializable {
    
    
    
    
    
        //   userFunction func1 = new userFunction();
        //RECEIPT FXMLs
        @FXML
        private Label receipt_change;
        @FXML
        private Label receipt_discount;
    
        @FXML
        private Label receipt_paid;
    
        @FXML
        private Label receipt_subtotal;
        @FXML
        private Label receipt_total;
    
        @FXML
        private Label receipt_vat;
    
    
        public static double receipt_changeR;
        public static String receipt_discountR;
        public static double receipt_paidR;
        public static double receipt_subtotalR;
        public static String receipt_totalR;
        public static String receipt_vatR;
        public static Double vatamount;
    
    
    
        //PROFILE LABELS
    
        adminFunction func1 = new adminFunction();
    
        ObservableList<users> listM;
    
        public static int soldproduct;

    

      //  @FXML
    //    private AnchorPane receipt_fxml;
    
    
    
    
    
    
        @FXML
        private Text profile_lastnamelabel;
    
        @FXML
        private Text profile_addresslabel;
    
        @FXML
        private Text profile_emaillabel;
    
        @FXML
        private Text profile_firstnamelabel;
    
        @FXML
        private Text profile_phonelabel;
    
        @FXML
        private Text profile_birthdaylabel;
    
    
        @FXML
        private Button contactUs9;
    
        @FXML
        private TextField menu_amount;
    
        @FXML
        private Text menu_promo;
    
        @FXML
        private Label menu_change;
    
        @FXML
        private TableColumn<transaction, Double> menu_col_price;
    
        @FXML
        private TableColumn<transaction, String> menu_col_productName;
    
        @FXML
        private TableColumn<transaction, Integer> menu_col_quantity;
    
        @FXML
        private Label menu_discount;
    
        @FXML
        private AnchorPane menu_fxml;
    
        @FXML
        private GridPane menu_gridPane;
    
        @FXML
        private Label menu_label;
    
        @FXML
        private Button menu_paymentButton;
    
        @FXML
        private Button menu_receiptButton;
    
        @FXML
        private ScrollPane menu_scrollPaneCoffee;
    
        @FXML
        private TableView<transaction> menu_tableView;
    
        @FXML
        private Label menu_total;
    
        @FXML
        private TextField menu_voucher;
    
        @FXML
        public Label menu_welcomeLabel;
    
        //MENU PROFILE
    
        @FXML
        private Button contactUs8;
    
        @FXML
        private TextField menu_Updateaddress;
    
        @FXML
        private DatePicker menu_Updatebirthday;
    
        @FXML
        private TextField menu_Updateemail;
    
        @FXML
        private TextField menu_Updatefirstname;
    
        @FXML
        private Label menu_Updatelabel;
    
        @FXML
        private TextField menu_Updatelastname;
    
        @FXML
        private PasswordField menu_Updatepassword;
    
        @FXML
        private TextField menu_Updatephone;
    
        @FXML
        private Text menu_profileemail;
    
        @FXML
        private Text menu_profileid;
    
        @FXML
        private Text menu_profilename;
    
        @FXML
        private Text menu_profilephone;
    
        @FXML
        private Button menu_Updateprofile;
    
        @FXML
        private Button menu_backAdmin;
    
        @FXML
        private StackPane profile_fxml;
    
        Connection owgConnection;
        PreparedStatement owgData;
        ResultSet owgResult;
    
        public static double totalP;
        private double percent;
        public static double discount;
        private int cID;
        private int getid;
    
        private Alert alert;
    
        @FXML
        private StackPane aboutus_fxml;
        @FXML
        private StackPane contactus_fxml;
        @FXML
        private StackPane vision_fxml;
        @FXML
        private StackPane mission_fxml;
        @FXML
        private StackPane values_fxml;
        @FXML
        private StackPane direction_fxml;
    
    
    
    
        private String globalupdateID = userFunction.globalupdateID;
    
    
    
    
    
        public static String name;
        private String phone;
        private String email;
        private String lastname;
    
        private double amount;
        private double change;
        private ObservableList<transaction> menuOrderListData;
    
        ObservableList<userreceipt> listU = ConnectionDB.getuserReceipt();
    
    
    
          /*      aboutus_fxml.setVisible(true);
            contactus_fxml.setVisible(false);
            vision_fxml.setVisible(false);
            mission_fxml.setVisible(false);
            values_fxml.setVisible(false);
            direction_fxml.setVisible(false);
            profile_fxml.setVisible(false);
    
           */
    
        public void menu_backAdmin(){
            try {
                int result = JOptionPane.showConfirmDialog(null,
                        "You are logging out from the shop. Are you sure?",
                        "OWG | COFFEESHOP ",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
    
                if (result == JOptionPane.OK_OPTION) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userUI.fxml"));
                        Parent root = fxmlLoader.load();
                        Stage stage = (Stage) menu_backAdmin.getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setFullScreen(false);
                        stage.show();
                    JOptionPane.showConfirmDialog(null,
                            "Successfully logged out.",
                            "OWG | COFFEESHOP ",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                    }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    
        }
    
        public void menuGetTotal() {
            customerID();
            owgConnection = ConnectionDB.getConnection();
    
            try {
                owgData = owgConnection.prepareStatement("SELECT SUM(prod_price) FROM owgTransaction WHERE customer_id = ?");
                owgData.setInt(1, cID);
                owgResult = owgData.executeQuery();
    
                if (owgResult.next()) {
                    if (menu_voucher.getText().isEmpty() || menu_voucher.getText().isBlank()) {
                        totalP = owgResult.getDouble("SUM(prod_price)");
                        receipt_subtotalR = totalP;
                        vatamount = totalP * (12.00 / 100);
                        totalP += vatamount;
                        System.out.println("Voucher field is blank");
                    } else {
                        applyVoucher();
                    }
                }
    
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                //     closeResources();
            }
        }
    
    
        @FXML
        void applyVoucher() {
            try {
                owgConnection = ConnectionDB.getConnection();
                String voucherCode = menu_voucher.getText();
    
                // Check if voucher exists
                owgData = owgConnection.prepareStatement("SELECT * FROM owgVouchers WHERE CODE = ?");
                owgData.setString(1, voucherCode);
                owgResult = owgData.executeQuery();
    
                if (owgResult.next()) {
                    percent = owgResult.getDouble("PERCENT");
    
                    // Calculate total price and apply discount
                    owgData = owgConnection.prepareStatement("SELECT SUM(prod_price) FROM owgTransaction WHERE customer_id = ?");
                    owgData.setInt(1, cID);
                    owgResult = owgData.executeQuery();
    
                    if (owgResult.next()) {
                        totalP = owgResult.getDouble("SUM(prod_price)");
                        totalP += vatamount;
                        discount = totalP * (percent / 100);
    
                        totalP -= discount;
                        System.out.println(discount);
                        System.out.println(percent);
    
                        System.out.println("Voucher exists");
                        menu_label.setText("Voucher Applied");
                        menu_total.setText(String.valueOf(totalP));
                        menu_discount.setText("₱"+(discount + "|" + (percent) + "%"));
    
                    }
                } else {
                    System.out.println("Voucher doesn't exist or expired");
                    menu_label.setText("Voucher doesn't exist or expired");
                }
    
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                //   closeResources();
            }
        }
    
        @FXML
        void menuSelectOrder(MouseEvent event) {
            transaction prod = menu_tableView.getSelectionModel().getSelectedItem();
            int num = menu_tableView.getSelectionModel().getSelectedIndex();
    
            if ((num - 1) < -1) {
                return;
            }
            // TO GET THE ID PER ORDER
            getid = prod.getID();
        }
    
        @FXML
        void menu_aboutus(ActionEvent event) {
            menu_fxml.setVisible(false);
            aboutus_fxml.setVisible(true);
            contactus_fxml.setVisible(false);
            vision_fxml.setVisible(false);
            mission_fxml.setVisible(false);
            values_fxml.setVisible(false);
            direction_fxml.setVisible(false);
            profile_fxml.setVisible(false);
    
        }
    
        @FXML
        void menu_contactus(ActionEvent event) {
            menu_fxml.setVisible(false);
            aboutus_fxml.setVisible(false);
            contactus_fxml.setVisible(true);
            vision_fxml.setVisible(false);
            mission_fxml.setVisible(false);
            values_fxml.setVisible(false);
            direction_fxml.setVisible(false);
            profile_fxml.setVisible(false);
    
        }
    
        @FXML
        void menu_direction(ActionEvent event) {
            menu_fxml.setVisible(false);
            aboutus_fxml.setVisible(false);
            contactus_fxml.setVisible(false);
            vision_fxml.setVisible(false);
            mission_fxml.setVisible(false);
            values_fxml.setVisible(false);
            direction_fxml.setVisible(true);
            profile_fxml.setVisible(false);
    
        }
    
        @FXML
        void menu_menu(ActionEvent event) {
            menu_fxml.setVisible(true);
            aboutus_fxml.setVisible(false);
            contactus_fxml.setVisible(false);
            vision_fxml.setVisible(false);
            mission_fxml.setVisible(false);
            values_fxml.setVisible(false);
            direction_fxml.setVisible(false);
            profile_fxml.setVisible(false);
        //    receipt_table.setItems(listU);
            ObservableList<transaction> listC = ConnectionDB.getTransaction();
            menu_tableView.setItems(listC);
            menuGetTotal();
            menu_total.setText("₱" + totalP);
            menu_amount.setText("");
            menu_change.setText("₱0.00");
            menu_discount.setText("₱0.00 | 0.00%");
            menu_label.setText("");
            System.out.println("this is from menu function sodl product" + soldproduct);
            menuShowOrderData();
    
        }
    
        @FXML
        void menu_mission(ActionEvent event) {
            menu_fxml.setVisible(false);
            aboutus_fxml.setVisible(false);
            contactus_fxml.setVisible(false);
            vision_fxml.setVisible(false);
            mission_fxml.setVisible(true);
            values_fxml.setVisible(false);
            direction_fxml.setVisible(false);
            profile_fxml.setVisible(false);
    
        }
    
        @FXML
        void menu_profile(ActionEvent event) {
            profile_fxml.setVisible(true);
            menu_fxml.setVisible(false);
            aboutus_fxml.setVisible(false);
            contactus_fxml.setVisible(false);
            vision_fxml.setVisible(false);
            mission_fxml.setVisible(false);
            values_fxml.setVisible(false);
            direction_fxml.setVisible(false);
            profile_fxml.setVisible(true);
    
        }
    
        @FXML
        void menu_values(ActionEvent event) {
            menu_fxml.setVisible(false);
            aboutus_fxml.setVisible(false);
            contactus_fxml.setVisible(false);
            vision_fxml.setVisible(false);
            mission_fxml.setVisible(false);
            values_fxml.setVisible(true);
            direction_fxml.setVisible(false);
            profile_fxml.setVisible(false);
    
        }
    
        @FXML
        void menu_vision(ActionEvent event) {
            menu_fxml.setVisible(false);
            aboutus_fxml.setVisible(false);
            contactus_fxml.setVisible(false);
            vision_fxml.setVisible(true);
            mission_fxml.setVisible(false);
            values_fxml.setVisible(false);
            direction_fxml.setVisible(false);
            profile_fxml.setVisible(false);
    
        }
        public ObservableList<transaction> menuGetOrder() {
            customerID();
            ObservableList<transaction> listData = FXCollections.observableArrayList();
    
            String sql = "SELECT * FROM owgTransaction WHERE customer_id = " + cID;
    
            owgConnection = ConnectionDB.getConnection();
    
            try {
    
                owgData = owgConnection.prepareStatement(sql);
                owgResult = owgData.executeQuery();
    
                transaction prod;
    
                while (owgResult.next()) {
                    prod = new transaction(owgResult.getInt("ID"),owgResult.getString("prod_name"),
                            owgResult.getInt("prod_amount"),
                            owgResult.getDouble("prod_price"));
                    listData.add(prod);
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            return listData;
        }
    
        public void menuShowOrderData() {
            menuOrderListData = menuGetOrder();
            menu_col_productName.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
            menu_col_price.setCellValueFactory(new PropertyValueFactory<>("prod_price"));
            menu_col_quantity.setCellValueFactory(new PropertyValueFactory<>("prod_amount"));
            menu_tableView.setItems(menuOrderListData);
    
        }
      /*  public void receiptButton(){
            receipt_col_prodname.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
            receipt_col_prodtype.setCellValueFactory(new PropertyValueFactory<>("prod_type"));
            receipt_col_quantity.setCellValueFactory(new PropertyValueFactory<>("prod_amount"));
            receipt_col_price.setCellValueFactory(new PropertyValueFactory<>("prod_price"));
            receipt_fxml.setVisible(true);
            menu_fxml.setVisible(false);
            receipt_subtotal.setText(receipt_subtotalR);
            receipt_vat.setText(vatamount +"| 12%");
            receipt_discount.setText(String.valueOf(discount));
            receipt_total.setText(String.valueOf(totalP));
            receipt_paid.setText(receipt_paidR);
            receipt_change.setText(receipt_changeR);
    

        }
        */

 /*       public void receiptbackButton(){
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("OWG COFFEE SHOP");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure? The receipt will be permanently deleted if you press "+ "OK" + ".");
            System.out.println(amount);
            Optional<ButtonType> option = alert.showAndWait();
    
            if (option.get().equals(ButtonType.OK)) {
                Connection owgConnection;
                //receipt_fxml.setVisible(false);
                menu_fxml.setVisible(true);
    
                receipt_subtotal.setText("₱0.00");
                receipt_vat.setText("₱0.00 | 12%");
                receipt_discount.setText("₱0.00 (0%)");
                receipt_total.setText("₱0.00");
                receipt_paid.setText("₱0.00");
                receipt_change.setText("₱0.00");
    
                PreparedStatement owgData;
                owgConnection = null;
                owgData = null;
                PreparedStatement creating = null;
    
                try {
                    owgConnection = ConnectionDB.getConnection();
    
                    try {
                        owgData = owgConnection.prepareStatement("DROP TABLE IF EXISTS owguserReceipt");
                        int affectedRows = owgData.executeUpdate();
    
                        if (affectedRows > 0) {
                            System.out.println("Table owguserReceipt dropped successfully.");
                        } else {
                            System.out.println("Table owguserReceipt did not exist.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Failed to drop the table.");
                        e.printStackTrace();
                        return;
                    } finally {
                        if (owgData != null) {
                            try {
                                owgData.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
    
    
                    try {
                        creating = owgConnection.prepareStatement("CREATE TABLE owguserReceipt (" +
                                "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                                "customer_id INT(100) NOT NULL, " +
                                "prod_name VARCHAR(100) NOT NULL, " +
                                "customer_username VARCHAR(100) NOT NULL, " +
                                "prod_amount INT(100) NOT NULL, " +
                                "prod_type VARCHAR(100) NOT NULL, " +
                                "prod_price DOUBLE NOT NULL)");
                        creating.executeUpdate();
                        System.out.println("Created the table owguserReceipt again.");
                    } catch (SQLException e) {
                        System.out.println("Failed to create the table.");
                        e.printStackTrace();
                    } finally {
                        if (creating != null) {
                            try {
                                creating.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
    
                } finally {
                    if (owgConnection != null) {
                        try {
                            owgConnection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
    
    
            }
        }*/
    
        @FXML
        void payButton(ActionEvent event) {
            menuGetTotal();
            // menuAmount();
    
    
    
            if (totalP == 0) {
                menu_label.setStyle("-fx-text-fill: red;");
                menu_label.setText("Choose your order first!");
    
            } else {
         //       String insertuserreceipt = "INSERT INTO owguserReceipt (customer_username, customer_id, prod_total, prod_date, prod_change) "
         //               + "VALUES(?,?,?,?,?)";
                String insertPay = "INSERT INTO owgReceipt (customer_username, customer_id, prod_total, prod_date, prod_change) "
                        + "VALUES(?,?,?,?,?)";
    
          //      ObservableList<userreceipt> listU = ConnectionDB.getuserReceipt();
            //    receipt_table.setItems(listU);
    
    
                owgConnection = ConnectionDB.getConnection();
    
                try {
    
                    if (Double.parseDouble(menu_amount.getText()) == 0 || Double.parseDouble(menu_amount.getText()) < totalP || menu_amount.getText().isEmpty() || Double.parseDouble(menu_amount.getText()) == 0 || String.valueOf(menu_amount.getText()).isBlank() || String.valueOf(menu_amount.getText()).isBlank() || String.valueOf(menu_amount.getText()).isEmpty()){
    
    
                        amount = Double.parseDouble(menu_amount.getText());
                        menu_label.setStyle("-fx-text-fill: red;");
                        menu_label.setText("You have to put the exact amount ");
                    } else {
                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Are you sure?");
                        System.out.println("ni pay ko and valid");
    
                        System.out.println(amount);
                        Optional<ButtonType> option = alert.showAndWait();
    
                        if (option.get().equals(ButtonType.OK)) {
                            customerID();
                            menuGetTotal();
                            amount = Double.parseDouble(menu_amount.getText());
                            receipt_paidR = Double.parseDouble(menu_amount.getText());
                            System.out.println("from ok" + amount);
                            change = amount - totalP;
                            System.out.println(amount);
                            System.out.println(totalP);
                            System.out.println(change);
                            menu_change.setText(String.format("₱%.2f", change));
                            receipt_changeR = change;
                            receipt_totalR = String.valueOf(totalP);
                            Alert alertreceipt = new Alert(Alert.AlertType.CONFIRMATION);
                            alertreceipt.setTitle("Receipt Generation");
                            alertreceipt.setHeaderText(null);
                            alertreceipt.setContentText("Do you want to generate your receipt?");
    
                            System.out.println(amount);
                            System.out.println("THIS IS LIST U" + listU);
                          //  receipt_table.setItems(listU);
                            Optional<ButtonType> optionreceipt = alertreceipt.showAndWait();
    
                            if (optionreceipt.get().equals(ButtonType.OK)) {
    /*
                                receipt_subtotal.setText(String.format("₱%.2f", receipt_subtotalR));
                                receipt_vat.setText(String.format("₱%.2f | 12%%", vatamount));
                                receipt_discount.setText(String.format("₱%.2f", discount));
                                receipt_total.setText(String.format("₱%.2f", totalP));
                                receipt_paid.setText(String.format("₱%.2f", receipt_paidR));
                                receipt_change.setText(String.format("₱%.2f", receipt_changeR));
    
                                //receipt_fxml.setVisible(true);
                                menu_fxml.setVisible(false);

     */
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("receiptUI.fxml"));
                                Parent root = fxmlLoader.load();
                               // Stage stage = (Stage) menu_Updateprofile.getScene().getWindow();
                                Stage stage = new Stage();
                                Scene scene = new Scene(root);
                                stage.setTitle("OWG | Coffee Shop");
                                Image icon = new Image("icon.png");
                                stage.initStyle(StageStyle.UNDECORATED);

                                stage.getIcons().add(icon);
                                stage.setResizable(false);
                                stage.setScene(scene);
                                stage.setFullScreen(false);
                                stage.show();
    
                            }
    
    
                            menu_voucher.clear();
                            //    menu_discount.setText(String.valueOf(discount + (percent / 100)));
                            owgData = owgConnection.prepareStatement(insertPay);
                            owgData.setString(1, userFunction.name);
                            owgData.setInt(2, cID);
                            owgData.setDouble(3, totalP);
                            menu_label.setStyle("-fx-text-fill: #01FF13;");
                            java.util.Date date = new java.util.Date();
    
                            //    Date date = new Date();
                            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    
                            owgData.setString(4, String.valueOf(sqlDate));
                            owgData.setDouble(5, change);
                            owgData.executeUpdate();
    
                            menu_label.setText("Thank you for purchasing OWG COFFEESHOP ");
                            
         //                   receipt_table.setItems(listU);
                      //      listP = ConnectionDB.getProducts();
                        //    inventory_table_products.setItems(listP);
                      //      listP1 = ConnectionDB.getProducts();
                            //inventory_table_products1.setItems(listP1);
                            owgConnection = null;
                            owgData = null;
                            owgConnection = ConnectionDB.getConnection();
                   //         receipt_table.setItems(listU);
    
    
    
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("OWG COFFEE SHOP");
                            alert.setHeaderText(null);
                            alert.setContentText("Successful.");
                         //   listP = ConnectionDB.getProducts();
                         //   inventory_table_products.setItems(listP);
                        //    listP1 = ConnectionDB.getProducts();
                       //     ObservableList<receipt> listR = ConnectionDB.getReceipt();
                         //   transasction_table.setItems(listR);
                         //   inventory_table_products1.setItems(listP1);
                            alert.showAndWait();
    
                            String receipt_prodname;
                            String receipt_prodtype;
                            int prod_amount;
                            int prod_price;
    
                            try {
                                owgData = owgConnection.prepareStatement("SELECT SUM(prod_amount) FROM owgTransaction WHERE customer_id = ?");
                                owgData.setInt(1, cID);
                                owgResult = owgData.executeQuery();
    
                                if (owgResult.next()) {
    
                                        soldproduct += owgResult.getInt("SUM(prod_amount)");
    
                                        try{
    
                                            owgData = null;
                                            owgResult = null;
                                            String selectProduct = "SELECT * FROM owgSoldProduct WHERE ID = ?" ;
                                            owgData = owgConnection.prepareStatement(selectProduct);
                                            owgData.setInt(1, 1);
                                            owgResult = owgData.executeQuery();
    
                                            if (owgResult.next()){
                                                owgData = null;
                                                owgData = owgConnection.prepareStatement("UPDATE owgSoldProduct SET prod_amount = prod_amount + ? WHERE ID = ?");
                                             //   String updateAmount = "UPDATE owgSoldProduct SET prod_amount = prod_amount + ? WHERE ID = ?";
    
                                                owgData.setInt(1, soldproduct);
                                                owgData.setInt(2, 1);
                                                owgData.executeUpdate();
                                            }
                                            //            String insertPay = "INSERT INTO owgReceipt (customer_username, customer_id, prod_total, prod_date, prod_change) "
                                        } catch (Exception e) {
                                            throw new RuntimeException(e);
                                        }
                                }
    
                            } catch (SQLException e) {
                                e.printStackTrace();
                                throw new RuntimeException(e);
                            } finally {
                                //     closeResources();
                            }
    
                            Connection owgConnection;
                            PreparedStatement owgData;
                            owgConnection = null;
                            owgData = null;
                            PreparedStatement creating = null;
    
                            try {
                                owgConnection = ConnectionDB.getConnection();
    
                                try {
                                    owgData = owgConnection.prepareStatement("DROP TABLE IF EXISTS owgTransaction");
                                    int affectedRows = owgData.executeUpdate();
    
                                    if (affectedRows > 0) {
                                        System.out.println("Table owgTransaction dropped successfully.");
                                    } else {
                                        System.out.println("Table owgTransaction did not exist.");
                                    }
                                } catch (SQLException e) {
                                    System.out.println("Failed to drop the table.");
                                    e.printStackTrace();
                                    return;
                                } finally {
                                    if (owgData != null) {
                                        try {
                                            owgData.close();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
    
    
                                try {
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
                                    System.out.println("Failed to create the table.");
                                    e.printStackTrace();
                                } finally {
                                    if (creating != null) {
                                        try {
                                            creating.close();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
    
                            } finally {
                                if (owgConnection != null) {
                                    try {
                                        owgConnection.close();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            ObservableList<transaction> listC = ConnectionDB.getTransaction();
                            menu_tableView.setItems(listC);
                            menuGetTotal();
                            menu_total.setText("₱" + "0.0");
                            menu_amount.setText("");
                            menu_change.setText("₱0.00");
                            menu_discount.setText("₱0.00 | 0.00%");
                            menu_label.setText("");
                            menuShowOrderData();
    
    
                        } else {
                            alert = new Alert(Alert.AlertType.WARNING);
    
                            alert.setTitle("Infomation Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Cancelled.");
                            alert.showAndWait();
                        }
                    }
    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    
        public void menu_Updateprofile() throws SQLException {
            globalupdateID = userFunction.globalupdateID;
    
            Connection owgConnection = null;
            try {
                owgConnection = ConnectionDB.getConnection();
                if (menu_Updatepassword.getLength() < 5) {
                    menu_Updatelabel.setStyle("-fx-text-fill: red;");
                    menu_Updatelabel.setText("Your password must be at least 5 characters long.");
                    return;
                }
    
                List<String> fields = new ArrayList<>();
                List<Object> values = new ArrayList<>();
    
                if (!menu_Updateemail.getText().isBlank()) {
                    fields.add("EMAIL = ?");
                    values.add(menu_Updateemail.getText());
                }
                if (!menu_Updatepassword.getText().isBlank()) {
                    fields.add("PASSWORD = aes_encrypt(?, 'key1234')");
                    values.add(menu_Updatepassword.getText());
                    System.out.println(menu_Updatepassword);
                }
                if (!menu_Updatefirstname.getText().isBlank()) {
                    fields.add("FIRSTNAME = ?");
                    values.add(menu_Updatefirstname.getText());
                }
                if (!menu_Updatelastname.getText().isBlank()) {
                    fields.add("LASTNAME = ?");
                    values.add(menu_Updatelastname.getText());
                }
                if (!menu_Updatephone.getText().isBlank()) {
                    fields.add("PHONE = ?");
                    values.add(menu_Updatephone.getText());
                }
                if (!menu_Updateaddress.getText().isBlank()) {
                    fields.add("ADDRESS = ?");
                    values.add(menu_Updateaddress.getText());
                }
                LocalDate selectedDate = menu_Updatebirthday.getValue();
                if (selectedDate != null) {
                    fields.add("BIRTHDAY = ?");
                    values.add(selectedDate.toString());
                }
    
                if (fields.isEmpty()) {
                    menu_Updatelabel.setStyle("-fx-text-fill: red;");
                    menu_Updatelabel.setText("Please fill out at least one field.");
                    return;
                }
    
                String sql = "UPDATE owgCoffee SET " + String.join(", ", fields) + " WHERE ID = ?";
                values.add(globalupdateID);
    
                PreparedStatement owgData = owgConnection.prepareStatement(sql);
                for (int i = 0; i < values.size(); i++) {
                    owgData.setObject(i + 1, values.get(i));
                }
    
                int rowsAffected = owgData.executeUpdate();
                if (rowsAffected > 0) {
                    menu_Updatelabel.setStyle("-fx-text-fill: #01FF13;");
                    menu_Updatelabel.setText("Updated Successfully");
    
                    profile_fxml.setVisible(false);
    
                    menu_Updatepassword.clear();
                    menu_Updateemail.clear();
                    menu_Updatefirstname.clear();
                    menu_Updatelastname.clear();
                    menu_Updatephone.clear();
                    menu_Updatebirthday.setValue(null);
                    menu_Updateaddress.clear();
    
                    int result = JOptionPane.showConfirmDialog(null,
                            "You are updating your account information. Are you sure?",
                            "OWG | COFFEESHOP ",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
    
                    if (result == JOptionPane.OK_OPTION) {
                        int result2 = JOptionPane.showConfirmDialog(null,
                                "Successfully updated your account.",
                                "OWG | COFFEESHOP ",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                        if (result2 == JOptionPane.OK_OPTION) {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userUI.fxml"));
                            Parent root = fxmlLoader.load();
                            Stage stage = (Stage) menu_Updateprofile.getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setFullScreen(false);
                            stage.show();
                        }
                    }
                } else {
                    menu_Updatelabel.setStyle("-fx-text-fill: red;");
                    menu_Updatelabel.setText("Update Failed. Please try again.");
                }
            } catch (SQLException ex) {
                menu_Updatelabel.setStyle("-fx-text-fill: red;");
                menu_Updatelabel.setText("An error occurred while updating the database.");
                ex.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (owgConnection != null) {
                    owgConnection.close();
                }
            }
        }
    
        @FXML
        void removeButton(ActionEvent event) {
    
            if (getid == 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the order you want to remove");
                alert.showAndWait();
            } else {
                String deleteData = "DELETE FROM owgTransaction WHERE id = " + getid;
                String receiptData = "DELETE FROM owguserReceipt WHERE id = " + getid;
    
                owgConnection = ConnectionDB.getConnection();
                try {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete this order?");
                    Optional<ButtonType> option = alert.showAndWait();
    
                    if (option.get().equals(ButtonType.OK)) {
                        owgData = owgConnection.prepareStatement(deleteData);
                        owgData.executeUpdate();
                        owgData = null;
                        owgData = owgConnection.prepareStatement(receiptData);
                        owgData.executeUpdate();
    
                    }
    
                    menuShowOrderData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
    
            }
    
        }
    
    
        public void customerID(){
    
            String sql = "SELECT MAX(customer_id) FROM owgTransaction";
    
            owgConnection = ConnectionDB.getConnection();
            try{
                owgData = owgConnection.prepareStatement(sql);
                owgResult = owgData.executeQuery();
    
                if(owgResult.next()){
                    cID = owgResult.getInt("MAX(customer_id)");
    
                }
                String checkCID = "SELECT MAX(customer_id) FROM owgReceipt";
    
                owgData = owgConnection.prepareStatement(checkCID);
                owgResult = owgData.executeQuery();
    
                int checkID = 0;
    
                if (owgResult.next()){
                    checkID = owgResult.getInt("MAX(customer_id)");
    
                }
    
                if(cID == 0 ){
                    cID+=1;
    
                }
                else if(cID == checkID){
                    cID +=1;
                }
                data.cID = cID;
    
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    
    
        }
        public ObservableList<products> menuGetData() {
    
            PreparedStatement owgData = null;
            ResultSet owgResult = null;
            Connection owgConnection = null;
            ObservableList<products> listData = FXCollections.observableArrayList();
            try {
                owgConnection = ConnectionDB.getConnection();
    
                owgData = owgConnection.prepareStatement("SELECT * FROM owgProducts");
                owgResult = owgData.executeQuery();
    
                products prod;
    
                while(owgResult.next()){
                    prod = new products(
                            owgResult.getInt("ID"),
                            owgResult.getString("NAME"),
                            owgResult.getDouble("PRICE"),
                            owgResult.getString("TYPE"),
                            owgResult.getInt("STOCK")
                    );
                    listData.add(prod);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return listData;
        }
        private ObservableList<products> productsData = FXCollections.observableArrayList();
        public void menuDisplay(){
            productsData.clear();
            productsData.addAll(menuGetData());
    
    
            int row = 0;
            int column = 0;
            menu_gridPane.getRowConstraints().clear();
            menu_gridPane.getColumnConstraints().clear();
    
            for(int i = 0 ; i < productsData.size(); i++){
    
                try{
                    FXMLLoader load = new FXMLLoader();
                    load.setLocation(getClass().getResource("productDisplay.fxml"));
                    AnchorPane pane = load.load();
                    productOrdering productC = load.getController();
                    productC.setData(productsData.get(i));
    
                    if(column == 3){
                        column = 0;
                        row+=1;
    
                    }
    
                    menu_gridPane.add(pane, column++, row);
                    //GridPane.setMargin(pane);
    
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
    
            }
        }
        public void menuDisplayTotal() {
            menuGetTotal();
            menu_total.setText("₱" + totalP);
            System.out.println(totalP);
        }
    
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            aboutus_fxml.setVisible(false);
            contactus_fxml.setVisible(false);
            vision_fxml.setVisible(false);
            mission_fxml.setVisible(false);
            values_fxml.setVisible(false);
            direction_fxml.setVisible(false);
            profile_fxml.setVisible(false);
            String promo_message = null;
            try{
    
                owgConnection = ConnectionDB.getConnection();
    
    
                owgData = null;
                owgData = owgConnection.prepareStatement("SELECT * FROM owgPromo WHERE ID = 1");
                owgResult = owgData.executeQuery();
    
                if (owgResult.next()) {
                    promo_message = owgResult.getString("promo_message");
    
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    
            menu_promo.setText(promo_message);
            System.out.println("this is the admin fucntion promo" + promo_message);
    
    
            //INFORMATION DISPLAY
            menu_welcomeLabel.setText("WELCOME, " + userFunction.name);
            menu_profileid.setText(userFunction.name + " " + userFunction.lastname);
            menu_profilephone.setText(userFunction.phone);
            menu_profileemail.setText(userFunction.email);
    
    
    
            //PROFILE INFORMATION DISPLAY
            profile_lastnamelabel.setText(userFunction.lastname);
            profile_firstnamelabel.setText(userFunction.name);
            profile_addresslabel.setText(userFunction.address);
            profile_emaillabel.setText(userFunction.email);
            profile_phonelabel.setText(userFunction.phone);
            profile_birthdaylabel.setText(userFunction.birthday);
            menuDisplay();
            menuGetOrder();
            menuDisplayTotal();
            menuShowOrderData();
    
    
            Connection owgConnection;
            PreparedStatement owgData;
            owgConnection = null;
            owgData = null;
            PreparedStatement creating = null;
    
            try {
                owgConnection = ConnectionDB.getConnection();
    
                try {
                    owgData = owgConnection.prepareStatement("DROP TABLE IF EXISTS owguserReceipt");
                    int affectedRows = owgData.executeUpdate();
    
                    if (affectedRows > 0) {
                        System.out.println("Table owguserReceipt dropped successfully.");
                    } else {
                        System.out.println("Table owguserReceipt did not exist.");
                    }
                } catch (SQLException e) {
                    System.out.println("Failed to drop the table.");
                    e.printStackTrace();
                    return;
                } finally {
                    if (owgData != null) {
                        try {
                            owgData.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
    
    
                try {
                    creating = owgConnection.prepareStatement("CREATE TABLE owguserReceipt (" +
                            "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                            "customer_id INT(100) NOT NULL, " +
                            "prod_name VARCHAR(100) NOT NULL, " +
                            "customer_username VARCHAR(100) NOT NULL, " +
                            "prod_amount INT(100) NOT NULL, " +
                            "prod_type VARCHAR(100) NOT NULL, " +
                            "prod_price DOUBLE NOT NULL)");
                    creating.executeUpdate();
                    System.out.println("Created the table owguserReceipt again.");
                } catch (SQLException e) {
                    System.out.println("Failed to create the table.");
                    e.printStackTrace();
                } finally {
                    if (creating != null) {
                        try {
                            creating.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
    
            } finally {
                if (owgConnection != null) {
                    try {
                        owgConnection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
    
    
        }
    }
