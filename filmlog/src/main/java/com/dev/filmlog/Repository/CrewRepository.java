package com.dev.filmlog.Repository;

import com.dev.filmlog.Model.Crew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewRepository extends JpaRepository<Crew,Integer> {

    Crew findCrewById(Integer id);
}
