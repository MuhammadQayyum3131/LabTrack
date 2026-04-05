package com.labtrack.service;

import com.labtrack.entity.Result;
import com.labtrack.entity.Sample;
import com.labtrack.entity.TestType;
import com.labtrack.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public Result submitResult(Sample sample, BigDecimal value) {
        TestType testType = sample.getTestType();
        Result.Flag flag;

        if (value.compareTo(testType.getReferenceMax()) > 0) {
            flag = Result.Flag.HIGH;
        } else if (value.compareTo(testType.getReferenceMin()) < 0) {
            flag = Result.Flag.LOW;
        } else {
            flag = Result.Flag.NORMAL;
        }

        Result result = new Result();
        result.setSample(sample);
        result.setValue(value);
        result.setFlag(flag);
        return resultRepository.save(result);
    }
}