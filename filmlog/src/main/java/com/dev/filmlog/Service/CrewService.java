package com.dev.filmlog.Service;

import com.dev.filmlog.Api.ApiException;
import com.dev.filmlog.Model.Crew;
import com.dev.filmlog.Repository.CrewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrewService {

    private final CrewRepository crewRepository;

    public void addCrew(Crew crew){
        crewRepository.save(crew);
    }

    public List<Crew> getAllCrew(){
        return crewRepository.findAll();
    }

    public Crew getCrewById(Integer crewId){
        return crewRepository.findCrewById(crewId);
    }

    public void updateCrew(Integer crewId, Crew crew){
        Crew oldCrew =crewRepository.findCrewById(crewId);
        if (oldCrew ==null){
            throw new ApiException("Crew not found");
        }

        oldCrew.setName(crew.getName());
        oldCrew.setDescription(crew.getDescription());
        oldCrew.setRole(crew.getRole());
        oldCrew.setImageUrl(crew.getImageUrl());
        crewRepository.save(oldCrew);
    }

    public void deleteCrew(Integer crewId){
        Crew crew =crewRepository.findCrewById(crewId);
        if (crew ==null){
            throw new ApiException("Crew not found");
        }

        crewRepository.delete(crew);
    }
}
