package com.sparta.n5.repository;

import com.sparta.n5.entity.N5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface N5Repository extends JpaRepository<N5,Long> {
}
