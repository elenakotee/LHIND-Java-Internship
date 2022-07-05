package repository;

import model.entity.Flight;

import java.util.List;

public interface FlightRepository extends Repository<Flight, Integer> {

	List<Flight> findByOrigin(String origin);
	List<Flight> findByDestination(String destination);
	List<Flight> findByAirline(String airline);
	List<Flight> findByFlightNumber(String flight_number);
	List<Flight> findByDepartureDate(String departure_date);
	List<Flight> findByArrivalDate(String arrival_date);

}
