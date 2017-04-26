package com.ecommerce.core.security;

import com.ecommerce.core.CoreException;
import com.ecommerce.core.entity.Permission;
import com.ecommerce.core.entity.Role;
import com.ecommerce.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dguzik
 */
@Service
@Transactional
public class SecurityService {

    @Autowired UserRepository userRepository;
    @Autowired PermissionRepository permissionRepository;
    @Autowired RoleRepository roleRepository;

    public User findUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleByName(String roleName)
    {
        return roleRepository.findByName(roleName);
    }

    public Role createRole(Role role)
    {
        Role roleByName = getRoleByName(role.getName());
        if(roleByName != null){
            throw new CoreException("Role "+role.getName()+" already exist");
        }
        List<Permission> persistedPermissions = new ArrayList<>();
        List<Permission> permissions = role.getPermissions();
        if(permissions != null){
            for (Permission permission : permissions) {
                if(permission.getId() != null)
                {
                    persistedPermissions.add(permissionRepository.findOne(permission.getId()));
                }
            }
        }

        role.setPermissions(persistedPermissions);
        return roleRepository.save(role);
    }

    public Role updateRole(Role role)
    {
        Role persistedRole = getRoleById(role.getId());
        if(persistedRole == null){
            throw new CoreException("Role "+role.getId()+" doesn't exist");
        }
        persistedRole.setDescription(role.getDescription());
        List<Permission> updatedPermissions = new ArrayList<>();
        List<Permission> permissions = role.getPermissions();
        if(permissions != null){
            for (Permission permission : permissions) {
                if(permission.getId() != null)
                {
                    updatedPermissions.add(permissionRepository.findOne(permission.getId()));
                }
            }
        }
        persistedRole.setPermissions(updatedPermissions);
        return roleRepository.save(persistedRole);
    }

    public Role getRoleById(Integer id) {
        return roleRepository.findOne(id);
    }

    public User getUserById(Integer id)
    {
        return userRepository.findOne(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user)
    {
        User userByEmail = findUserByEmail(user.getEmail());
        if(userByEmail != null){
            throw new CoreException("Email "+user.getEmail()+" already in use");
        }
        List<Role> persistedRoles = new ArrayList<>();
        List<Role> roles = user.getRoles();
        if(roles != null){
            for (Role role : roles) {
                if(role.getId() != null)
                {
                    persistedRoles.add(roleRepository.findOne(role.getId()));
                }
            }
        }
        user.setRoles(persistedRoles);

        return userRepository.save(user);
    }

    public User updateUser(User user)
    {
        User persistedUser = getUserById(user.getId());
        if(persistedUser == null){
            throw new CoreException("User "+user.getId()+" doesn't exist");
        }

        List<Role> updatedRoles = new ArrayList<>();
        List<Role> roles = user.getRoles();
        if(roles != null){
            for (Role role : roles) {
                if(role.getId() != null)
                {
                    updatedRoles.add(roleRepository.findOne(role.getId()));
                }
            }
        }
        persistedUser.setRoles(updatedRoles);
        return userRepository.save(persistedUser);
    }
}
