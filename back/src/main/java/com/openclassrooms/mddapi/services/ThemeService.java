package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {

    private final ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public void delete(Long id) {
        this.themeRepository.deleteById(id);
    }

    public Theme findById(Long id) {
        return this.themeRepository.findById(id).orElse(null);
    }

    public List<Theme> findAll() {
        return this.themeRepository.findAll();
    }

    public Theme create(Theme theme) {
        return this.themeRepository.save(theme);
    }

}
