package owgdata;

public class transaction {

    private String prod_name;

    private int ID;
    private int prod_amount;
    private Double prod_price;

    public transaction(int ID, String prod_name, int prod_amount, double prod_price) {
        this.ID = ID;
        this.prod_name = prod_name;
        this.prod_amount = prod_amount;
        this.prod_price = prod_price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProd_name() {
        return prod_name;
    }

    public int getProd_amount() {
        return prod_amount;
    }

    public Double getProd_price() {
        return prod_price;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public void setProd_amount(int prod_amount) {
        this.prod_amount = prod_amount;
    }

    public void setProd_price(Double prod_price) {
        this.prod_price = prod_price;
    }
}
