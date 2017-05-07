package com.ecommerce.admin.web.controllers;

import com.ecommerce.admin.security.SecurityUtil;
import com.ecommerce.admin.web.validators.CategoryValidator;
import com.ecommerce.core.catalog.CatalogService;
import com.ecommerce.core.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by dguzik
 */
@Controller
@Secured(SecurityUtil.MANAGE_CATEGORIES)
public class CategoryController extends AdminBaseController {

    private static final String viewPrefix = "categories/";

    @Autowired private CatalogService catalogService;

    @Autowired private CategoryValidator categoryValidator;

    @Override
    protected String getHeaderTitle()
    {
        return "Manage Categories";
    }

    @RequestMapping(value="/categories", method= RequestMethod.GET)
    public String listCategories(Model model) {
        List<Category> list = catalogService.getAllCategories();
        model.addAttribute("categories",list);
        return viewPrefix+"categories";
    }

    @RequestMapping(value="/categories/new", method=RequestMethod.GET)
    public String createCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category",category);

        return viewPrefix+"create_category";
    }

    @RequestMapping(value="/categories", method=RequestMethod.POST)
    public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result,
                                 Model model, RedirectAttributes redirectAttributes) {
        categoryValidator.validate(category, result);
        if(result.hasErrors()){
            return viewPrefix+"create_category";
        }
        catalogService.createCategory(category);
        redirectAttributes.addFlashAttribute("info", "Category created successfully");
        return "redirect:/categories";
    }

    @RequestMapping(value="/categories/{id}", method=RequestMethod.GET)
    public String editCategoryForm(@PathVariable Integer id, Model model) {
        Category category = catalogService.getCategoryById(id);
        model.addAttribute("category",category);
        return viewPrefix+"edit_category";
    }

    @RequestMapping(value="/categories/{id}", method=RequestMethod.POST)
    public String updateCategory(Category category, Model model, RedirectAttributes redirectAttributes) {
        catalogService.updateCategory(category);
        redirectAttributes.addFlashAttribute("info", "Category updated successfully");
        return "redirect:/categories";
    }
}
