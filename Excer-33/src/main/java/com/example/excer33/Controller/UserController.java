package com.example.excer33.Controller;

import com.example.excer33.ApiResponse.ApiResponse;
import com.example.excer33.Model.User;
import com.example.excer33.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        List<User> userList = userService.findAllUsers();
        return ResponseEntity.status(200).body(userList);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("user added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.updateUser(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
    }

    @GetMapping("check-username-passwors/{username}/{password}")
    public ResponseEntity checkUsernameAndPassword(@PathVariable String username, @PathVariable String password){
        String res = userService.checkUsernameAndPassword(username, password);
        if (res.equals("Correct")){
            return ResponseEntity.status(200).body(new ApiResponse("username and password correct"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("username and password not correct"));
    }

    @GetMapping("/get-by-email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        User user = userService.getUserByEmail(email);
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping("/get-by-role/{role}")
    public ResponseEntity getUserByRole (@PathVariable String role){
        List<User> userList = userService.getUsersByRole(role);
        return ResponseEntity.status(200).body(userList);
    }

    @GetMapping("/get-by-age/{age}")
    public ResponseEntity getUserbyAge(@PathVariable Integer age){
        List<User> userListAge = userService.getUserByAge(age);
        return ResponseEntity.status(200).body(userListAge);
    }

}
