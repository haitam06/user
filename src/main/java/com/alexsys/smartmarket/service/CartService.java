package com.alexsys.smartmarket.service;

import com.alexsys.smartmarket.mapper.CartMapper;
import com.alexsys.smartmarket.model.Cart;
import com.alexsys.smartmarket.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public CartService(CartRepository cartRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<Cart> getCartById(Integer id) {
        return cartRepository.findById(id);
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart> updateCart(Integer id, Cart cartDetails) {
        var existingCartOptional = getCartById(id);
        if (existingCartOptional.isEmpty()) {
            return Optional.empty();
        }
        var existingCart = existingCartOptional.get();
        cartMapper.update(existingCart, cartDetails);
        return Optional.of(cartRepository.save(existingCart));
    }

    public void deleteCart(Integer id) {
        cartRepository.deleteById(id);
    }
}
