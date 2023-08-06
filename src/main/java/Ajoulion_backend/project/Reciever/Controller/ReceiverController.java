package Ajoulion_backend.project.Reciever.Controller;

import Ajoulion_backend.project.Reciever.Service.ReceiverService;
import Ajoulion_backend.project.Table.DTO.ApplyDto;
import Ajoulion_backend.project.Table.DTO.DeviceDto;
import Ajoulion_backend.project.Table.DTO.UsersDto;
import Ajoulion_backend.project.Table.Entity.Device;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ReceiverController {

    private final ReceiverService recvService;

    @GetMapping("/receivers")
    public ResponseEntity<List<ApplyDto>> getApplyList(HttpServletRequest request){
        ResponseEntity re = recvService.logincheck(request);
        if (re != null) return re;

        log.info("in getApplyList");
        List<ApplyDto> applyDtoList = recvService.getApplyList();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(applyDtoList);
    }

    @GetMapping("/receivers/{applyId}")
    public ResponseEntity<Map<String, Object>> Apply(@PathVariable(name="applyId") Long applyId, HttpServletRequest request){
        ResponseEntity re = recvService.logincheck(request);
        if (re != null) return re;

        log.info("in read all");
        ApplyDto applyDto = recvService.getApplyInfo(applyId);
        UsersDto userDto = recvService.getUserInfo(applyDto.getUserId());
        Map<String, Object> ret = new HashMap<>();
        ret.put("apply", applyDto);
        ret.put("users", userDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ret);
    }
}
