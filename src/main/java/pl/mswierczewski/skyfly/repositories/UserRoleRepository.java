package pl.mswierczewski.skyfly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mswierczewski.skyfly.models.UserRole;
import pl.mswierczewski.skyfly.models.enums.UserType;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findUserRoleByRole(UserType role);
}
