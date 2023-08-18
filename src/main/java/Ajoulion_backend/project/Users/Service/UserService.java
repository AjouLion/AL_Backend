package Ajoulion_backend.project.Users.Service;

import Ajoulion_backend.project.Error.CustomException;
import Ajoulion_backend.project.ImageUpload.ImageUpload;
import Ajoulion_backend.project.JWT.JwtProvider;
import Ajoulion_backend.project.Table.DTO.UserSimpleDto;
import Ajoulion_backend.project.Table.DTO.UsersDto;
import Ajoulion_backend.project.Table.Entity.Device;
import Ajoulion_backend.project.Table.Entity.Users;
import Ajoulion_backend.project.Users.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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
    public Long join(Integer category,
                     UsersDto userDto,
                     MultipartFile profileImage,
                     MultipartFile certificationImage) throws CustomException {
        Users user = userRepository.findById(userDto.getId());
        if (user != null) {
            throw new CustomException(ERR_DUPLICATE_ID);
        }
        userDto.setCategory(category);
        userDto.setIsDeleted(false);
        userDto.setProfile(null);
        userDto.setCertification(null);
        user = new Users(userDto);
        userRepository.save(user);
        Long userId = user.getUserId();
        uploadProfileImage(userId, profileImage);
        uploadCertificationImage(userId, certificationImage);
        return userId;
    }

    @Transactional
    public void uploadProfileImage(Long userId, MultipartFile profileImage) throws CustomException {
        if (profileImage == null) return;
        Users user = getUser(userId);
        String filename = ImageUpload.uploadProfileImage(user.getUserId(), profileImage);
        if (filename == null) {
            throw new CustomException(ERR_IMAGE_PROFILE);
        }
        if (filename != user.getProfile()) ImageUpload.deleteProfileImage(user);
        user.setProfile(filename);
    }

    @Transactional
    public void uploadCertificationImage(Long userId, MultipartFile certificationImage) throws CustomException {
        if (certificationImage == null) return;
        Users user = getUser(userId);
        String filename = ImageUpload.uploadCertificationImage(user.getUserId(), certificationImage);
        if (filename == null) {
            throw new CustomException(ERR_IMAGE_CERTIFICATION);
        }
        if (filename != user.getCertification()) ImageUpload.deleteCertificationImage(user);
        user.setCertification(filename);
    }

    public UserSimpleDto login(String id, String password){
        Users user = userRepository.findById(id);
        if (user == null)  return null;
        if (user.getIsDeleted() == true) return null; // delete user
        if (user.getPassword().equals(password)) {
            return (new UserSimpleDto(user));
        }
        return null;
    }

    public Users getUser(Long userId) {
        Users user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new CustomException(ERR_USER_NOT_EXIST);
        }
        return user;
    }

    public UserSimpleDto getUserInfo(Long userId) {
        return new UserSimpleDto(getUser(userId));
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

    @Transactional
    public void deleteupdate(Long userId) {
        Users user = getUser(userId);
        ImageUpload.deleteProfileImage(user);
        ImageUpload.deleteCertificationImage(user);
//        user.setName("deleted");
//        user.setId("deleted");
//        user.setCertification(null);
//        user.setProfile(null);
//        user.setPassword(null);
        user.setIsDeleted(true);
    }

    @Transactional
    public void update(Long userId, UsersDto usersDto) {
        Users user = getUser(userId);
        user.setName(usersDto.getName());
        user.setId(usersDto.getId());
        user.setPassword(usersDto.getPassword());
    }
}


