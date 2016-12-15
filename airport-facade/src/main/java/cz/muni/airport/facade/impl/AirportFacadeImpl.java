package cz.muni.airport.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import cz.muni.airport.dto.AirportDTO;
import cz.muni.airport.facadeApi.AirportFacade;
import cz.muni.airport.model.Airport;
import cz.muni.airport.services.AirportService;
import cz.muni.airport.services.BeanMappingService;

/**
 *
 * @author Jiri Krejci, github name: xkrejci7
 */
@Service
@Transactional
public class AirportFacadeImpl implements AirportFacade {

    @Autowired
    private AirportService airportService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public AirportDTO createAirport(AirportDTO airportDTO) {

        Airport airport = beanMappingService.mapTo(airportDTO, Airport.class);
        /*if (airportService.hasValidIata(airport)) {

            Airport created = airportService.saveAirport(airport);
            return beanMappingService.mapTo(created, AirportDTO.class);
        } else {
            throw new IllegalArgumentException("IATA code is invalid");
        }*/
		
		Airport created = airportService.saveAirport(airport);
        return beanMappingService.mapTo(created, AirportDTO.class);
    }

    @Override
    public List<AirportDTO> getAllAirports() {
        return beanMappingService.mapTo(airportService.findAllAirports(), AirportDTO.class);
    }

    @Override
    public AirportDTO getAirportById(Long id) {
        return beanMappingService.mapTo(airportService.findAirportById(id), AirportDTO.class);
    }

    @Override
    public List<AirportDTO> getAirportsByIata(String iata) {
        return beanMappingService.mapTo(airportService.findAirportByIata(iata), AirportDTO.class);
    }

    @Override
    public List<AirportDTO> getAirportsByCity(String city) {
        return beanMappingService.mapTo(airportService.findAirportsByCity(city), AirportDTO.class);
    }

    @Override
    public List<AirportDTO> getAirportsByName(String name) {
        return beanMappingService.mapTo(airportService.findAirportsByName(name), AirportDTO.class);
    }

    @Override
    public List<AirportDTO> getAirportsByCountry(String country) {
        return beanMappingService.mapTo(airportService.findAirportsByCountry(country), AirportDTO.class);
    }

    @Override
    public void removeAirport(Long id) {
        airportService.removeAirport(airportService.findAirportById(id));
    }

    @Override
    public AirportDTO updateAirport(AirportDTO airportDTO) {
        Airport airport = beanMappingService.mapTo(airportDTO, Airport.class);
        return beanMappingService.mapTo(airportService.updateAirport(airport), AirportDTO.class);
    }

    @Override
    public boolean hasValidIAta(AirportDTO airportDTO) {
        Airport airport = beanMappingService.mapTo(airportDTO, Airport.class);
        return airportService.hasValidIata(airport);
    }

}
