package uz.pdp.WebAuto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.dtos.role.RoleDTO;
import uz.pdp.WebAuto.dtos.role.RoleRequestDTO;
import uz.pdp.WebAuto.entity.Role;
import uz.pdp.WebAuto.entity.User;
import uz.pdp.WebAuto.enums.UserRole;
import uz.pdp.WebAuto.repository.RoleRepository;
import uz.pdp.WebAuto.service.RoleService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DynamicRoleServiceImp implements RoleService {

    private final Set<String> dynamicRoles = new HashSet<>();
    private final RoleRepository roleRepository;
    private final UserServiceImp userServiceImp;

    @Override
    public RoleDTO save(RoleRequestDTO dto) {
        // Dinamik ro'lni qo'shish
        dynamicRoles.add(dto.name().toUpperCase());

        // Dinamik rolni ma'lumotlar bazasiga qo'shish
        Role role = new Role();
        role.setName(UserRole.valueOf(dto.name().toUpperCase()));
        Role savedRole = roleRepository.save(role);

        return RoleDTO.builder().roleName(savedRole.getName().name()).build();
    }

    @Override
    public void addRole(String role) {
        dynamicRoles.add(role.toUpperCase());

        // Dinamik rolni ma'lumotlar bazasiga qo'shish
        Role newRole = new Role();
        newRole.setName(UserRole.valueOf(role.toUpperCase())); // Enum qiymatiga aylantirib qo'shamiz
        roleRepository.save(newRole);
    }

    @Override
    public boolean roleExists(String role) {
        // Dinamik ro'lni tekshirish, Enum ro'lini va ma'lumotlar bazasidagi ro'lni tekshirish
        return dynamicRoles.contains(role.toUpperCase()) ||
                Arrays.stream(UserRole.values()).anyMatch(r -> r.name().equalsIgnoreCase(role)) ||
                roleRepository.existsByName(UserRole.valueOf(role.toUpperCase()));
    }

    @Override
    public Set<String> getAllRoles() {
        // Dinamik va ma'lumotlar bazasidagi barcha rollarni olish
        Set<String> allRoles = new HashSet<>(dynamicRoles);
        allRoles.addAll(Arrays.stream(UserRole.values()).map(Enum::name).toList());

        // Rollarni ma'lumotlar bazasidan olish
        Set<Role> rolesFromDb = new HashSet<>(roleRepository.findAll());
        rolesFromDb.forEach(role -> allRoles.add(role.getName().name()));

        return allRoles;
    }

    @Override
    public Set<Role> getRolesByUsername(String username) {
        User user = userServiceImp.findByUsername(username);
        // Foydalanuvchining rollarini olish
        return roleRepository.findByUserId(user.getId());
    }

    @Override
    public Optional<Role> findByName(UserRole roleName) {
        // `UserRole` orqali ro'lni olish
        return roleRepository.findByName(roleName);
    }

    @Override
    public Set<Role> getRolesByUserId(Long userId) {
        // Ma'lumotlar bazasidan foydalanuvchiga tegishli rollarni olish
        return roleRepository.findByUserId(userId);
    }
}
