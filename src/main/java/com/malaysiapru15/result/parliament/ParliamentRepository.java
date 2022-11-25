package com.malaysiapru15.result.parliament;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParliamentRepository extends JpaRepository<Parliament, Long> {

}
