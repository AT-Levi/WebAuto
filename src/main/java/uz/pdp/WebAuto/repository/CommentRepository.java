package uz.pdp.WebAuto.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.WebAuto.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.WebAuto.enums.ObjectType;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByObjectTypeAndObjectId(ObjectType objectType, Long objectId);
}