package com.lastma.data.repositories;
import com.lastma.data.models.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepoImpl implements VehicleRepo {

    private List<Vehicle> vehicles = new ArrayList<>();
    private int numberOfVehicles;

    @Override
    public Vehicle save(Vehicle vehicle) {
        vehicle.setId(++numberOfVehicles);
        vehicles.add(vehicle);
        return vehicle;
    }

    @Override
    public long count() {
        return numberOfVehicles;
    }

    @Override
    public Vehicle findById(int id) {
        return null;
    }

    @Override
    public List<Vehicle> findAll() {
        return List.of();
    }

    @Override
    public void delete(int vehicleId) {

    }

    @Override
    public void deleteAll() {

    }
}