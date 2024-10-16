package owgdata;

public class delivery {

    int id, stock;
    String name, date;

    public void setId(int id) {

        this.id = id;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }
    public void setName(String name) {

        this.name = name ;
    }


    public void setDate(String date) {

        this.date = date ;
    }

    public int getId() {

        return id;
    }

    public int getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public String getDate() {

        return date;
    }

    public delivery(int id, String name, int stock, String date){
        this.id = id;
        this.stock = stock;
        this.name = name;
        this.date = date;
    }


}
