package com.covid.covid19.controller;

import com.covid.covid19.exception.CoronaVirusException;
import com.covid.covid19.models.LocationStats;
import com.covid.covid19.service.CoronaVirusDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.exceptions.TemplateInputException;

import java.util.List;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        int totalNewCases = allStats.stream()
                .mapToInt(LocationStats::getDiffFromPrevDay)
                .sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }

    @GetMapping("/search/{country}")
    public String searchCountry(@PathVariable String country, Model model) throws CoronaVirusException {

        LocationStats state = coronaVirusDataService.searchState(country);
        if (state == null) {
            throw new CoronaVirusException("State under the name: " + country + " not found");
        }
        model.addAttribute("st", state);
        return "search";
    }

    @ExceptionHandler(CoronaVirusException.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "excep";
    }

}
