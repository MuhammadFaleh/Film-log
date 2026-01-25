package com.dev.filmlog.Repository;

import com.dev.filmlog.Model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media,Integer> {

    List<Media> findMediaById(Integer id);
}
