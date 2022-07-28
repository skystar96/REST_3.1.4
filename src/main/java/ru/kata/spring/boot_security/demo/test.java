package ru.kata.spring.boot_security.demo;


import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.reposirory.RoleRepository;
import ru.kata.spring.boot_security.demo.reposirory.UserRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@Component
public class test {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public test(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void test() {

        User user = new User("User", "lastUser", "user@mail.ru", "$2a$10$UDGzZijDidvn4Rc560CbC.iG6RmQnJqONdl2woEi5PEb5QqK7mMca"); //user
        User admin = new User("Admin", "lastAdmin", "admin@mail.ru", "$2a$10$elhX3Rtd7jSJwIVkQqiypehVc1lXfkNw3PmeLt9vsXxxUpnOMdyP6"); //admin

        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");

        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);

        Set<Role> userRoles = new HashSet<>();
        Set<Role> adminRoles = new HashSet<>();

        userRoles.add(roleUser);
        adminRoles.add(roleAdmin);

        user.setRoles(userRoles);
        admin.setRoles(adminRoles);

        userRepository.save(user);
        userRepository.save(admin);

    }

}
