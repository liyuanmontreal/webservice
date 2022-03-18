package jac.learning.model;

public class Product {


    private int id; //This is an auto generated field by the database and is unique for each item.
    private String sku_id; // This is just an identifier of the product
    private String name; //Name of the product
    private String expiry_date;// Date on which the product would expire.
    private long days_to_expire_from_today; //the days left from today for the item to expire.


    public Product(int id, String sku_id, String name, String expiry_date, int days_to_expire_from_today) {
        this.id = id;
        this.sku_id = sku_id;
        this.name = name;
        this.expiry_date = expiry_date;
        this.days_to_expire_from_today = days_to_expire_from_today;
    }

    public Product() {
        this.id = 0;
        this.sku_id = "";
        this.name = "";
        this.expiry_date = "";
        this.days_to_expire_from_today = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public long getDays_to_expire_from_today() {
        return days_to_expire_from_today;
    }

    public void setDays_to_expire_from_today(long days_to_expire_from_today) {
        this.days_to_expire_from_today = days_to_expire_from_today;
    }


}
