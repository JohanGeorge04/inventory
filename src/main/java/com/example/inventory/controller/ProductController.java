package com.example.inventory.controller;

import com.example.inventory.model.Product;
import com.example.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/";
    }

    @GetMapping("/searchProduct")
    public String searchProduct(@RequestParam("searchProductName") String searchProductName, Model model) {
        List<Product> products = productService.getProductByName(searchProductName);
        List<Product> product = productService.getProductByName(searchProductName);
        model.addAttribute("products", products);
        return "index";
        
    }

    @PostMapping("/removeProduct")
    public String removeProduct(@RequestParam("removeProductName") String removeProductName) {
        List<Product> products = productService.getProductByName(removeProductName);
        for (Product product : products) {
            productService.deleteProductById(product.getId());
        }
        return "redirect:/";
    }
}
