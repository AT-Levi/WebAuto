package uz.pdp.WebAuto.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.WebAuto.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findByAuthor_Id(Long authorId);
}

