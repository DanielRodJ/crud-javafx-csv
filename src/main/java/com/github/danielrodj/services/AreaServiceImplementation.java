package com.github.danielrodj.services;

import java.util.Map;

import com.github.danielrodj.interfaces.BasicService;
import com.github.danielrodj.models.Area;
import com.github.danielrodj.repositories.AreaRepositoryCSV;

public class AreaServiceImplementation implements BasicService<Area> {

    private final AreaRepositoryCSV areaRepository;

    public AreaServiceImplementation() {
        this.areaRepository = new AreaRepositoryCSV();
    }

    @Override
    public Map<Integer, Area> getAll() {
        return areaRepository.getAll();
    }

    @Override
    public Area get(int id) {
        return areaRepository.get(id);
    }

    @Override
    public int insert(Area area) {
        return areaRepository.insert(area);
    }

    @Override
    public int update(Area area) {
        return areaRepository.update(area);
    }

    @Override
    public int delete(Area area) {
        return areaRepository.delete(area);
    }
    
}