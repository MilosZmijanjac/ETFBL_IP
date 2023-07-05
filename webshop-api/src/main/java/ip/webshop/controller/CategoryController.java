package ip.webshop.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ip.webshop.model.enumeration.LogType;
import ip.webshop.service.CategoryService;
import ip.webshop.service.LogService;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LogService logService;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        logService.writeLog(LogType.INFO, "/api/category", "Requesting all categories", Instant.now());
        return ResponseEntity.ok(categoryService.getCategories());
    }
}
