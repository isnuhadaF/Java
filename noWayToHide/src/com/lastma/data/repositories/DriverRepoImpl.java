package com.lastma.data.repositories;

import com.lastma.data.models.Driver;

import java.util.ArrayList;
import java.util.List;

public class DriverRepoImpl implements DriverRepo {

    private List<Driver> drivers = new ArrayList<>();
    private int numberOfDrivers;

    @Override
    public Driver save(Driver driver) {
        driver.setId(++numberOfDrivers);
        drivers.add(driver);
        return driver;
    }

    @Override
    public long count() {
        return numberOfDrivers;
    }

    @Override
    public Driver findById(int id) {
        return null;
    }

    @Override
    public List<Driver> findAll() {
        return List.of();
    }

    @Override
    public void delete(int driverId) {

    }

    @Override
    public void deleteAll() {

    }
}
