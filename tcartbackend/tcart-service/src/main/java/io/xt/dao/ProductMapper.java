package io.xt.dao;

import com.github.pagehelper.Page;
import io.xt.dto.ProductListDTO;
import io.xt.pojo.Product;

public interface ProductMapper {
    int insert(Product record);

    int insertSelective(Product record);

    Page<ProductListDTO> selectWithPage();
}