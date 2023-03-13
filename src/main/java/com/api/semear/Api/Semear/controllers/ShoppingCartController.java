package com.api.semear.Api.Semear.controllers;


import com.api.semear.Api.Semear.domain.cart.ShoppingCart;
import com.api.semear.Api.Semear.domain.course.model.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCart cart;

    public ShoppingCartController(ShoppingCart cart) {
        this.cart = cart;
    }

    @GetMapping
    public ShoppingCart showcart (){
        return cart;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCourse (@RequestBody Course course){
        cart.addCourse(course);
        return ResponseEntity.ok("Curso Adicionado ao carrinho");
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeCourse (@RequestBody Course course){
        cart.removeCourse(course);
        return ResponseEntity.ok("Curso removido do carrinho");
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearCart(){
        cart.clear();
        return ResponseEntity.ok("Carrinho esvaziado");
    }
}
