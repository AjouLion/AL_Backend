package Ajoulion_backend.project.StatusUpdate.Controller;

import Ajoulion_backend.project.Table.DTO.ApplyDto;
import Ajoulion_backend.project.Users.Service.UserService;
import Ajoulion_backend.project.StatusUpdate.Service.DeliveredService;
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
@CrossOrigin(origins = "http://front-server.com")
@RequestMapping("/")
public class DeliveredController {

    @Autowired
    private final DeliveredService deliveredservice;
    private final UserService userService;

    @PatchMapping("/apply/complete/{applyId}")
    public ResponseEntity<?> deliveryComplete(@RequestHeader HttpHeaders header, @PathVariable(name="applyId") Long applyId) throws Exception {
        Long userId = userService.loginCheck(header);

        deliveredservice.deliveryComplete(applyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Update Success");
    }


    @PatchMapping("/donatelist/{deviceId}")
    public ResponseEntity<?> updateDeliveryInfo(@RequestHeader HttpHeaders header, @PathVariable(name="deviceId") Long deviceId, @RequestBody ApplyDto applyDto) throws Exception {
        Long userId = userService.loginCheck(header);

        deliveredservice.updateDeliveryInfo(deviceId, applyDto.getDeliverNum(), applyDto.getDeliverCorp());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Update Success");
    }


    @PatchMapping("/receiver/{applyId}/{deviceId}")
    public ResponseEntity<?> completeMatching(@RequestHeader HttpHeaders header, @PathVariable(name="applyId") Long applyId, @PathVariable(name="deviceId") Long deviceId) throws Exception {
        Long userId = userService.loginCheck(header);

        deliveredservice.completeMatching(applyId, deviceId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Update Success");
    }

}
