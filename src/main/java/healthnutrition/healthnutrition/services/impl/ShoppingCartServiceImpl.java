package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.ProductInCartDTO;
import healthnutrition.healthnutrition.repositories.ProductRepository;
import healthnutrition.healthnutrition.repositories.ShoppingCartRepositories;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {


    private final ProductRepository productRepository;
    private final ShoppingCartRepositories shoppingCartRepositories;

    private final Map<String,ProductInCartDTO> product;


    public ShoppingCartServiceImpl(ProductRepository productRepository, ShoppingCartRepositories shoppingCartRepositories) {
        this.productRepository = productRepository;
        this.shoppingCartRepositories = shoppingCartRepositories;
        this.product =new HashMap<>();
    }



}
