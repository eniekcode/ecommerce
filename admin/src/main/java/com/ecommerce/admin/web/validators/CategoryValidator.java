package com.ecommerce.admin.web.validators;

import com.ecommerce.core.catalog.CatalogService;
import com.ecommerce.core.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by dguzik
 */
@Component
public class CategoryValidator implements Validator {

    @Autowired protected CatalogService catalogService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Category.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Category category = (Category) target;
        String name = category.getName();
        Category categoryByName = catalogService.getCategoryByName(name);
        if (categoryByName != null) {
            errors.rejectValue("name", "error.exists", new Object[]{name}, "Category " + category.getName() + " already exists");
        }
    }
}
