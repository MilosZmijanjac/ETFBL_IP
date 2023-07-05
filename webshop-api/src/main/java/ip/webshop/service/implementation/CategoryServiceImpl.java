package ip.webshop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ip.webshop.model.entity.Category;
import ip.webshop.repository.CategoryRepository;
import ip.webshop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
       return categoryRepository.findAll();
    }

}
