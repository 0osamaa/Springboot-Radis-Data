
package com.springradis.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.springradis.entity.Product;
import com.springradis.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@EnableCaching
public class ProductController {
    @Autowired
    private ProductDao dao;

    @PostMapping
    public Product save(@RequestBody Product product) throws JsonProcessingException {
        return dao.save(product);
    }


    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "Product")
    public Product findProduct(@PathVariable int id) throws JsonProcessingException {
        return dao.findProductById(id);
    }

    @PutMapping("/{id}")
    @CachePut(key = "#id", value = "Product")
    public String updateProduct(@RequestBody Product product, @PathVariable Integer id) throws JsonProcessingException {
        dao.updateProduct(id, product);
        return "product has been updated";
    }


    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "Product")
    public String remove(@PathVariable int id)   {
        return dao.deleteProduct(id);
    }



}

