package Ajoulion_backend.project.Donator.Controller;

import Ajoulion_backend.project.Donator.Service.DeviceService;
import Ajoulion_backend.project.Table.DTO.DeviceDto;
import Ajoulion_backend.project.Table.Entity.Device;
import Ajoulion_backend.project.Users.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class DeviceController {
    private final DeviceService deviceService;
    private final UserService userService;

    @GetMapping("/donatelist")
    public ResponseEntity<List<DeviceDto>> getDonateList(@RequestHeader HttpHeaders header) {
        Long userId = userService.loginCheck(header);

        log.info("in deviceList");
        List<DeviceDto> deviceDtoList = deviceService.getDonateList(userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deviceDtoList);
    }

    @GetMapping("/device")
    public ResponseEntity<List<DeviceDto>> getDeviceList(@RequestHeader HttpHeaders header) {
        Long userId = userService.loginCheck(header);

        log.info("in deviceList");
        List<DeviceDto> deviceDtoList = deviceService.getDeviceList(userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deviceDtoList);
    }

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<DeviceDto> getDeviceInfo(@RequestHeader HttpHeaders header, @PathVariable(name="deviceId") Long deviceId) throws Exception {
        Long userId = userService.loginCheck(header);

        DeviceDto deviceDto = deviceService.getDeviceInfo(userId, deviceId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deviceDto);
    }

    @PostMapping("/device/post")
    public ResponseEntity devicePost(@RequestHeader HttpHeaders header, @RequestBody DeviceDto deviceDto) throws Exception {
        Long userId = userService.loginCheck(header);
        Date now = new Date();
        deviceDto.setUserId(userId);
        deviceDto.setStatus(1);
        deviceDto.setDate(now.toString());
        log.info(deviceDto.toString());
        deviceService.save(deviceDto);
        return ResponseEntity.created(URI.create("/device")).build();
    }

    @PatchMapping("/device/{deviceId}/update")
    public ResponseEntity updateDeviceDto(@RequestHeader HttpHeaders header, @PathVariable(name="deviceId") Long deviceId, @RequestBody DeviceDto deviceDto) throws Exception {
        Long userId = userService.loginCheck(header);

        deviceService.update(deviceId, deviceDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Update Success");
    }

    @DeleteMapping("/device/{deviceId}/delete")
    public ResponseEntity<?> deleteByDeviceId(@RequestHeader HttpHeaders header, @PathVariable(name="deviceId") Long deviceId) throws Exception {
        Long userId = userService.loginCheck(header);

        deviceService.deleteByDeviceId(deviceId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Delete Success");
    }


}
