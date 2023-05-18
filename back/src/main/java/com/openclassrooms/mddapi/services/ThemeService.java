/**
 * This class represents a service for managing themes.
 */
@Service
public class ThemeService {

    private final ThemeRepository themeRepository;

    /**
     * Constructs a new ThemeService with the given ThemeRepository.
     *
     * @param themeRepository the ThemeRepository to use
     */
    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    /**
     * Deletes the theme with the given ID.
     *
     * @param id the ID of the theme to delete
     */
    public void delete(Long id) {
        this.themeRepository.deleteById(id);
    }

    /**
     * Finds the theme with the given ID.
     *
     * @param id the ID of the theme to find
     * @return the theme with the given ID, or null if it does not exist
     */
    public Theme findById(Long id) {
        return this.themeRepository.findById(id).orElse(null);
    }

    /**
     * Finds all themes.
     *
     * @return a list of all themes
     */
    public List<Theme> findAll() {
        return this.themeRepository.findAll();
    }

    /**
     * Creates a new theme.
     *
     * @param theme the theme to create
     * @return the created theme
     */
    public Theme create(Theme theme) {
        return this.themeRepository.save(theme);
    }

}
