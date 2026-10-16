package com.lastma.data.repositories;

import com.lastma.data.models.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverRepoImplTest {

    private DriverRepoImpl driverRepository;
    private Driver driver;
    private Driver driverTwo;

    @BeforeEach

    public void setUp(){
        driverRepository = new DriverRepoImpl();
        driver = new Driver();

    }

    @Test

    public void testThatWhenISaveOneDriver_CountIsOne(){
        driverRepository.save(driver);
        assertEquals(1L, driverRepository.count());
    }

    @Test
    public void testThatWhenIAddTwoDrivers_CountIsTwo(){
        assertEquals(0L, driverRepository.count());
        driverRepository.save(driver);
        driverRepository.save(driverTwo);
        assertEquals(2L, driverRepository.count());
    }

    @Test
    public void testThatWhenISaveDriver_IDIsSet(){
        driverRepository.save(driver);
        assertEquals(1, driver.getId());
    }

//    @Test
//    public void testThatWhenIFindByID_DriverIsFound(){
//        driver.setId(1);
//        driverRepository.save(driver);
//        assertEquals(driver, driverRepository.findById(1));
//    }

}