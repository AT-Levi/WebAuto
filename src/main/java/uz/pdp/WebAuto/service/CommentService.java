package uz.pdp.WebAuto.service;

import uz.pdp.WebAuto.dtos.comment.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO save(CommentDTO commentDTO);

    CommentDTO getById(Long id);

    List<CommentDTO> getAllByObjectTypeAndObjectId(String objectType, Long objectId);

    List<CommentDTO> getAll();

    CommentDTO update(Long id, CommentDTO commentDTO);

    void delete(Long id);
}
