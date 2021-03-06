package cz.muni.airport.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cz.muni.airport.enums.PlaneType;

/**
 *
 * @author Karolína Božková, github name: Kayeeec
 */
public class AirplaneDTO {
    private Long id;
    private String name;
    private int capacity;
    private PlaneType type;
    private List<FlightDTO> flights = new ArrayList<>();

    public AirplaneDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public PlaneType getType() {
        return type;
    }

    public void setType(PlaneType type) {
        this.type = type;
    }

    public List<FlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightDTO> flights) {
        this.flights = flights;
    }

    public void addFlight(FlightDTO flightDTO){
        this.flights.add(flightDTO);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.getName());
        hash = 53 * hash + this.getCapacity();
        hash = 53 * hash + Objects.hashCode(this.getType());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof AirplaneDTO)) return false; 
        
        final AirplaneDTO other = (AirplaneDTO) obj;
        
        if (!Objects.equals(this.getCapacity(), other.getCapacity())) return false;
        if (!Objects.equals(this.getName(), other.getName())) return false;
        if (!Objects.equals(this.getType(), other.getType())) return false;
        
        return true;
    }

    @Override
    public String toString() {
        return "AirplaneDTO{" + "id=" + id + ", name=" + name + ", capacity=" + capacity + ", type=" + type + ", flights=" + flights + '}';
    }
    
    
    
    
    
    
    
}
