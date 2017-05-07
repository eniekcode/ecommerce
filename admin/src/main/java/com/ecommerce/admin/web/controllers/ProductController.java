package com.ecommerce.admin.web.controllers;

import com.ecommerce.admin.security.SecurityUtil;
import com.ecommerce.admin.web.models.ProductForm;
import com.ecommerce.admin.web.validators.ProductFormValidator;
import com.ecommerce.core.CoreException;
import com.ecommerce.core.catalog.CatalogService;
import com.ecommerce.core.entity.Category;
import com.ecommerce.core.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by dguzik
 */
@Controller
@Secured(SecurityUtil.MANAGE_PRODUCTS)
public class ProductController extends AdminBaseController {

    private static final String viewPrefix = "products/";

    @Autowired
    private CatalogService catalogService;
    @Autowired
    private ProductFormValidator productFormValidator;

    @Override
    protected String getHeaderTitle() {
        return "Manage Products";
    }

    @ModelAttribute("categoriesList")
    public List<Category> categoriesList() {
        return catalogService.getAllCategories();
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String listProducts(Model model) {
        model.addAttribute("products", catalogService.getAllProducts());
        return viewPrefix + "products";
    }

    @RequestMapping(value = "/products/new", method = RequestMethod.GET)
    public String createProductForm(Model model) {
        ProductForm product = new ProductForm();
        model.addAttribute("product", product);
        return viewPrefix + "create_product";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String createProduct(@Valid @ModelAttribute("product") ProductForm productForm, BindingResult result,
                                Model model, RedirectAttributes redirectAttributes) {
        productFormValidator.validate(productForm, result);
        if (result.hasErrors()) {
            return viewPrefix + "create_product";
        }
        Product product = productForm.toProduct();
        productForm.setId(product.getId());
        redirectAttributes.addFlashAttribute("info", "Product created successfully");
        return "redirect:/products";
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public String editProductForm(@PathVariable Integer id, Model model) {
        Product product = catalogService.getProductById(id);
        model.addAttribute("product", ProductForm.fromProduct(product));
        return viewPrefix + "edit_product";
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.POST)
    public String updateProduct(@Valid @ModelAttribute("product") ProductForm productForm, BindingResult result,
                                Model model, RedirectAttributes redirectAttributes) {
        productFormValidator.validate(productForm, result);
        if (result.hasErrors()) {
            return viewPrefix + "edit_product";
        }
        Product product = productForm.toProduct();
        catalogService.updateProduct(product);
        redirectAttributes.addFlashAttribute("info", "Product updated successfully");
        return "redirect:/products";
    }
}
