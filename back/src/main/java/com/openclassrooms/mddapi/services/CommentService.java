/**
 * This class represents a service for managing comments.
 */
@Service
public class CommentService {

    /**
     * The repository for managing comments.
     */
    private final CommentRepository commentRepository;

    /**
     * Constructs a new CommentService with the given CommentRepository.
     *
     * @param commentRepository the repository for managing comments.
     */
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Deletes the comment with the given id.
     *
     * @param id the id of the comment to delete.
     */
    public void delete(Long id) {
        this.commentRepository.deleteById(id);
    }

    /**
     * Finds the comment with the given id.
     *
     * @param id the id of the comment to find.
     * @return the comment with the given id, or null if not found.
     */
    public Comment findById(Long id) {
        return this.commentRepository.findById(id).orElse(null);
    }

    /**
     * Finds all comments for the given article.
     *
     * @param articleId the id of the article to find comments for.
     * @return a list of all comments for the given article.
     */
    public List<Comment> findAllByArticle(Long articleId) {
        return this.commentRepository.findAllByArticleId(articleId);
    }

    /**
     * Creates a new comment.
     *
     * @param comment the comment to create.
     * @return the created comment.
     */
    public Comment create(Comment comment) {
        return this.commentRepository.save(comment);
    }
}
