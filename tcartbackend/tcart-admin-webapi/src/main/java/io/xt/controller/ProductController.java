package io.xt.controller;

import com.github.pagehelper.PageInfo;
import io.xt.dto.ProductAddDTO;
import io.xt.dto.ProductListDTO;
import io.xt.pojo.Product;
import io.xt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getProductWithPage")
    public PageInfo<Product> getProductWithPage(@RequestParam(required = false,defaultValue = "1") Integer pageNum){
        PageInfo<Product> productWithPage = productService.fetchList(pageNum);
        return productWithPage;
    }
    @PostMapping("/addProduct")
    public void addProduct(@RequestBody ProductAddDTO productAddDTO){
        System.out.println("hhahahha");
        productService.addProduct(productAddDTO);
    }
}
