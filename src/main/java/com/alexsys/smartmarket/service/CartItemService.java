package com.alexsys.smartmarket.service;

import com.alexsys.smartmarket.mapper.CartItemMapper;
import com.alexsys.smartmarket.model.CartItem;
import com.alexsys.smartmarket.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    public CartItemService(CartItemRepository cartItemRepository, CartItemMapper cartItemMapper) {
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public Optional<CartItem> getCartItemById(Integer id) {
        return cartItemRepository.findById(id);
    }

    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    
    public List<CartItem> getCartItemsByCartId(Integer cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    public Optional<CartItem> updateCartItem(Integer id, CartItem cartItemDetails) {
        var existingCartItemOptional = getCartItemById(id);
        if (existingCartItemOptional.isEmpty()) {
            return Optional.empty();
        }
        var existingCartItem = existingCartItemOptional.get();
        cartItemMapper.update(existingCartItem, cartItemDetails);
        return Optional.of(cartItemRepository.save(existingCartItem));
    }

    public void deleteCartItem(Integer id) {
        cartItemRepository.deleteById(id);
    }
}
