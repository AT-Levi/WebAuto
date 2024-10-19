package uz.pdp.WebAuto.repository;

import uz.pdp.WebAuto.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost,Long> {

}
