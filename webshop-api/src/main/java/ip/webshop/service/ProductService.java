package ip.webshop.service;

import java.util.List;
import ip.webshop.model.entity.Product;
import ip.webshop.model.request.ProductRequest;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    List<Product> getProductsByUserId(Long id);
    List<Product> getProductsByCategoryId(Long id);
    Product addNewProduct(ProductRequest request);
    Product endProduct(Long id);
    Product activateProduct(Long id);
    void deleteProduct(Long id);
}
