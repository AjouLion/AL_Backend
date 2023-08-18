package Ajoulion_backend.project.Donator.Service;

import Ajoulion_backend.project.Donator.Repository.DeviceRepository;
import Ajoulion_backend.project.Error.CustomException;
import Ajoulion_backend.project.ImageUpload.ImageUpload;
import Ajoulion_backend.project.Reciever.Repository.ReceiverRepository;
import Ajoulion_backend.project.Table.DTO.ApplyDto;
import Ajoulion_backend.project.Table.DTO.DeviceDto;
import Ajoulion_backend.project.Table.DTO.UserSimpleDto;
import Ajoulion_backend.project.Table.Entity.Apply;
import Ajoulion_backend.project.Table.Entity.Device;
import Ajoulion_backend.project.Table.Entity.Users;
import Ajoulion_backend.project.Users.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Ajoulion_backend.project.Error.ErrorCode.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final ReceiverRepository receiverRepository;
    private final UserRepository userRepository;

    public List<Map<String, Object>> getDonateList(Long userId) {

        List<Device> list = deviceRepository.findByUser_UserIdAndStatusNotOrderByDeviceIdDesc(userId, 1);
        List<Map<String, Object>> map = new ArrayList<>();

        for (Device device : list) {
            Map<String, Object> ret = new HashMap<>();
            Apply apply = receiverRepository.findByApplyId(device.getApply().getApplyId());
            Users user = userRepository.findByUserId(apply.getUser().getUserId());
            ret.put("device", new DeviceDto(device));
            ret.put("apply", new ApplyDto(apply));
            ret.put("user", new UserSimpleDto(user));
            map.add(ret);
        }
        return map;
    }

    public List<DeviceDto> getDeviceList(Long userId, String deviceType) {
        List<Device> list = null;
        if (deviceType == null) {
            list = deviceRepository.findByUser_UserIdAndStatusOrderByDeviceIdDesc(userId, 1);
        } else {
            log.info(deviceType);
            list = deviceRepository.findByUser_UserIdAndStatusAndDeviceTypeOrderByDeviceIdDesc(userId, 1, deviceType);
        }
        List<DeviceDto> deviceDtoList = new ArrayList<>();
        for (Device device : list) {
            deviceDtoList.add(new DeviceDto(device));
        }
        return deviceDtoList;
    }

    public DeviceDto getDeviceInfo(Long userId, Long deviceId) {
        Device device = deviceRepository.findByUser_UserIdAndDeviceId(userId, deviceId);
        if (device == null) {
            throw new CustomException(ERR_DEVICE_NOT_EXIST);
        }
        return (new DeviceDto(device));
    }

    public Device getDevice(Long deviceId) throws CustomException {
        Device device = deviceRepository.findByDeviceId(deviceId);
        if (device == null) {
            throw new CustomException(ERR_DEVICE_NOT_EXIST);
        }
        return device;
    }

    @Transactional
    public void uploadDeviceImage(Long deviceId, MultipartFile deviceImage) {
        if (deviceImage == null) return;
        Device device = getDevice(deviceId);
        String filename = ImageUpload.uploadDeviceImage(device.getDeviceId(), deviceImage);
        if (filename == null) {
            throw new CustomException(ERR_IMAGE_DEVICE);
        }
        if (filename != device.getImage()) ImageUpload.deleteDeviceImage(device);
        device.setImage(filename);
    }

    public Long save(DeviceDto dto) {
        Device device = new Device(dto);
        deviceRepository.save(device);
        return device.getDeviceId();
    }

    @Transactional
    public void update(Long deviceId, DeviceDto deviceDto) {
        Device device = getDevice(deviceId);
        device.setDeviceType(deviceDto.getDeviceType());
        device.setModel(deviceDto.getModel());
        device.setConditions(deviceDto.getConditions());
        device.setUsedDate(deviceDto.getUsedDate());
    }

    public void deleteByDeviceId(Long deviceId){
        ImageUpload.deleteDeviceImage(getDevice(deviceId));
        deviceRepository.deleteById(deviceId);
    }

}
