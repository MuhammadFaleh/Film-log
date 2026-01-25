package com.dev.filmlog.Repository;

import com.dev.filmlog.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    List<Review> findReviewsById(Integer id);
}
