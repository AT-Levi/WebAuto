package uz.pdp.WebAuto.repository;

import uz.pdp.WebAuto.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
