package uz.pdp.WebAuto.repository.blogAndCommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.WebAuto.entity.blog.BlogPost;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    /*@Query("SELECT b FROM BlogPost b WHERE b.category = :category AND b.id <> :id")
    List<BlogPost> findRelatedPosts(@Param("category") String category, @Param("id") Long id);*/
}