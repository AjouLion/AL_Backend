package Ajoulion_backend.project.Reciever.Controller;

import Ajoulion_backend.project.Reciever.Service.ReceiverService;
import Ajoulion_backend.project.Table.DTO.ApplyDto;
import Ajoulion_backend.project.Table.DTO.UserSimpleDto;
import Ajoulion_backend.project.Users.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://front-server.com")
@RequestMapping("/")
public class ApplyController {

    private final ReceiverService recvService;
    private final UserService userService;

    @GetMapping("/apply")
    public ResponseEntity<Map<String, Object>> getApplyList(@RequestHeader HttpHeaders header) {
        Long userId = userService.loginCheck(header);
        log.info("in getApplyList");
        List<ApplyDto> applyList = recvService.getApplyListForUser(userId);
        UserSimpleDto user = userService.getUserInfo(userId);
        Map<String, Object> ret = new HashMap<>();
        ret.put("apply", applyList);
        ret.put("user", user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ret);
    }

    @GetMapping("/apply/{applyId}")
    public ResponseEntity<Map<String, Object>> getApplyInfo(@RequestHeader HttpHeaders header, @PathVariable(name="applyId") Long applyId) {
        Long userId = userService.loginCheck(header);

        log.info("in read all");
        ApplyDto apply = recvService.getApplyInfo(applyId);
        UserSimpleDto user = userService.getUserInfo(userId);
        Map<String, Object> ret = new HashMap<>();
        ret.put("apply", apply);
        ret.put("user", user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ret);
    }

    @PostMapping("/apply/post")
    public ResponseEntity applyPost(@RequestHeader HttpHeaders header, @RequestBody ApplyDto applyDto) {
        Long userId = userService.loginCheck(header);
        Date now = new Date();
        applyDto.setUserId(userId);
        applyDto.setStatus(1);
        applyDto.setDate(now.toString());

        log.info(applyDto.toString());
        recvService.save(applyDto);
        return ResponseEntity.created(URI.create("/applylist")).build();
    }

    @DeleteMapping("/apply/{applyId}/delete")
    public ResponseEntity<?> deleteByApplyId(@RequestHeader HttpHeaders header, @PathVariable(name="applyId") Long applyId) throws Exception {
        Long userId = userService.loginCheck(header);

        recvService.deleteByApplyId(applyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Delete Success");
    }

    @PatchMapping("/apply/{applyId}/update")
    public ResponseEntity updateApplyDto(@RequestHeader HttpHeaders header, @PathVariable(name="applyId") Long applyId, @RequestBody ApplyDto applyDto) throws Exception {
        Long userId = userService.loginCheck(header);

        recvService.update(applyId, applyDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Update Success");
    }
}
