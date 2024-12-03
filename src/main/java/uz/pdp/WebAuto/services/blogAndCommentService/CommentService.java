package uz.pdp.WebAuto.services.blogAndCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.blog.Comment;
import uz.pdp.WebAuto.enums.ObjectType;
import uz.pdp.WebAuto.repository.blogAndCommentRepository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogPostService blogPostService;

    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByObjectIdAndObjectType(postId, ObjectType.BLOG);
    }

    public Comment addCommentToPost(Long postId, Comment comment) {
        comment.setObjectId(postId);
        comment.setObjectType(ObjectType.BLOG);
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, Comment updatedComment) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        existingComment.setText(updatedComment.getText());
        return commentRepository.save(existingComment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
