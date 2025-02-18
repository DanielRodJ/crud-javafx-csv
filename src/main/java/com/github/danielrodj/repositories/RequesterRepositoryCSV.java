package com.github.danielrodj.repositories;

import com.github.danielrodj.database.BasicRepository;
import com.github.danielrodj.models.Requester;

public class RequesterRepositoryCSV extends BasicRepository<Requester> {
    private static final String CSV_FILE = "src/main/resources/com/github/danielrodj/csv/Requesters.csv";
    private static final String HEADER = "ID;First Name;Last Name;Position;Area";

    public RequesterRepositoryCSV(){
        super(CSV_FILE, HEADER);
    }

    @Override
    protected int getId(Requester requester) {
        return requester.getRequesterId();
    }

    @Override
    protected Requester parse(String[] values) {
        return new Requester(Integer.parseInt(values[0]), values[1], values[2], values[3], Integer.parseInt(values[4]));
    }

    @Override
    protected String format(Requester requester) {
        return String.join(";", new String[] {
                String.valueOf(requester.getRequesterId()),
                requester.getFirstName(),
                requester.getLastName(),
                requester.getPosition(),
                String.valueOf(requester.getArea())
        });
    }
}
