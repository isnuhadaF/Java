package com.lastma.data.repositories;
import com.lastma.data.models.Vehicle;
import java.util.List;

public interface VehicleRepo {

    Vehicle save(Vehicle vehicle);

    long count();

    Vehicle findById(int id);

    List<Vehicle> findAll();

    void delete(int vehicleId);

    void deleteAll();
}