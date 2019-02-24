package mapfood.controller;

import lombok.RequiredArgsConstructor;
import mapfood.model.Report;
import mapfood.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@BasePathAwareController
@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping
    public List<Report> getReport(@RequestParam String date, @RequestParam Integer idRestaurant) {
        return reportService.getReport(LocalDate.parse(date), idRestaurant);
    }

}
