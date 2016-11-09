package cz.muni.airport.dto;

/**
 * Created by richard bariny on 9.11.2016.
 * Data transfer object between flights layers
 */
public class FlightDTO {
    public int id;
    public String name;
    public int arrival;
    public int departure;
    public int passagers;
    public int airplane;
    public int destinationport;
    public int sourceport;

    public FlightDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArrival() {
        return arrival;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public int getDeparture() {
        return departure;
    }

    public void setDeparture(int departure) {
        this.departure = departure;
    }

    public int getPassagers() {
        return passagers;
    }

    public void setPassagers(int passagers) {
        this.passagers = passagers;
    }

    public int getAirplane() {
        return airplane;
    }

    public void setAirplane(int airplane) {
        this.airplane = airplane;
    }

    public int getDestinationport() {
        return destinationport;
    }

    public void setDestinationport(int destinationport) {
        this.destinationport = destinationport;
    }

    public int getSourceport() {
        return sourceport;
    }

    public void setSourceport(int sourceport) {
        this.sourceport = sourceport;
    }
}