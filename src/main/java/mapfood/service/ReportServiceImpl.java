package mapfood.service;

import mapfood.model.Report;
import mapfood.model.Restaurant;
import mapfood.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    OrderService orderService;

    @Autowired
    ReportRepository reportRepository;

    @Override
    public List<Report> getReport(LocalDate date, Integer idRestaurant) {
        return reportRepository.findAllByDateAndRestaurant__id(date, idRestaurant);
    }

    public void setDistanceTimeForReport(String timeRoutToRestaurant, String distanceToRestaurant,
                                         String timeRouteToClient, String distanceToClient, Restaurant restaurant) {
        Report report = new Report();
        report.setDurationToClient(timeRouteToClient);
        report.setDistanceToClient(distanceToClient);
        report.setDurationToRestaurant(timeRoutToRestaurant);
        report.setDistanceToRestaurant(distanceToRestaurant);
        report.setRestaurant(restaurant);
        report.setDate(LocalDate.now());

        reportRepository.save(report);
    }

}

