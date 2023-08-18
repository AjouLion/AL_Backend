package Ajoulion_backend.project.Donator.Repository;

import Ajoulion_backend.project.Table.Entity.Apply;
import Ajoulion_backend.project.Table.Entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByUser_UserIdAndStatusOrderByDeviceIdDesc(Long userId, Integer status);
    List<Device> findByUser_UserIdAndStatusAndDeviceTypeOrderByDeviceIdDesc(Long userId, Integer status, String deviceType);
    List<Device> findByUser_UserIdAndStatusNotOrderByDeviceIdDesc(Long userId, Integer status);
    Device findByUser_UserIdAndDeviceId(Long userId, Long DeviceId);
    Device findByDeviceId(Long deviceId);
}
