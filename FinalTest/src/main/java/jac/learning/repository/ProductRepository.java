package jac.learning.repository;

import jac.learning.exception.DatabaseException;
import jac.learning.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

   // Get Product List
    public List<Product> getAll(){
        try {
            List<Product> productList = jdbcTemplate.query("SELECT id,sku_id,product_name,expiry_date FROM product",
                    (rs, rowNum) ->
                            new Product(rs.getInt("id"),
                                    rs.getString("sku_id"),
                                    rs.getString("product_name"),
                                    rs.getString("expiry_date"),0
                            ));
            //edit the value of expiry_date for each productList
           for (Product pdt : productList) {
                pdt.setDays_to_expire_from_today(CalDateDiff(pdt.getExpiry_date()));
            }

            return productList;
        }catch (DatabaseException exception){
            throw  new DatabaseException(exception.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long CalDateDiff(String date) throws ParseException {
        long days = 0;
        try {
            SimpleDateFormat formatter=new SimpleDateFormat("dd MMM,yyyy");
            Date d1=formatter.parse(date);
            Date today = new Date();

            long diff = d1.getTime() -today.getTime();
            days = diff / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
        }
        return days;
    }

    //POST - Add a Product
    public int saveProduct(Product product) {
        jdbcTemplate.update("INSERT INTO Product(sku_id,product_name,expiry_date) VALUES(?,?,?)", product.getSku_id(),product.getName(),product.getExpiry_date());

        //how can I get the inserted id
        return jdbcTemplate.queryForObject("SELECT max(id) from product", Integer.class);
    }


    //GET - get product by id
    public Product getProductById(int id){
        try {
            String sql = "SELECT id, sku_id,product_name,expiry_date FROM Product WHERE ID = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductRowMapper());
        }catch (DatabaseException exception){
            throw new DatabaseException("database error");
        }
    }


    //GET - get product by sku_id
    public Product getProductBySid(String sid){
        try {
            String sql = "SELECT id, sku_id,product_name,expiry_date FROM Product WHERE sku_id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{sid}, new ProductRowMapper());
        }catch (DatabaseException exception){
            throw new DatabaseException("database error");
        }
    }

    //PUT - update product by id
    public Product updateProduct(int id, Product product){
        String sql = "UPDATE product set sku_id=?,product_name=?,expiry_date=? where id=?";
        int result = jdbcTemplate.update(sql, product.getSku_id(),product.getName(),product.getExpiry_date(), id);
        if (result == 1){
            product.setId(id);
            return product;
        }
        return null;
    }


    //Delete - delete product by id
    public void deleteProduct(int id){
        try {
            jdbcTemplate.update("DELETE from product where id=?", id);
        }catch (DatabaseException exception){
            throw new DatabaseException(exception.getMessage());
        }
    }
}
