package ip.webshop.service;

import java.util.List;
import ip.webshop.model.entity.Product;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    List<Product> getProductsByUserId(Long id);
    List<Product> getProductsByCategoryId(Long id);
    List<Product> getProductsByLocationId(Long id);
    List<Product> getProductsByLocationIdAndCategoryId(Long category_id,Long location_id);
    void addNewProduct(Product p);
}
