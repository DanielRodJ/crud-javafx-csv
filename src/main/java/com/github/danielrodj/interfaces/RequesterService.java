package com.github.danielrodj.interfaces;

import com.github.danielrodj.models.Requester;

public interface RequesterService extends BasicQueries<Requester>{

    String getFirstNameByRequesterId(Integer id);

    String getLastNameByRequesterId(Integer id);

    String getFullNameByRequesterId(Integer id);
    
}