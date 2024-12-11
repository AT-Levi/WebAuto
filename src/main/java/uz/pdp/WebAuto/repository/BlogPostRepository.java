package uz.pdp.WebAuto.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.WebAuto.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE BlogPost bp SET bp.deleted = true WHERE bp.id = :id")
    void deletePostById(Long id);

    List<BlogPost> findByAuthorId(Long ownerId); // @Query talab qilinmaydi
}
