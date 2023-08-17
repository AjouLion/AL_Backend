package Ajoulion_backend.project.Users.Service;

import Ajoulion_backend.project.Error.CustomException;
import Ajoulion_backend.project.JWT.JwtProvider;
import Ajoulion_backend.project.Table.DTO.UserSimpleDto;
import Ajoulion_backend.project.Table.DTO.UsersDto;
import Ajoulion_backend.project.Table.Entity.Users;
import Ajoulion_backend.project.Users.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static Ajoulion_backend.project.Error.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    //회원 가입
    public void join(UsersDto usersDto) throws CustomException {
        Users user = userRepository.findById(usersDto.getId());
        if (user != null) {
            throw new CustomException(ERR_DUPLICATE_ID);
        }
        user = new Users(usersDto);
        userRepository.save(user);
    }

    public UserSimpleDto login(String id, String password){
        Users user = userRepository.findById(id);
        if (user == null)  return null;
        if (user.getPassword().equals(password)) {
            return (new UserSimpleDto(user));
        }
        return null;
    }

    public UserSimpleDto getUserInfo(Long userId) {
        Users user = userRepository.findByUserId(userId);
        return (new UserSimpleDto(user));
    }

    public Long loginCheck(HttpHeaders header) throws CustomException {
        String token = header.getFirst("Authorization");
        log.info("token="+token);
        try {
            Claims claims = jwtProvider.parseJwtToken(token);
            Users user = userRepository.findByUserId(Long.parseLong(claims.getSubject()));
            return user.getUserId();
        } catch (Exception e) {
            log.info(ERR_UNAUTHORIZED.getMessage());
            throw new CustomException(ERR_UNAUTHORIZED);
        }
    }

    public void delete(Long userId) {
        userRepository.deleteByUserId(userId);
    }

    public void updateInfo(Long userId, UsersDto usersDto) {
        Users user = userRepository.findByUserId(userId);
        user.setName(usersDto.getName());
        user.setId(usersDto.getId());
        user.setPassword(usersDto.getPassword());
        user.setCertification(usersDto.getCertification());
        userRepository.save(user);
    }

    public void updateImg(Long userId, UsersDto usersDto) {
        Users user = userRepository.findByUserId(userId);
        user.setProfile(usersDto.getProfile());
        userRepository.save(user);
    }
}


