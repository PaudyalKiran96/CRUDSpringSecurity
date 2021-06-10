package com.kiran.crud.users;

import org.springframework.data.jpa.repository.JpaRepository;

//Spring Data JPA Repository
//JpaRepository: Repo for CRUD actions.
public interface UserRepository extends JpaRepository<User, Long> {

}
