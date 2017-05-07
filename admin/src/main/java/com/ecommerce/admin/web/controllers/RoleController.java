package com.ecommerce.admin.web.controllers;

import com.ecommerce.admin.security.SecurityUtil;
import com.ecommerce.admin.web.validators.RoleValidator;
import com.ecommerce.core.entity.Permission;
import com.ecommerce.core.entity.Role;
import com.ecommerce.core.security.SecurityService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dguzik
 */
@Controller
@Secured(SecurityUtil.MANAGE_ROLES)
public class RoleController extends AdminBaseController {

    private static final String viewPrefix = "roles/";

    @Autowired protected SecurityService securityService;
    @Autowired private RoleValidator roleValidator;

    @Override
    protected String getHeaderTitle() {
        return "Manage Roles";
    }

    @ModelAttribute("permissionsList")
    public List<Permission> permissionsList() {
        return securityService.getAllPermissions();
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public String listRoles(Model model) {
        List<Role> list = securityService.getAllRoles();
        model.addAttribute("roles", list);
        return viewPrefix + "roles";
    }

    @RequestMapping(value = "/roles/new", method = RequestMethod.GET)
    public String createRoleForm(Model model) {
        Role role = new Role();
        model.addAttribute("role", role);

        return viewPrefix + "create_role";
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public String createRole(@Valid @ModelAttribute("role") Role role, BindingResult result,
                             Model model, RedirectAttributes redirectAttributes) {
        roleValidator.validate(role, result);
        if (result.hasErrors()) {
            return viewPrefix + "create_role";
        }
        securityService.createRole(role);
        redirectAttributes.addFlashAttribute("info", "Role created successfully");
        return "redirect:/roles";
    }


    @RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
    public String editRoleForm(@PathVariable Integer id, Model model) {
        Role role = securityService.getRoleById(id);
        Map<Integer, Permission> assignedPermissionMap = new HashMap<>();
        List<Permission> permissions = role.getPermissions();
        for (Permission permission : permissions) {
            assignedPermissionMap.put(permission.getId(), permission);
        }
        List<Permission> rolePermissions = new ArrayList<>();
        List<Permission> allPermissions = securityService.getAllPermissions();
        for (Permission permission : allPermissions) {
            if (assignedPermissionMap.containsKey(permission.getId())) {
                rolePermissions.add(permission);
            } else {
                rolePermissions.add(null);
            }
        }
        role.setPermissions(rolePermissions);
        model.addAttribute("role", role);
        return viewPrefix + "edit_role";
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.POST)
    public String updateRole(@ModelAttribute("role") Role role, BindingResult result,
                             Model model, RedirectAttributes redirectAttributes) {
        securityService.updateRole(role);
        redirectAttributes.addFlashAttribute("info", "Role updated successfully");
        return "redirect:/roles";
    }
}
