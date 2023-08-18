package Ajoulion_backend.project.Donator.Controller;

import Ajoulion_backend.project.Donator.Service.DeviceService;
import Ajoulion_backend.project.ImageUpload.ImageUpload;
import Ajoulion_backend.project.Table.DTO.DeviceDto;
import Ajoulion_backend.project.Table.Entity.Device;
import Ajoulion_backend.project.Users.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class DeviceController {
    private final DeviceService deviceService;
    private final UserService userService;

    @GetMapping("/donatelist")
    public ResponseEntity<List<Map<String, Object>>> getDonateList(@RequestHeader HttpHeaders header) {
        Long userId = userService.loginCheck(header);

        log.info("in deviceList");
        List<Map<String, Object>> map = deviceService.getDonateList(userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
    }

    @GetMapping("/device")
    public ResponseEntity<List<DeviceDto>> getDeviceList(@RequestHeader HttpHeaders header, @RequestParam(value = "deviceType", required=false) String deviceType) {
        Long userId = userService.loginCheck(header);
        log.info(deviceType);
        log.info("in deviceList");
        List<DeviceDto> deviceDtoList = deviceService.getDeviceList(userId, deviceType);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deviceDtoList);
    }

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<DeviceDto> getDeviceInfo(@RequestHeader HttpHeaders header, @PathVariable(name="deviceId") Long deviceId) throws Exception {
        Long userId = userService.loginCheck(header);

        DeviceDto deviceDto = deviceService.getDeviceInfo(userId, deviceId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deviceDto);
    }

    @PostMapping("/device/post")
    public ResponseEntity devicePost(@RequestHeader HttpHeaders header,
                                     @RequestPart(name="device") DeviceDto deviceDto,
                                     @RequestPart(name="image") MultipartFile deviceImage) throws Exception {
        Long userId = userService.loginCheck(header);
        deviceDto.setUserId(userId);
        deviceDto.setDate(new Date().toString());
        deviceDto.setImage(null);
        deviceDto.setStatus(1);
        log.info(deviceDto.toString());
        Long deviceId = deviceService.save(deviceDto);
        deviceService.uploadDeviceImage(deviceId, deviceImage);
        return ResponseEntity.created(URI.create("/device")).build();
    }

    @PatchMapping("/device/{deviceId}/update/image")
    public ResponseEntity<?> uploadDeviceImage(@RequestHeader HttpHeaders header,
                                               @PathVariable(name="deviceId") Long deviceId,
                                               @RequestPart(name="image") MultipartFile deviceImage) {
        Long userId = userService.loginCheck(header);

        deviceService.uploadDeviceImage(deviceId, deviceImage);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
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
