package uz.pdp.WebAuto.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.dtos.comment.CommentDTO;
import uz.pdp.WebAuto.entity.Comment;
import uz.pdp.WebAuto.entity.User;
import uz.pdp.WebAuto.enums.ObjectType;
import uz.pdp.WebAuto.repository.CommentRepository;
import uz.pdp.WebAuto.repository.UserRepository;
import uz.pdp.WebAuto.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentDTO save(CommentDTO commentDTO) {
        Comment comment = toEntity(commentDTO);
        comment = commentRepository.save(comment);
        return toDto(comment);
    }

    @Override
    public CommentDTO getById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return toDto(comment);
    }

    @Override
    public List<CommentDTO> getAllByObjectTypeAndObjectId(String objectType, Long objectId) {
        List<Comment> comments = commentRepository.findAllByObjectTypeAndObjectId(
                ObjectType.valueOf(objectType), objectId);
        return comments.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getAll() {
        return commentRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public CommentDTO update(Long id, CommentDTO commentDTO) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        existingComment.setText(commentDTO.getText());


        User owner = userRepository.findById(commentDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingComment.setOwnerId(owner);  // Устанавливаем ownerId
        existingComment.setObjectType(ObjectType.valueOf(commentDTO.getObjectType()));
        existingComment.setObjectId(commentDTO.getObjectId());

        return toDto(commentRepository.save(existingComment));
    }

    @Override
    public void delete(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new RuntimeException("Comment not found");
        }
        commentRepository.deleteById(id);
    }

    private CommentDTO toDto(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getText(),
                comment.getOwnerId().getId(),
                comment.getObjectType().name(),
                comment.getObjectId()
        );
    }

    private Comment toEntity(CommentDTO commentDTO) {

        User owner = userRepository.findById(commentDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return Comment.builder()
                .text(commentDTO.getText())
                .ownerId(owner)  // Устанавливаем User через репозиторий
                .objectType(ObjectType.valueOf(commentDTO.getObjectType()))
                .objectId(commentDTO.getObjectId())
                .build();
    }
}
