package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userServiceImpl;
    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userServiceImpl = userService;
    }

    @GetMapping()
    public String findAll(Model model) {
        List<User> users = userServiceImpl.getAllUsers();
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("users", users);
        model.addAttribute("roles", userServiceImpl.listRoles());
        model.addAttribute("user1", user);
        return "admin";
    }

    @PostMapping("/new")
    public String createUser(User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") User user, @RequestParam("roles") Long id) {
        userServiceImpl.update(user);
        return "redirect:/admin";
    }

    @PostMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String userInfoPage(Principal principal, Model model) {
        User user = userServiceImpl.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
