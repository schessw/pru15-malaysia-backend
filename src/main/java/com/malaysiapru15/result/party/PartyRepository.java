package com.malaysiapru15.result.party;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    @Query(value = "SELECT * FROM party p WHERE p.party_name = :name", nativeQuery = true)
    List<Party> findByNameContaining(@Param("name") String party_name);
}
