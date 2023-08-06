package Ajoulion_backend.project.Donator.Repository;

import Ajoulion_backend.project.Table.Entity.Apply;
import Ajoulion_backend.project.Table.Entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByUserId(Long userId);
    List<Device> findByUserIdAndStatusNot(Long userId, Integer key);
    Device findByUserIdAndDeviceId(Long userId, Long DeviceId);
    Device findByDeviceId(Long deviceId);
}
