package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.UserDto;
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

    /**
     * Finds a user by their email address.
     *
     * @param authentication the authentication object containing the user's email address
     * @return a ResponseEntity containing the user's information if found, or a not found response if not found
     */
    @GetMapping("")
    public ResponseEntity<UserDto> findByEmail(Authentication authentication) {
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

    /**
     * Updates the user's name and email address.
     *
     * @param user the User object containing the updated name and email address
     * @param authentication the authentication object containing the user's email address
     * @return a ResponseEntity containing the updated user's information if successful, or a not found response if the user is not found
     */
    @PutMapping("")
    public ResponseEntity<UserDto> update(@RequestBody User user, Authentication authentication) {
        try {

            User userToUpdate = this.userService.findByEmail(authentication.getName());

            if (userToUpdate == null) {
                return ResponseEntity.notFound().build();
            }

            userToUpdate.setName(user.getName());
            userToUpdate.setEmail(user.getEmail());

            this.userService.create(userToUpdate);
            return ResponseEntity.ok().body(this.userMapper.toDto(userToUpdate));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Deletes the user associated with the authenticated email address.
     *
     * @param authentication the authentication object containing the user's email address
     * @return a ResponseEntity indicating whether the deletion was successful or not
     */
    @DeleteMapping("")
    public ResponseEntity<?> delete(Authentication authentication) {
        try {
            User user = this.userService.findByEmail(authentication.getName());

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!Objects.equals(userDetails.getUsername(), user.getEmail())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            this.userService.delete(user.getId());
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Subscribes the authenticated user to a theme with the given ID.
     *
     * @param themeId the ID of the theme to subscribe to
     * @param authentication the authentication object containing the user's email address
     * @return a ResponseEntity indicating whether the subscription was successful or not
     */
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

    /**
     * Unsubscribes the authenticated user from a theme with the given ID.
     *
     * @param themeId the ID of the theme to unsubscribe from
     * @param authentication the authentication object containing the user's email address
     * @return a ResponseEntity indicating whether the unsubscription was successful or not
     */
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
