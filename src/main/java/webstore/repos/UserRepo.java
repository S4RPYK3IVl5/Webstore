package webstore.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import webstore.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
}
