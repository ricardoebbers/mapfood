package mapfood.service;

import mapfood.model.Report;
import mapfood.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<Report> getReport(LocalDate date, Integer idRestaurant);

    void setDistanceTimeForReport(String timeRoutToRestaurant, String distanceToRestaurant,
                                  String timeRouteToClient, String distanceToClient, Restaurant restaurant);

}
