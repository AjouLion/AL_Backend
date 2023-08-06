package Ajoulion_backend.project.Users.Repository;

import Ajoulion_backend.project.Table.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findById(String Id);
    Users findByUserId(Long userId);
}