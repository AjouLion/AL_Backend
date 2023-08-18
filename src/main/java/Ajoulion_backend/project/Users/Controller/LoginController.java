package Ajoulion_backend.project.Users.Controller;

import Ajoulion_backend.project.Error.CustomException;
import Ajoulion_backend.project.JWT.AuthRequest;
import Ajoulion_backend.project.JWT.JwtProvider;
import Ajoulion_backend.project.JWT.TokenResponse;
import Ajoulion_backend.project.Table.DTO.UserSimpleDto;
import Ajoulion_backend.project.Table.DTO.UsersDto;
import Ajoulion_backend.project.Table.Entity.Users;
import Ajoulion_backend.project.Users.Service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static Ajoulion_backend.project.Error.ErrorCode.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class LoginController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/join/donator")
    public ResponseEntity<?> donatorJoin(@RequestPart(name="user") UsersDto userDto,
                                         @RequestPart(name="profile", required = false) MultipartFile profileImage) {
        userService.join(0, userDto, profileImage, null); // 0 : 기부자
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @PostMapping("/join/receiver")
    public ResponseEntity<?> receiverJoin(@RequestPart(name="user") UsersDto userDto,
                                          @RequestPart(name="profile", required = false) MultipartFile profileImage,
                                          @RequestPart(name="certification") MultipartFile certificationImage) {
        userService.join(1, userDto, profileImage, certificationImage); // 1 : 수혜자
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @PatchMapping("/update/image")
    public ResponseEntity<?> uploadProfileImage(@RequestHeader HttpHeaders header,
                                                @RequestPart(name="profile") MultipartFile profileImage) throws Exception {
        Long userId = userService.loginCheck(header);
        userService.uploadProfileImage(userId, profileImage);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @PostMapping("/login")
    public  ResponseEntity<TokenResponse> login(@RequestBody AuthRequest authRequest) throws Exception {
        log.info("id="+authRequest.getId()+" pw="+authRequest.getPassword());
        UserSimpleDto user = userService.login(authRequest.getId(), authRequest.getPassword());
        try {
            log.info("user="+user);
            String token = jwtProvider.createToken(user.getUserId().toString()); // 토큰 생성
            log.info("token="+token);
            Claims claims = jwtProvider.parseJwtToken("Bearer " + token); // 토큰 검증
            TokenResponse tokenResponse = new TokenResponse(token,
                    "Bearer",
                    claims.getIssuedAt().toString(),
                    claims.getExpiration().toString(),
                    user.getName(),
                    user.getProfile(),
                    user.getCategory());
            return ResponseEntity.ok().body(tokenResponse);
        } catch(Exception e) {
            throw new CustomException(ERR_AUTHORIZED);
        }
     }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader HttpHeaders header) {
        //session.invalidate();
        log.info("logout");
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestHeader HttpHeaders header){
        Long userId = userService.loginCheck(header);
        userService.deleteupdate(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Delete Success");
    }

    @PatchMapping("/update/info")
    public ResponseEntity update(@RequestHeader HttpHeaders header, @RequestBody UsersDto usersDto) throws Exception {
        Long userId = userService.loginCheck(header);

        userService.update(userId, usersDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Update Success");
    }
}
