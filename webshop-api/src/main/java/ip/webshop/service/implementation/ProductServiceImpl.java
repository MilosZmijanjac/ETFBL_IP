package ip.webshop.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import ip.webshop.model.entity.Product;
import ip.webshop.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getAllProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'");
    }

    @Override
    public Product getProductById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductById'");
    }

    @Override
    public List<Product> getProductsByUserId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByUserId'");
    }

    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByCategoryId'");
    }

    @Override
    public List<Product> getProductsByLocationId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByLocationId'");
    }

    @Override
    public List<Product> getProductsByLocationIdAndCategoryId(Long category_id, Long location_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByLocationIdAndCategoryId'");
    }

    @Override
    public void addNewProduct(Product p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNewProduct'");
    }
    
}
