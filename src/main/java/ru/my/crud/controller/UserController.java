package ru.my.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

//    @PathVariable - работает с новой страницей, т.е. в маппинге должно быть что-то типа "/{i}", где i-это именно та переменная, которую @PathVariable ищет
//    @RequestParam - работает с нынешней странице, ищет в ней запросы типа ...?i=...&p=... и так далее. Причем, если в html файле
//    есть запрос типа /users(id=${user.getId()}), то /users - это страница, а выражение (id=${user.getId()}) будет восприниматься как запрос "?id=1",
//    последующие выражения в скобках будут дополнительными запросами через &
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
    public String updateUser(@RequestBody User user, Model model) {
        userService.update(user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") Integer id) {
        userService.delete(userService.findById(id));
        return "redirect:/users";
    }
}
