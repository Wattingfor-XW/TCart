package io.xt.controller;

import com.github.pagehelper.PageInfo;
import io.xt.dto.ProductListDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    @GetMapping("/getProductWithPage")
    public PageInfo<ProductListDTO> getProductWithPage(Integer pageNuM){
        return null;
    }
}
