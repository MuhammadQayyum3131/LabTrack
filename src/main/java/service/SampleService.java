package com.labtrack.service;

import com.labtrack.entity.Sample;
import com.labtrack.entity.TestType;
import com.labtrack.entity.User;
import com.labtrack.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SampleService {

    @Autowired
    private SampleRepository sampleRepository;

    public List<Sample> getAllSamples() {
        return sampleRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<Sample> getSampleById(Long id) {
        return sampleRepository.findById(id);
    }

    public Sample registerSample(String patientName, LocalDate collectionDate,
                                 TestType testType, String notes, User createdBy) {
        Sample sample = new Sample();
        sample.setPatientName(patientName);
        sample.setCollectionDate(collectionDate);
        sample.setTestType(testType);
        sample.setNotes(notes);
        sample.setCreatedBy(createdBy);
        sample.setStatus(Sample.Status.RECEIVED);
        return sampleRepository.save(sample);
    }

    public Sample advanceStatus(Sample sample) {
        if (sample.getStatus() == Sample.Status.RECEIVED) {
            sample.setStatus(Sample.Status.IN_PROGRESS);
        } else if (sample.getStatus() == Sample.Status.IN_PROGRESS) {
            sample.setStatus(Sample.Status.COMPLETED);
        }
        return sampleRepository.save(sample);
    }

    public long countByStatus(Sample.Status status) {
        return sampleRepository.findAll().stream()
                .filter(s -> s.getStatus() == status)
                .count();
    }

    public long countFlagged() {
        return sampleRepository.findAll().stream()
                .filter(s -> s.getResult() != null)
                .filter(s -> s.getResult().getFlag() != com.labtrack.entity.Result.Flag.NORMAL)
                .count();
    }
}