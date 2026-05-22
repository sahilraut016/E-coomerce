package ecommerce.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.Model.Cart;
import ecommerce.Model.CartItem;
import ecommerce.Repo.CartItemRepository;
import ecommerce.Repo.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    public Cart getCartByUserId(int userId) {
        Cart cart = cartRepo.findByUserId(userId);

        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            return cartRepo.save(newCart);
        }

        return cart;
    }

    public CartItem addToCart(int userId, int productId) {
        Cart cart = getCartByUserId(userId);

        List<CartItem> items = cartItemRepo.findByCartId(cart.getId());

        for (CartItem item : items) {
            if (item.getProductId() == productId) {
                item.setQuantity(item.getQuantity() + 1);
                return cartItemRepo.save(item);
            }
        }

        CartItem newItem = new CartItem();
        newItem.setCartId(cart.getId());
        newItem.setProductId(productId);
        newItem.setQuantity(1);

        return cartItemRepo.save(newItem);
    }

    public List<CartItem> getCartItems(int userId) {
        Cart cart = getCartByUserId(userId);
        return cartItemRepo.findByCartId(cart.getId());
    }

    public void removeCartItem(int itemId) {
        cartItemRepo.deleteById(itemId);
    }

    public void clearCart(int userId) {
        Cart cart = getCartByUserId(userId);
        List<CartItem> items = cartItemRepo.findByCartId(cart.getId());
        cartItemRepo.deleteAll(items);
    }
}