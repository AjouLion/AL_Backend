package Ajoulion_backend.project.Reciever.Repository;

import Ajoulion_backend.project.Table.Entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReceiverRepository extends JpaRepository<Apply, Long> {
    List<Apply> findAll();
    List<Apply> findByUserId(Long userId);
    Apply findByApplyId(Long applyId);

}
