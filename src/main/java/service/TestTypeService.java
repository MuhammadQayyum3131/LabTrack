package com.labtrack.service;

import com.labtrack.entity.TestType;
import com.labtrack.repository.TestTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TestTypeService {

    @Autowired
    private TestTypeRepository testTypeRepository;

    public List<TestType> getAllTestTypes() {
        return testTypeRepository.findAll();
    }

    public Optional<TestType> getTestTypeById(Long id) {
        return testTypeRepository.findById(id);
    }
}