package com.ecommerce.shop.controllers;

import com.ecommerce.core.catalog.CatalogService;
import com.ecommerce.core.entity.Product;
import com.ecommerce.shop.models.Cart;
import com.ecommerce.shop.models.LineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by english on 5/7/17.
 */
@Controller
public class ErrorController extends ShopBaseController{

    private static final String viewPrefix = "error/";

    @Override
    protected String getHeaderTitle()
    {
        return "Error";
    }

    @RequestMapping("/403")
    public String accessDenied()
    {
        return viewPrefix+"accessDenied";
    }
}
