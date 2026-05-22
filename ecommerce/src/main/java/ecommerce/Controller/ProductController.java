package ecommerce.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ecommerce.Model.Product;
import ecommerce.Service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins="http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return service.getProductById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        service.deleteProduct(id);
        return "Product Deleted Successfully";
    }
}