package io.xt.service;

import com.github.pagehelper.PageInfo;
import io.xt.dto.ProductAddDTO;
import io.xt.pojo.Product;

public interface ProductService {


    PageInfo<Product> fetchList(Integer pageNum);

    void addProduct(ProductAddDTO productAddDTO);
}
