package Ajoulion_backend.project.Reciever.Repository;

import Ajoulion_backend.project.Table.Entity.Apply;
import Ajoulion_backend.project.Table.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ReceiverRepository extends JpaRepository<Apply, Long> {
    List<Apply> findNotByUser_IdAndStatusOrderByApplyIdDesc(String id, Integer status); // 최신순으로 정렬
    List<Apply> findByUser_UserIdOrderByApplyIdDesc(Long userId); // 최신순으로 정렬
    Apply findByApplyId(Long applyId);
}
