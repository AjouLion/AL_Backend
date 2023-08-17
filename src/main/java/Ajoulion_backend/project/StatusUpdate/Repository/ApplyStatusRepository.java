package Ajoulion_backend.project.StatusUpdate.Repository;

import Ajoulion_backend.project.Table.Entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyStatusRepository extends JpaRepository<Apply, Long> {
    Apply findByApplyId(Long applyId);
}
