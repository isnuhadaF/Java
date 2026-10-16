package com.lastma.data.repositories;

import com.lastma.data.models.Officer;

import java.util.ArrayList;
import java.util.List;

public class OfficerRepoImpl implements OfficerRepo {

    private List<Officer> officers = new ArrayList<>();
    private int numberOfOfficers;

    @Override
    public Officer save(Officer officer) {
        officer.setId(++numberOfOfficers);
        officers.add(officer);
        return officer;
    }

    @Override
    public long count() {
        return numberOfOfficers;
    }

    @Override
    public Officer findById(int id) {
        return null;
    }

    @Override
    public List<Officer> findAll() {
        return List.of();
    }

    @Override
    public void delete(int officerId) {

    }

    @Override
    public void deleteAll() {

    }
}