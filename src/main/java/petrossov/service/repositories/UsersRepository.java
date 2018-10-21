package petrossov.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petrossov.service.models.User;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User,Integer> {

    Optional<User> findOneByLogin(String login );
}
