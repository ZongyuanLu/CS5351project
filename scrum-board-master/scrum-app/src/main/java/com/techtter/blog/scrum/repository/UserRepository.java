package com.techtter.blog.scrum.repository;

import com.techtter.blog.scrum.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User as u where u.phone=?1")
    Optional<User> findUserIdByPhone(String phone);

    void deleteByPhone(String phone);
}
