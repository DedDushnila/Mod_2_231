package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.models.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }



    @GetMapping("/addUser")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";

    }
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "/addUser";
    }


    @GetMapping("/updateUser")
    public String updateUser() {
        return "updateUser";
    }
    @PostMapping("/updateUser")
    public String updateUserForm(@RequestParam(required = false) Long id,
                                 @RequestParam(required = false) String newName,
                                 @RequestParam(required = false) String newLastName,
                                 @RequestParam(required = false) Integer newAge) {
        userService.updateUser(id, newName, newLastName, newAge);
        return "redirect:/users";
    }


    @GetMapping("/deleteUser")
    public String deleteUser() {
        return "deleteUser";
    }
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}