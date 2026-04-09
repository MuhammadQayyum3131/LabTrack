package com.labtrack;

import com.labtrack.entity.TestType;
import com.labtrack.repository.TestTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TestTypeRepository testTypeRepository;

    @Override
    public void run(String... args) {
        if (testTypeRepository.count() == 0) {
            addTestType("Glucose",     "mg/dL", 70.0,  140.0);
            addTestType("CBC",         "g/dL",  12.0,  17.5);
            addTestType("Lipid Panel", "mg/dL", 0.0,   200.0);
            addTestType("Thyroid TSH", "mIU/L", 0.4,   4.0);
            System.out.println("Test types seeded successfully.");
        }
    }

    private void addTestType(String name, String unit,
                             double min, double max) {
        TestType tt = new TestType();
        tt.setName(name);
        tt.setUnit(unit);
        tt.setReferenceMin(BigDecimal.valueOf(min));
        tt.setReferenceMax(BigDecimal.valueOf(max));
        testTypeRepository.save(tt);
    }
}