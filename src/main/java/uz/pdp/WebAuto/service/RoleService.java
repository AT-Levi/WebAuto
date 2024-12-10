package uz.pdp.WebAuto.service;

import uz.pdp.WebAuto.dtos.role.RoleDTO;
import uz.pdp.WebAuto.dtos.role.RoleRequestDTO;
import uz.pdp.WebAuto.entity.Role;
import uz.pdp.WebAuto.enums.UserRole;

import java.util.Optional;
import java.util.Set;

public interface RoleService {
    RoleDTO save(RoleRequestDTO dto); // Yangi rol yaratish

    void addRole(String role); // Yangi dinamik rol qo'shish

    boolean roleExists(String role); // Rol mavjudligini tekshirish

    Set<String> getAllRoles(); // Barcha rollarni olish

    Set<Role> getRolesByUsername(String username); // Foydalanuvchining rollarini olish

    Optional<Role> findByName(UserRole roleName); // Foydalanuvchining rolini nomi bo'yicha olish

    Set<Role> getRolesByUserId(Long userId); // Foydalanuvchining id bo'yicha rollarni olish
}
