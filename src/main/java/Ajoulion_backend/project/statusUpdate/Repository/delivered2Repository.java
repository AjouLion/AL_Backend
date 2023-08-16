package Ajoulion_backend.project.statusUpdate.Repository;

import Ajoulion_backend.project.Table.Entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface delivered2Repository extends JpaRepository<Device, Long> {
    Device findByDeviceId(Long deviceId);
}
