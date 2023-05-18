package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.UserLtdDto;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;


    public UserController(UserService userService, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<?> findByEmail(Authentication authentication) {
        try {
            User user = this.userService.findByEmail(authentication.getName());

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(this.userMapper.toDto(user));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody User user, Authentication authentication) {
        try {

            User userToUpdate = this.userService.findByEmail(authentication.getName());

            if (userToUpdate == null) {
                return ResponseEntity.notFound().build();
            }

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


            userToUpdate.setName(user.getName());
            userToUpdate.setEmail(user.getEmail());

            this.userService.create(userToUpdate);
            return ResponseEntity.ok().body(this.userMapper.toDto(userToUpdate));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        try {
            User user = this.userService.findById(Long.valueOf(id));

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!Objects.equals(userDetails.getUsername(), user.getEmail())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            this.userService.delete(Long.parseLong(id));
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/subscribe/{themeId}")
    public ResponseEntity<?> subscribe(@PathVariable("themeId") String themeId, Authentication authentication) {
        try {
            User user = this.userService.findByEmail(authentication.getName());

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            this.userService.subscribeToTheme(user, Long.parseLong(themeId));
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/unsubscribe/{themeId}")
    public ResponseEntity<?> unsubscribe(@PathVariable("themeId") String themeId, Authentication authentication) {
        try {
            User user = this.userService.findByEmail(authentication.getName());

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            this.userService.unsubscribeToTheme(user, Long.parseLong(themeId));
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
