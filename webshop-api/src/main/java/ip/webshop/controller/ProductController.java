package ip.webshop.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import ip.webshop.model.entity.Product;
import ip.webshop.model.enumeration.LogType;
import ip.webshop.model.request.ProductRequest;
import ip.webshop.service.LogService;
import ip.webshop.service.ProductService;
import ip.webshop.service.UserService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        logService.writeLog(LogType.INFO, "/api/products/", "Request users products", Instant.now());
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/home")
    public ResponseEntity<?> getForHome() {
        logService.writeLog(LogType.INFO, "/api/products/home/", "Request users products", Instant.now());
        List<Product>list=productService.getAllProducts();
        int index=(list.size()>5)?4:list.size()-1;
        List<Product> newList=list.subList(0, (index<0)?0:index);
        return ResponseEntity.ok(newList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            logService.writeLog(LogType.WARNING, "/api/products/" + id, "Request users product failed " + id,
                    Instant.now());
            return ResponseEntity.notFound().build();
        } else {
            logService.writeLog(LogType.INFO, "/api/products/" + id, "Request users product " + id, Instant.now());
            return ResponseEntity.ok(product);
        }

    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getProductByUserId(@PathVariable String username) {
        Long id = userService.getUserByUsername(username).getId();
        List<Product> product = productService.getProductsByUserId(id);
        if (product == null) {
            logService.writeLog(LogType.WARNING, "/api/products/" + id, "Request users product " + id + " not found",
                    Instant.now());
            return ResponseEntity.notFound().build();
        } else {
            logService.writeLog(LogType.INFO, "/api/products/" + id, "Request users product " + id, Instant.now());
            return ResponseEntity.ok(product);
        }

    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest request) {
        logService.writeLog(LogType.INFO, "/api/products/", "Request creating product ", Instant.now());
        return ResponseEntity.ok(productService.addNewProduct(request));
    }

    @GetMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateProduct(@PathVariable Long id) {
        Product product = productService.endProduct(id);
        if (product == null) {
            logService.writeLog(LogType.WARNING, "/api/products/" + id, "Request deactivate product ", Instant.now());
            return ResponseEntity.badRequest().build();
        } else {
            logService.writeLog(LogType.INFO, "/api/products/" + id, "Request deactivate product ", Instant.now());
            return ResponseEntity.ok(product);
        }

    }

    @GetMapping("/activate/{id}")
    public ResponseEntity<?> activateProduct(@PathVariable Long id) {
        Product product = productService.activateProduct(id);
        if (product == null) {
            logService.writeLog(LogType.WARNING, "/api/products/" + id, "Request activate product ", Instant.now());
            return ResponseEntity.badRequest().build();
        }
        else {
            logService.writeLog(LogType.INFO, "/api/products/" + id, "Request activate product ", Instant.now());
            return ResponseEntity.ok(product);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        logService.writeLog(LogType.INFO, "/api/products/" + id, "Request delete product ", Instant.now());
        return ResponseEntity.ok(id);

    }
}
