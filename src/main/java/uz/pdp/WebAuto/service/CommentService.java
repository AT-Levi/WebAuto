package uz.pdp.WebAuto.service;

import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.Comment;
import uz.pdp.WebAuto.enums.ObjectType;
import uz.pdp.WebAuto.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsForObject(Long objectId, ObjectType objectType) {
        return commentRepository.findByObjectIdAndObjectType(objectId, objectType);
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }
}

