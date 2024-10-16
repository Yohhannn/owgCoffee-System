package me.yohhan.dev.owgrevamp;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import owgdata.ConnectionDB;
import owgdata.data;
import owgdata.products;
import owgdata.transaction;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class productOrdering implements Initializable {

    @FXML
    private AnchorPane menu_prodcard;

    @FXML
    private Text menu_prodname;
    private Alert alert;

    private String productID;

    public String getNameReceipt;

    private static String type;
    private String date;
    @FXML
    private Text menu_prodprice;

    @FXML
    private Spinner<Integer> menu_prodspinner;

    @FXML
    private Text menu_prodstock;

    @FXML
    private Text menu_prodtype;

    private products prodData;


    private Connection owgConnection;
    private PreparedStatement owgData;

    private ResultSet owgResult;

    public int receipt_cID;
    public String receipt_prodname;
    public String receipt_qty;

    public Double receipt_totalPrice;
    private static String checkType;



             /*           owgData.setInt(1, data.cID);
                    owgData.setString(2, mainFunction.name);
                    owgData.setString(3, menu_prodname.getText());
                    owgData.setString(4, String.valueOf(qty));
    totalPrice = (qty * pr);
    .                    owgData.setDouble(5, Double.parseDouble(String.valueOf(totalPrice)));


              */



    private SpinnerValueFactory<Integer> spin;
    private SpinnerValueFactory<Integer> test;
  //  private Label menu_label;


    public void setData(products prodData){
        this.prodData = prodData;

   //     productID = prodData.getName();

        productID = String.valueOf(prodData.getId());

        type = prodData.getType();
        menu_prodname.setText(prodData.getName());
        menu_prodprice.setText(String.valueOf(prodData.getPrice()));
        menu_prodtype.setText(prodData.getType());
        menu_prodstock.setText(String.valueOf(prodData.getStock()));
        pr = prodData.getPrice();

    }
    public String officalprodname;
    public void setNameData(products prodData){
        this.prodData = prodData;

        //     productID = prodData.getName();


        officalprodname = prodData.getName();

    }

    public int qty;
    public void setQuantity(){
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        menu_prodspinner.setValueFactory(spin);
    }



    public double totalPrice = 0.00;
    private double pr;

    public int upStock = 0;



    public void addButton(){
        menuFunction mainForm = new menuFunction();
        mainForm.customerID();


        owgConnection = null;
        owgData = null;
        owgResult = null;

        int newStock = 0;

        try {

            owgConnection = ConnectionDB.getConnection();
            owgData = owgConnection.prepareStatement("SELECT stock FROM owgProducts WHERE id = '" + productID + "'");
            owgResult = owgData.executeQuery();

            if (owgResult.next()){
                newStock = owgResult.getInt("STOCK");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        menu_prodstock.setText(String.valueOf(newStock));


        qty = menu_prodspinner.getValue();



        String check = "";
        String checkAvail = "SELECT status FROM owgProducts WHERE id = '"
                + productID + "'";

        owgConnection = ConnectionDB.getConnection();


        try{


            int checkStocks = 0;
            String checkStock = "SELECT STOCK, TYPE FROM owgProducts WHERE NAME = ?";

            owgData = owgConnection.prepareStatement(checkStock);
            owgData.setString(1, menu_prodname.getText());
            owgResult = owgData.executeQuery();

            if (owgResult.next()){
                checkStocks = owgResult.getInt("STOCK");
                checkType = owgResult.getString("TYPE");

            }
            owgData = owgConnection.prepareStatement(checkAvail);
            owgResult = owgData.executeQuery();


            if(owgResult.next()){
                check = owgResult.getString("STATUS");

            }
                if(!check.equals("Available") || checkStocks == 0){

                  //  menu_label.setText("nay guba yawa");
                    System.out.println("The product is unavailable");
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Product Status");

                  //  alert.titleProperty().d
                    ImageView icon = new ImageView("icon.png");
                    alert.setHeaderText("OWG COFFEESHOP");
                    alert.setContentText("Product is unavailable.\n\n2024 | BSIT 1-B2 | All Rights Reserved"); //\n\n- OWG MANAGEMENT");
          //          System.out.println("ni pay ko and valid");
                  //  Optional<ButtonType> option = alert.showAndWait();
                    icon.setFitHeight(48);
                    icon.setFitWidth(48);



                    alert.getDialogPane().setGraphic(icon);
                    alert.setGraphic(icon);
                    //   alert.getGraphic(icon);
                    alert.showAndWait();
               //     alert.setGraphic(icon);
               //    alert.getButtonTypes().add()

                }
            else if(qty == 0){
                System.out.println("you need to add a quantity!");
            }
            else {
                if(checkStocks <  qty){
               //     menu_label.setText("This product is out ef stock");
                    System.out.println("product is out of stock");
                }
                else {

                    //  System.out.println(productID);
                    String insertData = "INSERT INTO owgTransaction (customer_id, customer_username, prod_name, prod_amount, prod_type, prod_price)VALUES(?,?,?,?,?,?)";

                    //                owgData = owgConnection.prepareStatement("INSERT INTO owgCoffee (EMAIL, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, PHONE, ADDRESS, BIRTHDAY, PURCHASE, TYPE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0, 'client')");


                    owgData = owgConnection.prepareStatement(insertData);


                    System.out.println("ni sud ko balik");

                    System.out.println("tEST" + data.cID);
                    System.out.println(userFunction.name);



                    owgData.setInt(1, data.cID);
                    owgData.setString(2, userFunction.name);
                    owgData.setString(3, menu_prodname.getText());
                    owgData.setString(4, String.valueOf(qty));
                    owgData.setString(5, checkType);

                    totalPrice = (qty * pr);
                    owgData.setDouble(6, Double.parseDouble(String.valueOf(totalPrice)));

                    owgData.executeUpdate();

                    owgData = null;

                    String usereceipt = "INSERT INTO owguserReceipt (customer_id, prod_name, customer_username, prod_amount, prod_type, prod_price)VALUES(?,?,?,?,?,?)";

                    //                owgData = owgConnection.prepareStatement("INSERT INTO owgCoffee (EMAIL, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, PHONE, ADDRESS, BIRTHDAY, PURCHASE, TYPE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0, 'client')");


                    owgData = owgConnection.prepareStatement(usereceipt);


                    System.out.println("ni sud ko balik");

                    System.out.println("tEST" + data.cID);
                    System.out.println(userFunction.name);



                    owgData.setInt(1, data.cID);
                    owgData.setString(3, userFunction.name);
                    owgData.setString(2, menu_prodname.getText());
                    owgData.setString(4, String.valueOf(qty));
                    owgData.setString(5, checkType);

                    totalPrice = (qty * pr);
                    owgData.setDouble(6, Double.parseDouble(String.valueOf(totalPrice)));

                    owgData.executeUpdate();

                    owgConnection = null;
                    owgData = null;
                    ResultSet owgResult = null;
                    owgConnection = ConnectionDB.getConnection();



                    upStock = checkStocks - qty;

                    String updateStock = "UPDATE owgProducts SET STOCK = ?, TYPE = ?, PRICE = ?, STATUS = ? WHERE NAME = ? AND ID = ?";
                    owgData = owgConnection.prepareStatement(updateStock);



//                    owgData = owgConnection.prepareStatement(updateStock);
                    owgData.setInt(1, upStock);
              //      System.out.println("tEST"+upStock);
                    owgData.setString(2, menu_prodtype.getText());
                    owgData.setDouble(3, pr);
                    owgData.setString(4, check);
                    owgData.setString(5, menu_prodname.getText());
                    owgData.setString(6, productID);

                    owgData.executeUpdate();


                    System.out.println("SUCCESS ADD PRODUCT");
                 //   Integer test = 0;
                    test = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
                    menu_prodspinner.setValueFactory(test);
                    //updateLabel("TEST");
                    menu_prodstock.setText(String.valueOf(upStock));
         //           menuFunction func1 = new menuFunction();

                    if(upStock == 0){
                        String updateStocksecond = "UPDATE owgProducts SET STATUS = 'Unavailable' WHERE NAME = ? AND ID = ?";
                        owgData = owgConnection.prepareStatement(updateStocksecond);

                        owgData.setString(1, menu_prodname.getText());
                        owgData.setString(2, productID);

                        owgData.executeUpdate();

                    }

                }

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setQuantity();

    }

    public products getProdData() {
        return prodData;
    }

    public void setProdData(products prodData) {
        this.prodData = prodData;
    }
}
