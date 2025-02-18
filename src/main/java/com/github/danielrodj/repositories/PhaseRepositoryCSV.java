package com.github.danielrodj.repositories;

import com.github.danielrodj.database.BasicRepository;
import com.github.danielrodj.models.LabelPhase;

public class PhaseRepositoryCSV extends BasicRepository<LabelPhase> {

    private static final String CSV_FILE = "src/main/resources/com/github/danielrodj/csv/LabelPhases.csv";
    private static final String HEADER = "ID;Acronym;Name;Description";

    public PhaseRepositoryCSV() {
        super(CSV_FILE, HEADER);
    }

    @Override
    protected int getId(LabelPhase phase) {
        return phase.getPhaseId();
    }

    @Override
    protected LabelPhase parse(String[] values) {
        return new LabelPhase(Integer.parseInt(values[0]), values[1], values[2], values[3]);
    }

    @Override
    protected String format(LabelPhase phase) {
        return String.join(";", String.valueOf(phase.getPhaseId()), phase.getPhaseAcronym(), phase.getPhaseName(), phase.getPhaseDescription());
    }
}