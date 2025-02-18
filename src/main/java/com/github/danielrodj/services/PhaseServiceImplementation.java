package com.github.danielrodj.services;

import java.util.Map;

import com.github.danielrodj.interfaces.LabelPhaseService;
import com.github.danielrodj.models.LabelPhase;
import com.github.danielrodj.repositories.PhaseRepositoryCSV;

public class PhaseServiceImplementation implements LabelPhaseService {

    PhaseRepositoryCSV phaseRepositoryCSV;

    public PhaseServiceImplementation() {
        this.phaseRepositoryCSV = new PhaseRepositoryCSV();
    }

    @Override
    public LabelPhase get(int id) {
        return phaseRepositoryCSV.get(id);
    }

    @Override
    public Map<Integer, LabelPhase> getAll() {
        return phaseRepositoryCSV.getAll();
    }

    @Override
    public int insert(LabelPhase phase) {
        return phaseRepositoryCSV.insert(phase);
    }

    @Override
    public int update(LabelPhase phase) {
        return phaseRepositoryCSV.update(phase);
    }

    @Override
    public int delete(LabelPhase phase) {
        return phaseRepositoryCSV.delete(phase);
    }

}
