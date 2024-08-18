package ru.my.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.my.crud.models.User;
import ru.my.crud.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUserById(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id == null) {
            model.addAttribute("users", userService.findAll());
            return "users/show_all";
        } else {
            model.addAttribute("user", userService.findById(id));
            return "users/show_user";
        }
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new_user";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id == null) {
            model.addAttribute("users", userService.findAll());
            return "users/show_all";
        } else {
            model.addAttribute("user", userService.findById(id));
            return "users/edit_user";
        }
    }

    @PostMapping("/edit")
    public String updateUser(@RequestParam(value = "id", required = false) Integer id,
                             @ModelAttribute("user") User user) {
        userService.update(id, user);

        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") Integer id) {
        userService.delete(userService.findById(id));
        return "redirect:/users";
    }
}
