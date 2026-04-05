package com.labtrack.controller;

import com.labtrack.entity.Sample;
import com.labtrack.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private SampleService sampleService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("samples", sampleService.getAllSamples());
        model.addAttribute("totalCount", sampleService.getAllSamples().size());
        model.addAttribute("pendingCount", sampleService.countByStatus(Sample.Status.RECEIVED)
                + sampleService.countByStatus(Sample.Status.IN_PROGRESS));
        model.addAttribute("completedCount",
                sampleService.countByStatus(Sample.Status.COMPLETED));
        model.addAttribute("flaggedCount", sampleService.countFlagged());
        return "dashboard";
    }
}