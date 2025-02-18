package com.github.danielrodj.repositories;

import com.github.danielrodj.database.BasicRepository;
import com.github.danielrodj.models.User;

public class UserRepositoryCSV extends BasicRepository<User>{

    private static final String CSV_FILE = "src/main/resources/com/github/danielrodj/csv/Users.csv";
    private static final String HEADER = "ID;Username;Password;";

    public UserRepositoryCSV() {
        super(CSV_FILE, HEADER);
    }

    @Override
    protected int getId(User user) {
        return user.getUserId();
    }

    @Override
    protected User parse(String[] values) {
        return new User(Integer.parseInt(values[0]), values[1], values[2]);
    }

    @Override
    protected String format(User user) {
        return String.join(";", new String[] {
                String.valueOf(user.getUserId()),
                user.getUsername(),
                user.getPassword()
        });
    }
    
}
