package com.github.danielrodj.services;

import java.util.Map;

import com.github.danielrodj.interfaces.RequesterService;
import com.github.danielrodj.models.Requester;
import com.github.danielrodj.repositories.RequesterRepositoryCSV;

public class RequesterServiceImplementation implements RequesterService {

    RequesterRepositoryCSV requesterRepositoryCSV;

    public RequesterServiceImplementation(){
        this.requesterRepositoryCSV = new RequesterRepositoryCSV();
    }

    @Override
    public Requester get(int id) {
        return requesterRepositoryCSV.get(id);
    }

    @Override
    public Map<Integer, Requester> getAll() {
        return requesterRepositoryCSV.getAll();
    }

    @Override
    public int insert(Requester requester) {
        return requesterRepositoryCSV.insert(requester);
    }

    @Override
    public int update(Requester requester) {
        return requesterRepositoryCSV.update(requester);
    }

    @Override
    public int delete(Requester requester) {
        return requesterRepositoryCSV.delete(requester);
    }

    @Override
    public String getFirstNameByRequesterId(Integer id) {
        return get(id).getFirstName();
    }

    @Override
    public String getLastNameByRequesterId(Integer id) {
        return get(id).getFirstName();
    }

    @Override
    public String getFullNameByRequesterId(Integer id) {
        Requester requester = get(id);
        return requester.getFirstName() + " " + requester.getLastName();
    }
    
}
