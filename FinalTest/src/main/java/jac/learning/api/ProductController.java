package jac.learning.api;

import jac.learning.exception.ProductNotFoundException;
import jac.learning.model.Product;
import jac.learning.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    //---------GET--------
    //use "http://localhost:8080/product" to test
    @GetMapping("/product")  //GET PRODUCT LIST
    public ResponseEntity<List<Product>> getAll(){
        try {
            return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }

    //---------POST--------
    //use "http://localhost:8080/welcome/product" to test
    /*
        {
        "sku_id": "pb6",
        "name": "Milk",
        "expiry_date": "18 April,2022"
    }
     */
    @PostMapping("/welcome/product")   //POST API to create a new product
       public ResponseEntity<Integer> postProduct(@RequestBody Product product){
        return new ResponseEntity<>(service.addProduct(product), HttpStatus.CREATED);
    }


    //---------GET--------
    //use "http://localhost:8080/product/id/2" to test
    @GetMapping("/product/id/{id}") //GET API to get one product by id
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        try {
            return new ResponseEntity<>(service.getProductById(id), HttpStatus.OK);
        }catch(ProductNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //---------GET--------
    //use "http://localhost:8080/product/sid/iy4169" to set
    @GetMapping("/product/sid/{sid}") //GET API to get one product by sku_id
    public ResponseEntity<Product> getProductBySid(@PathVariable String sid){
        try {
            return new ResponseEntity<>(service.getProductBySid(sid), HttpStatus.OK);
        }catch(ProductNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //---------PUT--------
    // use "http://localhost:8080/product/4" to test
    /*
    {
        "id": 4,
        "sku_id": "p46",
        "name": "Milk2",
        "expiry_date": "18 April,2022"
    }
     */
    //PUT API to update one product by id
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable int id){
        return new ResponseEntity<>(service.updateProduct(id, product), HttpStatus.OK);
    }


    //---------DELETE--------
    //use "http://localhost:8080/product/你想删除的id号" to test
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Integer> deleteProduct(@PathVariable int id){
        return new ResponseEntity<>(service.deleteProduct(id), HttpStatus.NO_CONTENT);
    }


}

