package java.com.product.repository;

import java.org.springframework.data.mongodb.repository.MongoRepository;

import java.com.product.entity.Product;

public interface IProductRepo extends MongoRepository<Product, String> {

}
