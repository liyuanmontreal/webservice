package jac.learning.repository;

import jac.learning.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ProductRowMapper implements RowMapper<Product> {
    @Autowired
    private ProductRepository repository;

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {


        Product pdt = new Product();
        pdt.setId(rs.getInt("ID"));
        pdt.setSku_id(rs.getString("sku_id"));
        pdt.setName(rs.getString("product_name"));
        pdt.setExpiry_date(rs.getString("expiry_date"));
        try {
            pdt.setDays_to_expire_from_today(dateDiff(pdt.getExpiry_date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /*try {
            pdt.setDays_to_expire_from_today(repository.CalDateDiff(pdt.getExpiry_date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        return pdt;
        //return new Product(rs.getInt("ID"), rs.getString("sku_id"),rs.getString("product_name"),rs.getString("expiry_date"),0);
    }

    private long dateDiff(String date) throws ParseException {
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


}
