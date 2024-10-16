package owgdata;

public class vouchers {

    int id;
    String code;
    Double percent;

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Double getPercent() {
        return percent;
    }

    public vouchers(int id, String code, double percent){
        this.id = id;
        this.code = code;
        this.percent = percent;


    }
}
