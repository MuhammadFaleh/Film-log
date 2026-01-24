package com.dev.filmlog.Repository;

import com.dev.filmlog.Model.ListMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListMediaRepository extends JpaRepository<ListMedia, Integer> {
    ListMedia findListMediaById(Integer id);
    List<ListMedia> findListMediaByUserListId(Integer id);
}
