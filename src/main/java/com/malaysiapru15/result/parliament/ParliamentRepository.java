package com.malaysiapru15.result.parliament;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParliamentRepository extends JpaRepository<Parliament, Long> {
    @Query(value = "SELECT * FROM parliament p WHERE LOWER(p.parliament_name) LIKE '%' + LOWER(:name) + '%'", nativeQuery = true)
    List<Parliament> findByNameContaining(@Param("name") String parliament_name);

    @Query(value = "SELECT * FROM parliament p WHERE LOWER(p.parliament_code) LIKE '%' + LOWER(:code) + '%'", nativeQuery = true)
    List<Parliament> findByCodeContaining(@Param("code") String parliament_code);

    @Query(value = "SELECT * FROM parliament p WHERE p.state_id = :id", nativeQuery = true)
    List<Parliament> findByStateId(@Param("id") long state_id);
}
