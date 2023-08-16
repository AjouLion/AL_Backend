package Ajoulion_backend.project.main.repository;

import Ajoulion_backend.project.Table.Entity.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumRepository extends CrudRepository<Device, Long> {
    long countByStatus(Integer status);
    long countByStatusAndDeviceType(Integer status, Integer deviceType);
}
