package owgdata;

import java.sql.Date;

public class receipt {

    int id, customer_id;
    String customer_username;
    double prod_total, prod_change;

    Date prod_date;

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }

    public void setProd_total(double prod_total) {
        this.prod_total = prod_total;
    }

    public void setProd_change(double prod_change) {
        this.prod_change = prod_change;
    }

    public void setProd_date(Date prod_date) {
        this.prod_date = prod_date;
    }

    public int getId() {
        return id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getCustomer_username() {
        return customer_username;
    }

    public double getProd_total() {
        return prod_total;
    }

    public double getProd_change() {
        return prod_change;
    }

    public Date getProd_date() {
        return prod_date;
    }

    public receipt(int id, int customer_id, String customer_username, double prod_total, double prod_change, Date prod_date){
        this.id = id;
        this.customer_id = customer_id;
        this.customer_username = customer_username;
        this.prod_total = prod_total;
        this.prod_change = prod_change;
        this.prod_date = prod_date;
    }


}
