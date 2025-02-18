package com.github.danielrodj.repositories;

import com.github.danielrodj.database.BasicRepository;
import com.github.danielrodj.models.Area;

public class AreaRepositoryCSV extends BasicRepository<Area> {

    private static final String CSV_FILE = "src/main/resources/com/github/danielrodj/csv/Areas.csv";
    private static final String HEADER = "ID;Area Name;Location";

    public AreaRepositoryCSV() {
        super(CSV_FILE, HEADER);
    }

    @Override
    protected int getId(Area area) {
        return area.getAreaId();
    }

    @Override
    protected Area parse(String[] values) {
        return new Area(Integer.parseInt(values[0]), values[1], values[2]);
    }

    @Override
    protected String format(Area area) {
        return String.join(";", String.valueOf(area.getAreaId()), area.getAreaName(), area.getAreaLocation());
    }
}