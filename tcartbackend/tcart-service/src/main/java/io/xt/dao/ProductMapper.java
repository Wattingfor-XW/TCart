package io.xt.dao;

import io.xt.pojo.Product;

public interface ProductMapper {
    int insert(Product record);

    int insertSelective(Product record);
}