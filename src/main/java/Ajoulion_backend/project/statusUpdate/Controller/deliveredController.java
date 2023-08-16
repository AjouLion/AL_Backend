package Ajoulion_backend.project.statusUpdate.Controller;

import Ajoulion_backend.project.Table.DTO.ApplyDto;
import Ajoulion_backend.project.Users.Service.UserService;
import Ajoulion_backend.project.statusUpdate.Service.deliveredService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class deliveredController {

    @Autowired
    private final deliveredService deliveredservice;
    @Autowired
    private final UserService userService;

    @PatchMapping("/apply/complete/{applyId}")
    public ResponseEntity<?> updateStatus4(@RequestHeader HttpHeaders header, @PathVariable(name="applyId") Long applyId) throws Exception {
        Long userId = userService.loginCheck(header);

        deliveredservice.edit(applyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Update Success");
    }

    @PatchMapping("/donatelist/{deviceId}")
    public ResponseEntity<?> updateStatus3(@RequestHeader HttpHeaders header, @PathVariable(name="deviceId") Long deviceId, @RequestBody ApplyDto applyDto) throws Exception {
        Long userId = userService.loginCheck(header);

        deliveredservice.edit2(deviceId, applyDto.getDeliverNum(), applyDto.getDeliverCorp());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Update Success");
    }

    @PatchMapping("/receiver/{applyId}/{deviceId}")
    public ResponseEntity<?> updateStatus2(@RequestHeader HttpHeaders header, @PathVariable(name="applyId") Long applyId, @PathVariable(name="deviceId") Long deviceId) throws Exception {
        Long userId = userService.loginCheck(header);

        deliveredservice.edit3(applyId, deviceId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Update Success");
    }

}
