package io.xt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.xt.dao.ProductMapper;
import io.xt.dto.ProductAddDTO;
import io.xt.pojo.Product;
import io.xt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;


    @Override
    public PageInfo<Product> fetchList(Integer pageNum) {
        PageHelper.startPage(pageNum,3);
         Page  page =(Page) productMapper.selectByExample(null);
        PageInfo<Product> product = page.toPageInfo();
        return product;
    }

    @Override
    public void addProduct(ProductAddDTO productAddDTO) {
        Product product = new Product();
        product.setProductCode(productAddDTO.getProductCode());
        product.setName(productAddDTO.getName());
        product.setPrice(productAddDTO.getPrice());
        product.setBrand(productAddDTO.getBrand());
        productMapper.insert(product);
    }


}
