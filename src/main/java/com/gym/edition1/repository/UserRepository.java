package com.gym.edition1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gym.edition1.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);

  @Query("""
      SELECT u FROM User u
      JOIN Gym g
      ON u.gymId = g.gymId
      WHERE (:username IS NULL OR LOWER(u.username) LIKE CONCAT('%', LOWER(:username), '%'))
      AND   (:type IS NULL OR u.type = :type)
      AND   (:gymName IS NULL OR LOWER(g.gymName) LIKE CONCAT('%', LOWER(:gymName), '%'))
      AND   (:gymId IS NULL OR u.gymId = :gymId)
      AND   (:createdBy IS NULL OR u.createdBy = :createdBy)
      AND   (:modifiedBy IS NULL OR u.modifiedBy = :modifiedBy)
      """)
  List<User> getUsersByFilters(
    @Param("username")  String username,
    @Param("type")  String type,
    @Param("gymName")  String gymName,
    @Param("gymId")  Long gymId,
    @Param("createdBy")  Long createdBy,
    @Param("modifiedBy")  Long modifiedBy);
}
