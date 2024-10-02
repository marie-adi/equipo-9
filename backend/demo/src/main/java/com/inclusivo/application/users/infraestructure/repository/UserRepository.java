package com.inclusivo.application.users.infraestructure.repository;

import com.inclusivo.application.users.domain.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
}
