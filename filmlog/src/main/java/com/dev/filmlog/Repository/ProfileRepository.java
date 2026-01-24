package com.dev.filmlog.Repository;

import com.dev.filmlog.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findProfileById(Integer id);
}
