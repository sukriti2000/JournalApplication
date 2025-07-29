package net.engineeringdigest.journalApp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.EmailService;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin APIs")
public class adminController {

    private AppCache appCache;
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userService.getAll();
        if(all!=null & !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public void createAdmin(@RequestBody User user){
        System.out.println(user);
        userService.saveAdmin(user);
    }
    @GetMapping("/clear-app-cache")
    public  void clearAppCache(){
       appCache.init();
    }
    @PostMapping("/send-mail")
    public  void sendMail(){
        emailService.sendEmail("jeenulalka00@gmail.com","hahaha test","testing testing");
    }
}
