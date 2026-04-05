package com.labtrack.controller;

import com.labtrack.entity.Sample;
import com.labtrack.entity.TestType;
import com.labtrack.entity.User;
import com.labtrack.service.ResultService;
import com.labtrack.service.SampleService;
import com.labtrack.service.TestTypeService;
import com.labtrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/samples")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private TestTypeService testTypeService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResultService resultService;

    @GetMapping("/new")
    public String newSampleForm(Model model) {
        model.addAttribute("testTypes", testTypeService.getAllTestTypes());
        return "new-sample";
    }

    @PostMapping("/new")
    public String registerSample(@RequestParam String patientName,
                                 @RequestParam Long testTypeId,
                                 @RequestParam String collectionDate,
                                 @RequestParam(required = false) String notes,
                                 Principal principal) {
        User user = userService.findByUsername(principal.getName());
        TestType testType = testTypeService.getTestTypeById(testTypeId).orElseThrow();
        sampleService.registerSample(patientName, LocalDate.parse(collectionDate),
                testType, notes, user);
        return "redirect:/dashboard";
    }

    @GetMapping("/{id}")
    public String sampleDetail(@PathVariable Long id, Model model) {
        Optional<Sample> sample = sampleService.getSampleById(id);
        if (sample.isEmpty()) {
            return "redirect:/dashboard";
        }
        model.addAttribute("sample", sample.get());
        return "sample-detail";
    }

    @PostMapping("/{id}/status")
    public String advanceStatus(@PathVariable Long id) {
        Optional<Sample> sample = sampleService.getSampleById(id);
        sample.ifPresent(sampleService::advanceStatus);
        return "redirect:/samples/" + id;
    }

    @PostMapping("/{id}/result")
    public String submitResult(@PathVariable Long id,
                               @RequestParam BigDecimal value) {
        Optional<Sample> sample = sampleService.getSampleById(id);
        if (sample.isPresent() &&
                sample.get().getStatus() == Sample.Status.COMPLETED &&
                sample.get().getResult() == null) {
            resultService.submitResult(sample.get(), value);
        }
        return "redirect:/samples/" + id;
    }
}