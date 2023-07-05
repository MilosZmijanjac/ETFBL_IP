package ip.webshop.service.implementation;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ip.webshop.model.entity.Product;
import ip.webshop.model.enumeration.ProductState;
import ip.webshop.model.enumeration.ProductStatus;
import ip.webshop.model.request.ProductRequest;
import ip.webshop.repository.ProductRepository;
import ip.webshop.repository.UserRepository;
import ip.webshop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getProductsByUserId(Long id) {
        return productRepository.findByUserId(id);
    }

    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
    }

    @Override
    public Product addNewProduct(ProductRequest request) {
        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setAddress(request.getAddress());
        product.setCategoryId(Long.parseLong(request.getCategoryId()));
        product.setCity(request.getCity());
        product.setCountry(request.getCountry());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCreationTimestamp(Instant.now());
        product.setExtendedAttributes(request.getExtendedAttributes());
        product.setState(ProductState.valueOf(request.getState()));
        product.setStatus(ProductStatus.ACTIVE);
        product.setUserId(userRepository.findByUsername(request.getUsername()).getId());
        product = productRepository.save(product);
        product.setImagesPath("http://127.0.0.1:8008/Users/" + request.getUsername() + "/products/" + product.getId()
                + "/product.png");
        return productRepository.save(product);
    }

    @Override
    public Product endProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null)
            return null;
        product.setStatus(ProductStatus.ENDED);
        productRepository.save(product);
        return product;
    }

      @Override
    public Product activateProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null)
            return null;
        product.setStatus(ProductStatus.ACTIVE);
        productRepository.save(product);
        return product;
    }
    @Override
    public void deleteProduct(Long id){
         productRepository.deleteById(id);
    }

}
