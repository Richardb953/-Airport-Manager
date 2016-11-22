package cz.muni.airport.facadeApi;

import java.util.List;

import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.dto.StewardDTO;

/**
 * Created by Richard Bariny on 9.11.2016.
 * Facade Interface of Flight entity
 * @author Richard Bariny, github name: Richardb953
 */
public interface FlightFacade {

    /**
     * Create new Flight
     * @param flightDTO Flight object as DTO
     * @return id of created object
     */
    Long createFlight(FlightDTO flightDTO);

    /**
     * Get All Flights
     * @return retunr list of all Flights as DTO objects
     */
    List<FlightDTO> getAllFlights();

    /**
     * Get Existing flight by id
     * @param id Flight object id
     * @return FlightDto
     */
    FlightDTO getFlightById(Long id);

    /**
     * Delete Flight by id
     * @param id Flight object id
     */
    void removeFlight(Long id);

    /**
     * Update Flight object
     * @param flightDTO Flight object as DTO
     * @return updated Flight DTO
     */
    FlightDTO updateFlight(FlightDTO flightDTO);

    /**
     * Validate Flight - times, airports and airplane availibilitz
     * The system should also check that the plane does not have another flight scheduled during the time of the this flight.
     * It should also checking for the steward's availability.
     * @param flightDTO Flight object as DTO
     * @return boolean validated or not
     */
    boolean validateFlight(FlightDTO flightDTO);

    /**
     * Add steward to Flight
     * @param flightDTO  Flight object as DTO
     * @param stewardDTO steward object as DTO
     * @return updated Flight DTO
     */
    FlightDTO addStewardToFlight(FlightDTO flightDTO, StewardDTO stewardDTO);


}
