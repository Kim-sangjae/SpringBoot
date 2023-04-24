package org.example.repositories;

import org.example.entities.QUsers;
import org.example.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UsersRepository extends JpaRepository<Users,Long>, QuerydslPredicateExecutor {


    Users findByUserId(String userId);


    /**
     * 가입된 회원여부 체크
     * */
    default boolean userExists(String userId){
        QUsers users = QUsers.users;
        boolean exists = exists(users.userId.eq(userId));

        return exists;
    }





}
