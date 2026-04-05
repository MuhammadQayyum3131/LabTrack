package com.labtrack.repository;

import com.labtrack.entity.Sample;
import com.labtrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {
    List<Sample> findAllByOrderByCreatedAtDesc();
    List<Sample> findByCreatedBy(User user);
}