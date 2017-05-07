package com.ecommerce.shop.controllers;

import com.ecommerce.shop.models.Cart;
import com.ecommerce.shop.models.OrderDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by english on 5/7/17.
 */
@Controller
public class CheckoutController extends ShopBaseController {

    @Override
    protected String getHeaderTitle()
    {
        return "Checkout";
    }

    @RequestMapping(value="/checkout", method= RequestMethod.GET)
    public String checkout(HttpServletRequest request, Model model)
    {
        OrderDTO order = new OrderDTO();
        model.addAttribute("order", order);
        Cart cart = getOrCreateCart(request);
        model.addAttribute("cart", cart);
        return "checkout";
    }
}
