package com.rahul.shopease.Service;

import com.rahul.shopease.DTO.Request.CartItemRequest;
import com.rahul.shopease.DTO.Response.CartItemResponse;
import com.rahul.shopease.Entity.Cart;
import com.rahul.shopease.Entity.CartItems;
import com.rahul.shopease.Entity.Product;
import com.rahul.shopease.Exception.CartItemNotFoundException;
import com.rahul.shopease.Exception.CartNotFoundException;
import com.rahul.shopease.Exception.ProductNotFoundException;
import com.rahul.shopease.Exception.ProductOutOfStockException;
import com.rahul.shopease.Repository.CartItemRepository;
import com.rahul.shopease.Repository.CartRepository;
import com.rahul.shopease.Repository.ProductRepository;
import com.rahul.shopease.Transformer.CartItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository; // save/update cartItem
    @Autowired
    CartRepository cartRepository; // to find cart
    @Autowired
    ProductRepository productRepository;//to find product

    // Add CartItem
    public CartItemResponse addCartItem(CartItemRequest cartItemRequest) {
        Cart cart = cartRepository.findById(cartItemRequest.getCartId())  //TO FIND CART
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));

        Product product = productRepository.findById(cartItemRequest.getProductId())  //TO FIND PRODUCT
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        if(product.getStock() <cartItemRequest.getQuantity()) {  // CHECK STOCK AVAILABLE OR NOT
            throw new ProductOutOfStockException("Product is out of stock");
        }
        CartItems cartItems = CartItemTransformer.requestToCartItems(cartItemRequest); //NEW OBJECT
        cartItems.setCart(cart); //SET CART
        cartItems.setProduct(product); // SET PRODUCT
        cartItems.setQuantity(cartItemRequest.getQuantity()); //SET QUANTITY

        double totalPrice = product.getPrice()* cartItemRequest.getQuantity();
        cartItems.setTotalPrice(totalPrice);

        CartItems savedCartItems = cartItemRepository.save(cartItems); //NEW SAVED ITEM OBJECT

        cart.setTotalAmmount(cart.getTotalAmmount()+totalPrice);
        cartRepository.save(cart); // SAVE CART

        return CartItemTransformer.cartItemToResponse(savedCartItems);
    }

    //Get CartItem ByID
    public CartItemResponse getCartItemById(int cartItemId){
        CartItems cartItems =cartItemRepository.findById(cartItemId)
                .orElseThrow( ()-> new CartItemNotFoundException("CartItem Not Found"));
        return CartItemTransformer.cartItemToResponse(cartItems);
    }

    //Get All CartItem
    public List<CartItemResponse> getAllCartItems(){
        List<CartItems> cartItems =cartItemRepository.findAll();
        return cartItems.stream().map(CartItemTransformer::cartItemToResponse).toList();
    }

    //Delete cartItems
    public String deleteCartItem(int cartItemId){
        CartItems cartItems = cartItemRepository.findById(cartItemId)
                .orElseThrow( ()-> new CartItemNotFoundException("CartItem Not Found"));
        cartItemRepository.deleteById(cartItemId);
        return "CartItem Deleted Sucessfully";
    }

    //Update Qunatity
     public CartItemResponse updateCartItemQuantity(int cartItemId, int quantity){
        CartItems cartItems=cartItemRepository.findById(cartItemId)
                .orElseThrow(()->new CartItemNotFoundException("CartItem Not Found"));

        Product product=cartItems.getProduct();

        int oldQuantity= cartItems.getQuantity(); //Old quantity from database
        int newQuantity= quantity;    //New Quantity from method parameter
        int difference= newQuantity-oldQuantity;

        if(difference > 0 && product.getStock()<difference){
            throw new ProductOutOfStockException("Product is out of stock");
        }
        int stock =product.getStock()-difference;
        product.setStock(stock);

        cartItems.setQuantity(newQuantity);

        double oldTotalPrice = product.getPrice() *oldQuantity;
        double newTotalPrice = product.getPrice() *newQuantity;
        Cart cart=cartItems.getCart();
        double newCartTotal=cart.getTotalAmmount() - oldTotalPrice + newTotalPrice;
        cart .setTotalAmmount(newCartTotal);

        cartItems.setQuantity(newQuantity);
        cartItems.setTotalPrice(newTotalPrice);

        CartItems savedCartItems = cartItemRepository.save(cartItems);
        productRepository.save(product);
        cartRepository.save(cart);



        return CartItemTransformer.cartItemToResponse(savedCartItems);

     }
}
