package com.alexsys.smartmarket.service;

import com.alexsys.smartmarket.mapper.WishlistMapper;
import com.alexsys.smartmarket.model.Wishlist;
import com.alexsys.smartmarket.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final WishlistMapper wishlistMapper;

    public WishlistService(WishlistRepository wishlistRepository, WishlistMapper wishlistMapper) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistMapper = wishlistMapper;
    }

    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    public Optional<Wishlist> getWishlistById(Integer id) {
        return wishlistRepository.findById(id);
    }

    // TODO:
    // public List<Wishlist> getWishlistsByUserId(Integer userId) {
    //     return wishlistRepository.findByUserId(userId);
    // }

    // public List<Wishlist> getWishlistByProductId(Integer userId) {
    //     return wishlistRepository.findByProductId(userId);
    // }

    public Wishlist createWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    public Optional<Wishlist> updateWishlist(Integer id, Wishlist wishlistDetails) {
        var existingWishlistOptional = getWishlistById(id);
        if (existingWishlistOptional.isEmpty()) {
            return Optional.empty();
        }
        var existingWishlist = existingWishlistOptional.get();
        wishlistMapper.update(existingWishlist, wishlistDetails);
        return Optional.ofNullable(wishlistRepository.save(existingWishlist));
    }

    public void deleteWishlist(Integer id) {
        wishlistRepository.deleteById(id);
    }
}
