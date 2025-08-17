package com.alexsys.smartmarket.controller;

import com.alexsys.smartmarket.model.Wishlist;
import com.alexsys.smartmarket.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/smartmarket/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public List<Wishlist> getAllWishlistItems() {
        return wishlistService.getAllWishlists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getWishlistItemById(@PathVariable Integer id) {
        return wishlistService.getWishlistById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // TODO:
    // @GetMapping("/user/{userId}")
    // public List<Wishlist> getWishlistByUser(@PathVariable Integer userId) {
    //     return wishlistService.getWishlistsByUserId(userId);
    // }

    // @GetMapping("/product/{productId}")
    // public List<Wishlist> getWishlistByProduct(@PathVariable Integer productId) {
    //     return wishlistService.getWishlistByProductId(productId);
    // }

    @PostMapping
    public Wishlist addWishlistItem(@RequestBody Wishlist wishlist) {
        return wishlistService.createWishlist(wishlist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> updateWishlist(@PathVariable("id") Integer id, @RequestBody Wishlist wishlistDetails) {
        var updatedWishlist = wishlistService.updateWishlist(id, wishlistDetails);
        return updatedWishlist.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlistItem(@PathVariable Integer id) {
        wishlistService.deleteWishlist(id);
        return ResponseEntity.noContent().build();
    }
}
