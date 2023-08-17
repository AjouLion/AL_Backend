package Ajoulion_backend.project.main.repository;

import Ajoulion_backend.project.Table.Entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumRepository extends JpaRepository<Device, Long> {
    long countByStatus(Integer status);
    long countByStatusAndDeviceType(Integer status, String deviceType);
}
