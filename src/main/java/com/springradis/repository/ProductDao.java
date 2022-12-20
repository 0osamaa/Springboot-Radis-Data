package com.springradis.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springradis.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ProductDao {
    private final RedisTemplate<Integer, String> template;

    private final ObjectMapper mapper;

    public Product save(Product product) throws JsonProcessingException {

        template.opsForValue().set(product.getId(), mapper.writeValueAsString(product));
        return product;
    }

    public Product findProductById(Integer id) throws JsonProcessingException {
        System.out.println("Inside findProductById() in DB");
        Product product = mapper.readValue(template.opsForValue().get(id), Product.class);
        return product;
    }


    public String updateProduct(Integer id, Product product) throws JsonProcessingException {

        template.opsForValue().set(product.getId(), mapper.writeValueAsString(product));

        return "Product updated successfully";
    }


    public String deleteProduct(int id) {
        template.delete(id);
        return "product removed !!";
    }
}