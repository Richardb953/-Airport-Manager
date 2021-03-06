package cz.muni.airport.dao.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cz.muni.airport.dao.AirportDAO;
import cz.muni.airport.database.testConfig;
import cz.muni.airport.model.Airport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests for AirportDAOImpl class
 *
 * @author Andrea Navratilova, github name: andrea-n
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringApplicationConfiguration(testConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource("/application.properties")
// Enable JMX so we can test the MBeans (you can't do this in a properties file)
@TestPropertySource(properties = {"spring.jmx.enabled:true",
    "spring.datasource.jmx-enabled:true"})
public class AirportDAOImplTest {

    @Autowired
    private AirportDAO airportDAO;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addAirport method, of class AirportDAOImpl.
     */
    @Test
    public void testAddAirport() {
        System.out.println("add Airport");

        Airport airport = new Airport();
        airport.setName("Brno letiste");
        airport.setCountry("Ceska republika");
        airport.setCity("Brno");
        airport.setIata("BRN");
        airportDAO.addAirport(airport);
        assertEquals(1, airportDAO.getAllAirports().size());
        assertEquals(airport, airportDAO.getAirportsByName("Brno letiste").get(0));
    }

    /**
     * Test of addAirport method, of class AirportDAOImpl, with null given.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddAirportWithNull() {
        airportDAO.addAirport(null);
    }

    /**
     * Test of updateAirport method, of class AirportDAOImpl.
     */
    @Test
    public void testUpdateAirport() {
        System.out.println("update Airport");

        Airport airport = new Airport();
        airport.setName("Brno letiste");
        airport.setCountry("Ceska republika");
        airport.setCity("Brno");
        airport.setIata("BRN");
        airportDAO.addAirport(airport);

        Airport airportUpdate = airportDAO.getAirportsByName("Brno letiste").get(0);
        airportUpdate.setName("Paris Aeroport");
        airportUpdate.setCountry("France");
        airportUpdate.setCity("Paris");
        airportUpdate.setIata("PRS");
        airportDAO.updateAirport(airportUpdate);

        assertEquals("Paris Aeroport", airportDAO.getAirportById(airportUpdate.getId()).getName());
        assertEquals("France", airportDAO.getAirportById(airportUpdate.getId()).getCountry());
        assertEquals("Paris", airportDAO.getAirportById(airportUpdate.getId()).getCity());
        assertEquals("PRS", airportDAO.getAirportById(airportUpdate.getId()).getIata());
    }

    /**
     * Test of updateAirport method, of class AirportDAOImpl, with null given.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateAirportWithNull() {
        airportDAO.updateAirport(null);
    }

    /**
     * Test of removeAirport method, of class AirportDAOImpl.
     */
    @Test
    public void testRemoveAirport() {
        System.out.println("remove Airport");

        Airport airport1 = new Airport();
        airport1.setName("Brno letiste");
        airport1.setCountry("Ceska republika");
        airport1.setCity("Brno");
        airport1.setIata("BRN");
        airportDAO.addAirport(airport1);

        Airport airport2 = new Airport();
        airport2.setName("Paris Aeroport");
        airport2.setCountry("France");
        airport2.setCity("Paris");
        airport2.setIata("PRS");
        airportDAO.addAirport(airport2);

        airportDAO.removeAirport(airport1);
        assertEquals(1, airportDAO.getAllAirports().size());
        assertEquals("Paris Aeroport", airportDAO.getAllAirports().get(0).getName());
    }

    /**
     * Test of removeAirport method, of class AirportDAOImpl, with null given.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAirportWithNull() {
        airportDAO.removeAirport(null);
    }

    /**
     * Test of getAllAirports method, of class AirportDAOImpl.
     */
    @Test
    public void testGetAllAirports() {
        System.out.println("get all Airports");

        Airport airport1 = new Airport();
        airport1.setName("Brno letiste");
        airport1.setCountry("Ceska republika");
        airport1.setCity("Brno");
        airport1.setIata("BRN");
        airportDAO.addAirport(airport1);

        Airport airport2 = new Airport();
        airport2.setName("Paris Aeroport");
        airport2.setCountry("France");
        airport2.setCity("Paris");
        airport2.setIata("PRS");
        airportDAO.addAirport(airport2);

        assertEquals(2, airportDAO.getAllAirports().size());
    }

    /**
     * Test of getAirportById method, of class AirportDAOImpl.
     */
    @Test
    public void testGetAirportById() {
        System.out.println("get Airport by id");

        Airport airport = new Airport();
        airport.setName("Brno letiste");
        airport.setCountry("Ceska republika");
        airport.setCity("Brno");
        airport.setIata("BRN");
        Airport result = airportDAO.addAirport(airport);

        assertEquals(result, airportDAO.getAirportById(result.getId()));
    }

    /**
     * Test of getAirportById method, of class AirportDAOImpl, with null given.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetAirportByIdWithNull() {
        airportDAO.getAirportById(null);
    }

    /**
     * Test of getAirportById method, of class AirportDAOImpl, with nonexisting
     * id given.
     */
    @Test
    public void testGetAirportByIdWithNonexistingId() {
        Airport airport = new Airport();
        airport.setName("Brno letiste");
        airport.setCountry("Ceska republika");
        airport.setCity("Brno");
        airport.setIata("BRN");
        airportDAO.addAirport(airport);

        assertNull(airportDAO.getAirportById(new Long(10)));
    }

    /**
     * Test of getAirportsByCity method, of class AirportDAOImpl.
     */
    @Test
    public void testGetAirportsByCity() {
        System.out.println("get Airports by city");

        Airport airport1 = new Airport();
        airport1.setName("Brno letiste");
        airport1.setCountry("Ceska republika");
        airport1.setCity("Brno");
        airport1.setIata("BRN");
        airportDAO.addAirport(airport1);

        Airport airport2 = new Airport();
        airport2.setName("Paris Aeroport");
        airport2.setCountry("France");
        airport2.setCity("Paris");
        airport2.setIata("PRS");
        airportDAO.addAirport(airport2);

        assertEquals(airport1, airportDAO.getAirportsByCity("Brno").get(0));
        assertEquals(1, airportDAO.getAirportsByCity("Brno").size());
    }

    /**
     * Test of getAirportsByCity method, of class AirportDAOImpl, with null
     * given.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetAirportsByCityWithNull() {
        airportDAO.getAirportsByCity(null).size();
    }

    /**
     * Test of getAirportsByCity method, of class AirportDAOImpl, with
     * nonexisting city given.
     */
    @Test
    public void testGetAirportsByCityWithNonexisting() {
        Airport airport = new Airport();
        airport.setName("Brno letiste");
        airport.setCountry("Ceska republika");
        airport.setCity("Brno");
        airport.setIata("BRN");
        airportDAO.addAirport(airport);

        assertEquals(0, airportDAO.getAirportsByCity("Praha").size());
    }

    /**
     * Test of getAirportsByName method, of class AirportDAOImpl.
     */
    @Test
    public void testGetAirportsByName() {
        System.out.println("get Airports by name");

        Airport airport1 = new Airport();
        airport1.setName("Brno letiste");
        airport1.setCountry("Ceska republika");
        airport1.setCity("Brno");
        airport1.setIata("BRN");
        airportDAO.addAirport(airport1);

        Airport airport2 = new Airport();
        airport2.setName("Paris Aeroport");
        airport2.setCountry("France");
        airport2.setCity("Paris");
        airport2.setIata("PRS");
        airportDAO.addAirport(airport2);

        assertEquals(airport1, airportDAO.getAirportsByName("Brno letiste").get(0));
        assertEquals(1, airportDAO.getAirportsByName("Brno letiste").size());
    }

    /**
     * Test of getAirportsByName method, of class AirportDAOImpl, with null
     * given.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetAirportsByNameWithNull() {
        airportDAO.getAirportsByName(null).size();
    }

    /**
     * Test of getAirportsByName method, of class AirportDAOImpl, with
     * nonexisting name given.
     */
    @Test
    public void testGetAirportsByNameWithNonexisting() {
        Airport airport = new Airport();
        airport.setName("Brno letiste");
        airport.setCountry("Ceska republika");
        airport.setCity("Brno");
        airport.setIata("BRN");
        airportDAO.addAirport(airport);

        assertEquals(0, airportDAO.getAirportsByName("Praha letiště").size());
    }

    /**
     * Test of getAirportsByCountry method, of class AirportDAOImpl.
     */
    @Test
    public void testGetAirportsByCountry() {
        System.out.println("get Airports by country");

        Airport airport1 = new Airport();
        airport1.setName("Brno letiste");
        airport1.setCountry("Ceska republika");
        airport1.setCity("Brno");
        airport1.setIata("BRN");
        airportDAO.addAirport(airport1);

        Airport airport2 = new Airport();
        airport2.setName("Paris Aeroport");
        airport2.setCountry("France");
        airport2.setCity("Paris");
        airport2.setIata("PRS");
        airportDAO.addAirport(airport2);

        assertEquals(airport1, airportDAO.getAirportsByCountry("Ceska republika").get(0));
        assertEquals(1, airportDAO.getAirportsByCountry("Ceska republika").size());
    }

    /**
     * Test of getAirportsByCountry method, of class AirportDAOImpl, with null
     * given.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetAirportsByCountryWithNull() {
        airportDAO.getAirportsByCountry(null).size();
    }

    /**
     * Test of getAirportsByCountry method, of class AirportDAOImpl, with
     * nonexisting country given.
     */
    @Test
    public void testGetAirportsByCountryWithNonexisting() {
        Airport airport = new Airport();
        airport.setName("Brno letiste");
        airport.setCountry("Ceska republika");
        airport.setCity("Brno");
        airport.setIata("BRN");
        airportDAO.addAirport(airport);

        assertEquals(0, airportDAO.getAirportsByCountry("Germany").size());
    }

    /**
     * Test of getAirportsByIata method, of class AirportDAOImpl.
     */
    @Test
    public void testGetAirportsByIata() {
        System.out.println("get Airports by IATA");

        Airport airport = new Airport();
        airport.setName("Brno letiste");
        airport.setIata("BRN");
        airport.setCountry("Ceska republika");
        airport.setCity("Brno");
        airportDAO.addAirport(airport);

        assertEquals(airport, airportDAO.getAirportsByIata("BRN").get(0));
        assertEquals(1, airportDAO.getAirportsByIata("BRN").size());
    }

    /**
     * Test of getAirportsByIata method, of class AirportDAOImpl, with null
     * given.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetAirportsByIataWithNull() {
        airportDAO.getAirportsByIata(null).size();
    }
}
