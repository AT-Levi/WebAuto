package uz.pdp.WebAuto.repository.blogAndCommentRepository;
import uz.pdp.WebAuto.entity.blog.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.WebAuto.enums.ObjectType;
import java.util.List;
   public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByObjectIdAndObjectType(Long objectId, ObjectType objectType);
   }
