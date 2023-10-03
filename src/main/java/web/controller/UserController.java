package web.controller;

import org.springframework.ui.Model;
import web.model.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService service;
    @Autowired
    public UserController (UserService service) {
        this.service = service;
    }

    @GetMapping()
    public String printUsers(Model model) {
        model.addAttribute("list", service.getUsers());
        return "/users.html";
    }

    @GetMapping("/user")
    public String printUser(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        return "user";
    }

    @GetMapping("/user/edit")
    public String editUser(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        return "edit";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        service.addUser(user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") Long id) {
        service.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user) {
        service.editUser(user);
        return "redirect:/users";
    }
}