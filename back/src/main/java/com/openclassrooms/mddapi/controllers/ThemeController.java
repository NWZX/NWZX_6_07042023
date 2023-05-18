package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.services.ThemeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/themes")
public class ThemeController {

    private final ThemeService themeService;
    private final ThemeMapper themeMapper;

    public ThemeController(ThemeService themeService, ThemeMapper themeMapper) {
        this.themeService = themeService;
        this.themeMapper = themeMapper;
    }

    /**
     * This method handles the GET request to retrieve all themes.
     * @return ResponseEntity with a list of Theme DTOs in the response body.
     */
    @GetMapping("")
    public ResponseEntity<List<ThemeDto>> findAll() {
        List<Theme> themes = this.themeService.findAll();

        return ResponseEntity.ok().body(this.themeMapper.toDto(themes));
    }

}
