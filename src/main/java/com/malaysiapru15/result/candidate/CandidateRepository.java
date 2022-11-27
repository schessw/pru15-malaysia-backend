package com.malaysiapru15.result.candidate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    @Query(value = "SELECT * FROM candidate c WHERE LOWER(c.candidate_full_name) LIKE '%' + LOWER(:full_name) + '%'", nativeQuery = true)
    List<Candidate> findByFullNameContaining(@Param("full_name") String full_name);

    @Query(value = "SELECT * FROM candidate c WHERE LOWER(c.candidate_display_name) LIKE '%' + LOWER(:display_name) + '%'", nativeQuery = true)
    List<Candidate> findByDisplayNameContaining(@Param("display_name") String display_name);

    @Query(value = "SELECT * FROM candidate c WHERE c.candidate_status = :status", nativeQuery = true)
    List<Candidate> findByStatus(@Param("status") String status);

    @Query(value = "SELECT * FROM candidate WHERE party_id = :party_id",
            countName = "SELECT count(*) FROM candidate WHERE party_id = :party_id",
            nativeQuery = true)
    Page<Candidate> findByPartyId(@Param("party_id") long party_id, Pageable pageable);

    @Query(value = "SELECT * FROM candidate c WHERE c.parliament_id = :id", nativeQuery = true)
    List<Candidate> findByParliamentId(@Param("id") long parliament_id);
}
