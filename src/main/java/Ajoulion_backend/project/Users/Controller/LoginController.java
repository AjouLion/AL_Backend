package Ajoulion_backend.project.Users.Controller;

import Ajoulion_backend.project.Table.DTO.LoginDto;
import Ajoulion_backend.project.Table.DTO.UsersDto;
import Ajoulion_backend.project.Users.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UsersDto> create(@RequestBody UsersDto userDto){
        userService.Join(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UsersDto> login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        UsersDto login = userService.login(loginDto);
        if (login == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            log.info("login success");
            log.info(session.toString());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(login);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        log.info("logout");
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
