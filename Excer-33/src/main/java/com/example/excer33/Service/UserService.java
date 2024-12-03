package com.example.excer33.Service;

import com.example.excer33.ApiResponse.ApiException;
import com.example.excer33.Model.User;
import com.example.excer33.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Integer id,User user) {
        User oldUser = userRepository.findUserById(id);
        if (oldUser == null) {
            throw new ApiException("id not found");
        }
        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        oldUser.setAge(user.getAge());
        userRepository.save(oldUser);


    }

    public void deleteUser(Integer id) {
        User delUser = userRepository.findUserById(id);
        if (delUser == null) {
            throw new ApiException("id not found");

        }
        userRepository.delete(delUser);

    }

    public String checkUsernameAndPassword(String username, String password) {
        for (int i = 0; i < userRepository.findAll().size(); i++) {
            if (userRepository.findAll().get(i).getUsername().equals(username)) {
                if (userRepository.findAll().get(i).getPassword().equals(password)) {
                    return "Correct";
                }
            }
        }
        return "Not correct";
    }

    public User getUserByEmail(String email) {
        User userEmail = userRepository.findUserByEmail(email);
        if (userEmail == null) {
            throw new ApiException("email not found");
        }
        return userEmail;
    }

    public List<User> getUsersByRole(String role) {
        List<User> usersRole = userRepository.getUsersByRole(role);
        if (usersRole == null) {
            throw new ApiException("role not found");
        }
        return usersRole;
    }

    public List<User> getUserByAge(Integer age) {
        List<User> usersAge = userRepository.getUsersByAge(age);
        if (usersAge == null) {
            throw new ApiException("age not found");
        }
        return usersAge;
    }
}
