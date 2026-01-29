package com.dev.filmlog.Service;

import com.dev.filmlog.Api.ApiException;
import com.dev.filmlog.DTO.In.UserDTOIn;
import com.dev.filmlog.DTO.Out.ProfileDTOOut;
import com.dev.filmlog.DTO.Out.UserDTOOut;
import com.dev.filmlog.Model.Profile;
import com.dev.filmlog.Model.User;
import com.dev.filmlog.Repository.ProfileRepository;
import com.dev.filmlog.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Cacheable("users")
    public List<UserDTOOut> getUsers(){
        List<UserDTOOut> dtoOuts = new ArrayList<>();
        for(User user : userRepository.findAll()){
            dtoOuts.add(convertToDTO(user));
        }
        return dtoOuts;
    }

    public void createUser(UserDTOIn dto){
        if(userRepository.findUserByUsername(dto.getUsername()) != null){
            throw new ApiException("username exists");
        }
        if(userRepository.findUserByEmail(dto.getEmail()) != null){
            throw new ApiException("email exists");
        }
        String hash = new BCryptPasswordEncoder().encode(dto.getPassword());
        User user = convertToEntity(dto);
        user.setPassword(hash);
        userRepository.save(user);
        profileRepository.save(Profile.builder().name(user.getUsername()).description("").user(user).build());
    }

    public void updateUser(UserDTOIn dto, Integer id){
        User user = userRepository.findUserById(id);

        if(user == null){
            throw new ApiException("no user found");
        }

        user.setPassword(dto.getPassword());
        user.setFullName(user.getFullName());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Integer id){
        User user = userRepository.findUserById(id);

        if(user == null){
            throw new ApiException("no user found");
        }
        userRepository.delete(user);
    }

    public UserDTOOut getMyUser(Integer id){
        User user = userRepository.findUserById(id);

        if(user == null){
            throw new ApiException("user not found");
        }
        return convertToDTO(user);
    }

    public UserDTOOut convertToDTO(User user){
        return UserDTOOut.builder().email(user.getEmail())
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole()).build();
    }

    public User convertToEntity(UserDTOIn dto){
        return User.builder().email(dto.getEmail())
                .role("USER")
                .phoneNumber(dto.getPhoneNumber())
                .password(dto.getPassword())
                .fullName(dto.getFullName())
                .username(dto.getUsername()).build();
    }
}
