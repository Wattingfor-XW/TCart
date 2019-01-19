package io.xt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.xt.dao.ProductMapper;
import io.xt.dto.ProductListDTO;
import io.xt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Override
    public PageInfo<ProductListDTO> getProductWithPage(Integer pageNum) {
        PageHelper.startPage(pageNum,3);
        Page<ProductListDTO> page = productMapper.selectWithPage();
        PageInfo<ProductListDTO> productListDTOPageInfo = page.toPageInfo();
        return productListDTOPageInfo;
    }
}
