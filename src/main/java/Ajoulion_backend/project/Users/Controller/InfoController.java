package Ajoulion_backend.project.Users.Controller;

import Ajoulion_backend.project.JWT.JwtProvider;
import Ajoulion_backend.project.Table.DTO.UserSimpleDto;
import Ajoulion_backend.project.Users.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class InfoController {

    private final UserService userService;

    @GetMapping("/myinfo")
    public ResponseEntity<UserSimpleDto> myInfo(@RequestHeader HttpHeaders header) {
        Long userId = userService.loginCheck(header);
        UserSimpleDto user = userService.getUserInfo(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
