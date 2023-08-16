package Ajoulion_backend.project.statusUpdate.Repository;

import Ajoulion_backend.project.Table.DTO.DeviceDto;
import Ajoulion_backend.project.Table.Entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface deliveredRepository extends JpaRepository<Apply, Long> {
    Apply findByApplyId(Long applyId);
}
