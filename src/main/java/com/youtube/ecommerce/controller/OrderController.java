package com.youtube.ecommerce.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youtube.ecommerce.JWTConfiguration.ShoppingConfiguration;
import com.youtube.ecommerce.Repository.OrderRepo;
import com.youtube.ecommerce.controller.RequestPojo.ApiResponse;
import com.youtube.ecommerce.converter.OrderConverter;
import com.youtube.ecommerce.dto.ListPenjualan;
import com.youtube.ecommerce.model.AddtoCart;
import com.youtube.ecommerce.model.Category;
import com.youtube.ecommerce.model.CheckoutCart;
import com.youtube.ecommerce.model.OrderExcel;
import com.youtube.ecommerce.model.Products;
import com.youtube.ecommerce.service.CartService.CartService;
import com.youtube.ecommerce.service.ProductService.ProductServices;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    CartService cartService;
    ProductServices proService;
    
    @Autowired
    OrderConverter orderConverter;
    
    @Autowired
    OrderRepo orderRepo;

    @RequestMapping("checkout_order")
    public ResponseEntity<?> checkout_order(@RequestBody HashMap<String, String> addCartRequest) {
        try {
            String keys[] = {"userId", "total_price", "pay_type", "deliveryAddress"};
            if (ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {

            }
            long user_Id = Long.parseLong(addCartRequest.get("userId"));
            double total_amt = Double.parseDouble(addCartRequest.get("total_price"));
            if (cartService.checkTotalAmountAgainstCart(total_amt, user_Id)) {
                List<AddtoCart> cartItems = cartService.getCartByUserId(user_Id);
                List<CheckoutCart> tmp = new ArrayList<CheckoutCart>();
                for (AddtoCart addCart : cartItems) {
                    String orderId = "" + getOrderId();
                    CheckoutCart cart = new CheckoutCart();
                    cart.setPayment_type(addCartRequest.get("pay_type"));
                    cart.setPrice(total_amt);
                    cart.setUser_id(user_Id);
                    cart.setOrderId(orderId);
                    cart.setProduct(addCart.getProduct());
                    cart.setQty(addCart.getQty());
                    cart.setDelivery_address(addCartRequest.get("deliveryAddress"));
                    tmp.add(cart);
                }
                cartService.saveProductsForCheckout(tmp);
                return ResponseEntity.ok(new ApiResponse("Order placed successfully", ""));
            } else {
                throw new Exception("Total amount is mismatch");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }

    public int getOrderId() {
        Random r = new Random(System.currentTimeMillis());
        return 10000 + r.nextInt(20000);
    }

    @RequestMapping("getOrdersByUserId")
    public ResponseEntity<?> getOrdersByUserId(@RequestBody HashMap<String, String> ordersRequest) {
        try {
            String keys[] = {"userId"};
            return ResponseEntity.ok(new ApiResponse("Order placed successfully", ""));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }

    }
    
    @GetMapping("export/excel")
    public void exportToExcel(HttpServletResponse response)throws IOException{
        System.out.println("Export to Excel ......");
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Laporanpenjualan_"+currentDateTime+".xlsx";
        response.setHeader(headerKey, headerKey);
        List<CheckoutCart> listOrders = orderRepo.findAll();
        OrderExcel excel = new OrderExcel(listOrders);
        excel.export(response);
    }
    
    @RequestMapping("/getAll")
    public List<CheckoutCart> getAllOrder() {
        System.out.println("Get all Products...");
        return orderRepo.findAll();
    }
    
    @RequestMapping("getAllDto")
    public List<ListPenjualan> findAll(){
        List<CheckoutCart> dto = orderRepo.findAll();
        return orderConverter.entityToDto(dto);
    }
}
