package com.dev.filmlog.Service;

import com.dev.filmlog.Api.ApiException;
import com.dev.filmlog.DTO.In.ProfileDTOIn;
import com.dev.filmlog.DTO.In.UserDTOIn;
import com.dev.filmlog.DTO.Out.ProfileDTOOut;
import com.dev.filmlog.DTO.Out.UserDTOOut;
import com.dev.filmlog.Model.Profile;
import com.dev.filmlog.Model.User;
import com.dev.filmlog.Repository.ProfileRepository;
import com.dev.filmlog.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Cacheable("profiles")
    public List<ProfileDTOOut> getProfiles(){
        List<ProfileDTOOut> dtoOuts = new ArrayList<>();
        for(Profile profile : profileRepository.findAll()){
            dtoOuts.add(convertToDTO(profile));
        }
        return dtoOuts;
    }

    public ProfileDTOOut getMyProfile(Integer id){
        Profile profile = profileRepository.findProfileById(id);

        if(profile == null){
            throw new ApiException("user not found");
        }
        return convertToDTO(profile);
    }

//    public void createProfile(ProfileDTOIn dto, Integer id){
//        User user = userRepository.findUserById(id);
//
//        if(user == null){
//            throw new ApiException("user not found");
//        }
//
//        Profile profile = convertToEntity(dto);
//        profile.setUser(user);
//        profileRepository.save(profile);
//    }

    public void updateProfile(ProfileDTOIn dto, Integer id){
        Profile profile = profileRepository.findProfileById(id);

        if(profile == null){
            throw new ApiException("user not found");
        }

        profile.setDescription(dto.getDescription());
        profile.setName(dto.getName());
        profileRepository.save(profile);
    }

    public ProfileDTOOut convertToDTO(Profile profile){
        return ProfileDTOOut.builder()
                .profileName(profile.getName())
                .description(profile.getDescription())
                .build();
    }

    public Profile convertToEntity(ProfileDTOIn dto){
        return Profile.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

}
