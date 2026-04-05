package com.labtrack.repository;

import com.labtrack.entity.Result;
import com.labtrack.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    Optional<Result> findBySample(Sample sample);
}