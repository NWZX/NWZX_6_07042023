package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * This class provides services related to the User model.
 */
@Service
public class UserService {
    
    /**
     * The user repository used to interact with the database.
     */
    private final UserRepository userRepository;
    
    /**
     * The theme repository used to interact with the database.
     */
    private final ThemeRepository themeRepository;

    /**
     * Constructor for the UserService class.
     * @param userRepository The user repository used to interact with the database.
     * @param themeRepository The theme repository used to interact with the database.
     */
    public UserService(UserRepository userRepository, ThemeRepository themeRepository) {
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
    }

    /**
     * Creates a new user in the database.
     * @param user The user to be created.
     * @return The created user.
     */
    public User create(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Deletes a user from the database.
     * @param id The id of the user to be deleted.
     */
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    /**
     * Finds a user by their id.
     * @param id The id of the user to be found.
     * @return The found user, or null if not found.
     */
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    /**
     * Finds a user by their email.
     * @param email The email of the user to be found.
     * @return The found user, or null if not found.
     */
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    /**
     * Subscribes a user to a theme.
     * @param user The user to be subscribed.
     * @param themeId The id of the theme to be subscribed to.
     * @throws NotFoundException If the theme is not found.
     * @throws BadRequestException If the user is already subscribed to the theme.
     */
    public void subscribeToTheme(User user, Long themeId) {
        Theme theme = this.themeRepository.findById(themeId).orElse(null);

        if (theme == null) {
            throw new NotFoundException();
        }

        boolean alreadyParticipate = user.getThemes().stream().anyMatch(o -> o.getId().equals(themeId));
        if(alreadyParticipate) {
            throw new BadRequestException();
        }

        user.getThemes().add(theme);

        this.userRepository.save(user);
    }

    /**
     * Unsubscribes a user from a theme.
     * @param user The user to be unsubscribed.
     * @param themeId The id of the theme to be unsubscribed from.
     * @throws NotFoundException If the theme is not found.
     * @throws BadRequestException If the user is not subscribed to the theme.
     */
    public void unsubscribeToTheme(User user, Long themeId) {
        Theme theme = this.themeRepository.findById(themeId).orElse(null);

        if (theme == null) {
            throw new NotFoundException();
        }

        boolean alreadyParticipate = user.getThemes().stream().anyMatch(o -> o.getId().equals(themeId));
        if(!alreadyParticipate) {
            throw new BadRequestException();
        }

        user.setThemes(user.getThemes().stream().filter(o -> !o.getId().equals(themeId)).collect(Collectors.toList()));

        this.userRepository.save(user);
    }
}
