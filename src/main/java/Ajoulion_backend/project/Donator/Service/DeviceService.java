package Ajoulion_backend.project.Donator.Service;

import Ajoulion_backend.project.Donator.Repository.DeviceRepository;
import Ajoulion_backend.project.Reciever.Repository.ReceiverRepository;
import Ajoulion_backend.project.Table.DTO.ApplyDto;
import Ajoulion_backend.project.Table.DTO.DeviceDto;
import Ajoulion_backend.project.Table.Entity.Apply;
import Ajoulion_backend.project.Table.Entity.Device;
import Ajoulion_backend.project.Users.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public List<DeviceDto> getDonateList(Long userId) {

        List<Device> applyList = deviceRepository.findByUserIdAndStatusNot(userId, 1);
        List<DeviceDto> deviceDtoList = new ArrayList<>();
        for (Device device : applyList) {
            deviceDtoList.add(new DeviceDto(device));
        }
        return deviceDtoList;
    }

    public List<DeviceDto> getDeviceList(Long userId) {

        List<Device> applyList = deviceRepository.findByUserId(userId);
        List<DeviceDto> deviceDtoList = new ArrayList<>();
        for (Device device : applyList) {
            deviceDtoList.add(new DeviceDto(device));
        }
        return deviceDtoList;
    }

    public DeviceDto getDeviceInfo(Long userId, Long deviceId) {
        Device device = deviceRepository.findByUserIdAndDeviceId(userId, deviceId);
        return (new DeviceDto(device));
    }

    public void save(DeviceDto dto) {
        Device entity = new Device(dto);
        deviceRepository.save(entity);
    }

    public void update(Long deviceId, DeviceDto deviceDto) {
        Device entity = deviceRepository.findByDeviceId(deviceId);
        entity.setDeviceType(deviceDto.getDeviceType());
        entity.setDate(deviceDto.getDate());
        entity.setImage(deviceDto.getImage());
        entity.setModel(deviceDto.getModel());
        entity.setConditions(deviceDto.getConditions());
        entity.setUsedDate(deviceDto.getUsedDate());
        deviceRepository.save(entity);
    }

    public void deleteByDeviceId(Long deviceId){
        deviceRepository.deleteById(deviceId);
    }

}
