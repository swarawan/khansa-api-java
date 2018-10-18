package com.swarawan.khansapos.repository;

import com.swarawan.khansapos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findBySecureId(String secureId);

    User findByEmail(String email);

    @Query(value = "SELECT u FROM USER u WHERE u.email = :email AND u.password = :password", nativeQuery = true)
    User findByEmailPassword(@Param("email") String email,
                             @Param("password") String password);

}
