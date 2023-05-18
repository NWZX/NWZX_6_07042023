package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;

    public UserService(UserRepository userRepository, ThemeRepository themeRepository) {
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
    }

    public User create(User user) {
        return this.userRepository.save(user);
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

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
