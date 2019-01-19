package io.xt.service;

import com.github.pagehelper.PageInfo;
import io.xt.dto.ProductListDTO;

public interface ProductService {

    public PageInfo<ProductListDTO> getProductWithPage(Integer pageNum);
}
