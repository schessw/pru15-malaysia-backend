package com.malaysiapru15.result.state;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    @Query(value = "SELECT * FROM state s WHERE LOWER(s.state_name) LIKE '%' + LOWER(:name) + '%'", nativeQuery = true)
    List<State> findByNameContaining(@Param("name") String state_name);
}