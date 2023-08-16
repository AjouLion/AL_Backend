package Ajoulion_backend.project.Reciever.Controller;

import Ajoulion_backend.project.Reciever.Service.ReceiverService;
import Ajoulion_backend.project.Table.DTO.ApplyDto;
import Ajoulion_backend.project.Users.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ReceiverController {

    private final ReceiverService recvService;
    private final UserService userService;

    @GetMapping("/receiver")
    public ResponseEntity<List<Map<String, Object>>> getApplyList(@RequestHeader HttpHeaders header) {
        Long userId = userService.loginCheck(header);

        log.info("in getApplyList");
        List<Map<String, Object>> list = recvService.getApplyList();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
    }

}
