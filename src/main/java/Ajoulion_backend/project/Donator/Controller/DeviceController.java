package Ajoulion_backend.project.Donator.Controller;

import Ajoulion_backend.project.Donator.Service.DeviceService;
import Ajoulion_backend.project.Reciever.Service.ReceiverService;
import Ajoulion_backend.project.Table.DTO.ApplyDto;
import Ajoulion_backend.project.Table.DTO.DeviceDto;
import Ajoulion_backend.project.Table.DTO.UsersDto;
import Ajoulion_backend.project.Users.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class DeviceController {
    private final DeviceService deviceService;
    private final ReceiverService recvService;

    @GetMapping("/donatelist")
    public ResponseEntity<List<DeviceDto>> getDonateList(HttpServletRequest request){
        ResponseEntity re = recvService.logincheck(request);
        if (re != null) return re;

        log.info("in deviceList");
        List<DeviceDto> deviceDtoList = deviceService.getDonateList(recvService.loginUser.getUserId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deviceDtoList);
    }

    @GetMapping("/device")
    public ResponseEntity<List<DeviceDto>> getDeviceList(HttpServletRequest request){
        ResponseEntity re = recvService.logincheck(request);
        if (re != null) return re;

        log.info("in deviceList");
        List<DeviceDto> deviceDtoList = deviceService.getDeviceList(recvService.loginUser.getUserId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deviceDtoList);
    }

    @GetMapping("/receivers/{deviceId}")
    public ResponseEntity<DeviceDto> getDeviceInfo(@PathVariable(name="deviceId") Long deviceId, HttpServletRequest request){
        ResponseEntity re = recvService.logincheck(request);
        if (re != null) return re;

        DeviceDto deviceDto = deviceService.getDeviceInfo(recvService.loginUser.getUserId(), deviceId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deviceDto);
    }

    @PostMapping("/device/post")
    public ResponseEntity devicePost(@RequestBody DeviceDto deviceDto, HttpServletRequest request) {
        ResponseEntity re = recvService.logincheck(request);
        if (re != null) return re;

        deviceDto.setUserId(recvService.loginUser.getUserId());

        log.info(deviceDto.toString());
        deviceService.save(deviceDto);
        return ResponseEntity.created(URI.create("/device")).build();
    }

    @PatchMapping("/device/{deviceId}/update")
    public ResponseEntity updateDeviceDto(@PathVariable(name="deviceId") Long deviceId, @RequestBody DeviceDto deviceDto, HttpServletRequest request){
        ResponseEntity re = recvService.logincheck(request);
        if (re != null) return re;

        deviceService.update(deviceId, deviceDto);
        return ResponseEntity.created(URI.create("/device")).build();
    }

    @DeleteMapping("/device/{device_id}/delete")
    public ResponseEntity<?> deleteByDeviceId(@PathVariable(name="deviceId") Long deviceId, HttpServletRequest request){
        ResponseEntity re = recvService.logincheck(request);
        if (re != null) return re;
        deviceService.deleteByDeviceId(deviceId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Delete Success");
    }


}
