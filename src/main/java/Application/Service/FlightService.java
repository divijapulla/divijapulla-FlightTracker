package Application.Service;

import Application.Model.Flight;
import Application.DAO.FlightDAO;

import java.util.List;

/**
 * The purpose of a Service class is to contain "business logic" that sits between the web layer (controller) and
 * persistence layer (DAO). That means that the Service class performs tasks that aren't done through the web or
 * SQL: programming tasks like checking that the input is valid, conducting additional security checks, or saving the
 * actions undertaken by the API to a logging file.
 *
 * It's perfectly normal to have Service methods that only contain a single line that calls a DAO method. An
 * application that follows best practices will often have unnecessary code, but this makes the code more
 * readable and maintainable in the long run!
 */
public class FlightService {
    FlightDAO flightDAO;

    public FlightService() {
        flightDAO = new FlightDAO();
    }

    public FlightService(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    public Flight addFlight(Flight flight) {
        return flightDAO.insertFlight(flight);
    }

    public Flight updateFlight(int flight_id, Flight flight) {
        Flight existingFlight = flightDAO.getFlightById(flight_id);
        if (existingFlight == null) {
            return null;
        }
        flightDAO.updateFlight(flight_id, flight);
        return new Flight(flight_id, flight.getDeparture_city(), flight.getArrival_city());
    }

    public List<Flight> getAllFlights() {
        return flightDAO.getAllFlights();
    }

    public List<Flight> getAllFlightsFromCityToCity(String departure_city, String arrival_city) {
        return flightDAO.getAllFlightsFromCityToCity(departure_city, arrival_city);
    }
}
