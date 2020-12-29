package com.informatorio.myblog.repository;

import com.informatorio.myblog.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
       List<Post> findAllByTitleContaining(String title);
       
       List<Post> findAllByPublishedIs(Boolean condition);
       
}
