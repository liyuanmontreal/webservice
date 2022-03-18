package jac.learning.service;

import jac.learning.exception.DatabaseException;
import jac.learning.exception.ProductNotFoundException;
import jac.learning.model.Product;
import jac.learning.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService  {

    @Autowired
    private ProductRepository repository;

    static List<Product> productList = new ArrayList<>();

    // GET--get product list
    public List<Product> getProducts(){
        try {
            return this.repository.getAll();
        }catch (Exception exception){
            throw new DatabaseException(exception.getMessage());
        }

    }

    // PUT--add a product
    public int addProduct(Product product){
        return repository.saveProduct(product);
    }

    // GET --get product by id
    public Product getProductById(int id){
        try {
            Product product = this.repository.getProductById(id);
            if (product == null) {
                throw new ProductNotFoundException("The product not found");
            }
            return product;
        }catch (Exception exception){
            throw new ProductNotFoundException("The product not found");
        }
    }

    // GET --get product by sku_id
    public Product getProductBySid(String sid){
        try {
            Product product = this.repository.getProductBySid(sid);
            if (product == null) {
                throw new ProductNotFoundException("The product not found");
            }
            return product;
        }catch (Exception exception){
            throw new ProductNotFoundException("The product not found");
        }
    }

    //PUT - update Product
    public Product updateProduct(int id, Product product){
        return this.repository.updateProduct(id, product);
    }

    //Delete
    public Integer deleteProduct(int id){
        this.repository.deleteProduct(id);
        return 1;

    }


}

