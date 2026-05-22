package ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ecommerce.Model.CartItem;
import ecommerce.Service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins="http://localhost:3000")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public CartItem addToCart(@RequestParam int userId, @RequestParam int productId) {
        return cartService.addToCart(userId, productId);
    }

    @GetMapping("/items/{userId}")
    public List<CartItem> getCartItems(@PathVariable int userId) {
        return cartService.getCartItems(userId);
    }

    @DeleteMapping("/remove/{itemId}")
    public String removeItem(@PathVariable int itemId) {
        cartService.removeCartItem(itemId);
        return "Item Removed Successfully";
    }
}
