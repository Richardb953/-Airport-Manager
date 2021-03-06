package cz.muni.airport.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.facadeApi.StewardFacade;
import java.util.ArrayList;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 *
 * @author Andrea Navratilova, github name: andrea-n
 */
@RestController
@RequestMapping("/rest/stewards")
@Transactional
public class StewardRestController {

    @Autowired
    StewardFacade stewardFacade;

    /**
     * Get list of Stewards curl -i -X GET
     * http://localhost:8082/pa165/rest/stewards
     *
     * @return StewardDTO
     */
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public Collection<StewardDTO> getAllStewards() {

        Collection<StewardDTO> toReturn = new ArrayList<>();
        for (StewardDTO s : stewardFacade.getAllStewards()) {
            s.setFlights(new ArrayList<>());
            toReturn.add(s);
        }
        return toReturn;
    }

    /**
     * Get Steward by identifier curl -i -X GET
     * http://localhost:8082/pa165/rest/stewards/id/1
     *
     * @param id identifier for airport
     * @return StewardDTO
     */
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, value = "/id/{id}")
    public StewardDTO getStewardById(@PathVariable long id) {
        StewardDTO toReturn = stewardFacade.getSteward(id);
        toReturn.setFlights(new ArrayList<>());
        return toReturn;
    }

    /**
     * Get Stewards by first name and last name curl -i -X GET
     * http://localhost:8082/pa165/rest/stewards/name/Anna/Hlucha
     *
     * @param first first name
     * @param last last name
     * @return StewardDTO
     */
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, value = "/name/{first}/{last}")
    public Collection<StewardDTO> getStewardByName(@PathVariable String first, @PathVariable String last) {

        Collection<StewardDTO> toReturn = new ArrayList<>();
        for (StewardDTO s : stewardFacade.getStewardByName(first, last)) {
            s.setFlights(new ArrayList<>());
            toReturn.add(s);
        }
        return toReturn;
    }

    /**
     * Delete Steward specified by identifier curl -i -X DELETE
     * http://localhost:8082/pa165/rest/stewards/id/1
     *
     * @param id identifier for airport
     */
    @RequestMapping(method = DELETE, produces = APPLICATION_JSON_VALUE, value = "/id/{id}")
    public void deleteSteward(@PathVariable long id) {
        stewardFacade.deleteSteward(stewardFacade.getSteward(id));
    }

    /**
     * Create a new steward by POST method curl -X POST -i -H "Content-Type:
     * application/json" --data '{"firstName": "firstName", "lastName":
     * "lastName", "flights":[]}'
     * http://localhost:8082/pa165/rest/stewards/create
     *
     * @param steward StewardDTO with required fields
     * @return the created steward StewardDTO
     */
    @RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, value = "/create")
    public StewardDTO createAirport(@RequestBody StewardDTO steward) {
        return stewardFacade.createSteward(steward);
    }

    /**
     * Update steward by PUT method curl -X PUT -i -H "Content-Type:
     * application/json" --data '{"firstName": "firstNameUpdate", "lastName":
     * "lastNameUpdate", "flights":[]}'
     * http://localhost:8082/pa165/rest/stewards/id/1
     *
     * @param id identified of the steward to be updated
     * @param steward StewardDTO with required fields
     * @return the updated airport StewardDTO
     */
    @RequestMapping(method = PUT, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, value = "/id/{id}")
    public StewardDTO update(@PathVariable("id") long id, @RequestBody StewardDTO steward) {
        steward.setId(id);
        return stewardFacade.updateSteward(steward);
    }
}
