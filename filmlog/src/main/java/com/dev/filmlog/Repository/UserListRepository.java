package com.dev.filmlog.Repository;

import com.dev.filmlog.Model.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserListRepository extends JpaRepository<UserList, Integer> {
    UserList findUserListById(Integer id);
}
