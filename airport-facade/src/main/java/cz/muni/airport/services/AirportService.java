package cz.muni.airport.services;

import cz.muni.airport.model.Airport;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Interface for Airport service
 * 
 * @author Jiri Krejci
 */
@Service
public interface AirportService {

    /**
     * Add airport
     * @param airport Airport object to be added
     * @return Airport object
     */
    Airport addAirport(Airport airport);

    /**
     * Update airport
     * @param airport Airport object to be updated
     * @return Airport object after update
     */
    Airport updateAirport(Airport airport);

    /**
     * Delete airport
     * @param airport Airport object to be deleted 
     */
    void removeAirport(Airport airport);

    /**
     * Get all airports
     * @return List<Airport> of Airport objects
     */
    List<Airport> getAllAirports();

    /**
     * Get Airport
     * @param id Long airport id
     * @return Airport object
     */
    Airport getAirportById(Long id);
    
    /**
     * Get Airport
     * @param iata String airport iata identifier
     * @return Airport object
     */
    Airport getAirportByIata(String iata);

    /**
     * Get all airports with given city
     * @param city String value of city
     * @return List<Airport> of Airport objects
     */
    List<Airport> getAirportsByCity(String city);

    /**
     * Get all airports with given name
     * @param name String value of name
     * @return List<Airport> of Airport objects
     */
    List<Airport> getAirportsByName(String name);

    /**
     * Get all airports with given country
     * @param country String value of country
     * @return List<Airport> of Airport objects
     */
    List<Airport> getAirportsByCountry(String country);

}
