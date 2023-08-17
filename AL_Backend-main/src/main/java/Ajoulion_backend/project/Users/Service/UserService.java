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
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import static Ajoulion_backend.project.Error.ErrorCode.*;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    //회원 가입
    public void join(UsersDto usersDto, MultipartFile profile) throws CustomException, IOException {

        try {
            String sep = File.separator;
            String today = new SimpleDateFormat("yyMMdd").format(new Date());
            File file = new File("");
            String rootPath = file.getAbsolutePath().split("src")[0];
            String savePath = rootPath + sep + "profileImg" + sep + today;
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdirs();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            String originFileName = profile.getOriginalFilename();
            String saveFileName = UUID.randomUUID().toString() + originFileName.substring(originFileName.lastIndexOf("."));
            String filePath = savePath + sep + saveFileName;
            profile.transferTo(new File(filePath));


            Users user = userRepository.findById(usersDto.getId());
            if (user != null) {
                throw new CustomException(ERR_DUPLICATE_ID);
            }
            user = new Users(usersDto);
            user.setProfileUrl(filePath);

            userRepository.save(user);
        } catch(Exception e){
            e.printStackTrace();
        }
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
}


