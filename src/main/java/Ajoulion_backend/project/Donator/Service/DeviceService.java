package Ajoulion_backend.project.Donator.Service;

import Ajoulion_backend.project.Donator.Repository.DeviceRepository;
import Ajoulion_backend.project.Table.DTO.DeviceDto;
import Ajoulion_backend.project.Table.Entity.Device;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public List<DeviceDto> getDonateList(Long userId) {

        List<Device> list = deviceRepository.findByUser_UserIdAndStatusNotOrderByDeviceIdDesc(userId, 1);
        List<DeviceDto> deviceDtoList = new ArrayList<>();
        for (Device device : list) {
            deviceDtoList.add(new DeviceDto(device));
        }
        return deviceDtoList;
    }

    public List<DeviceDto> getDeviceList(Long userId) {

        List<Device> list = deviceRepository.findByUser_UserIdOrderByDeviceIdDesc(userId);
        List<DeviceDto> deviceDtoList = new ArrayList<>();
        for (Device device : list) {
            deviceDtoList.add(new DeviceDto(device));
        }
        return deviceDtoList;
    }

    public DeviceDto getDeviceInfo(Long userId, Long deviceId) {
        Device device = deviceRepository.findByUser_UserIdAndDeviceId(userId, deviceId);
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
