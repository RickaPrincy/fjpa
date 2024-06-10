package com.ricka.princy.fjpa;

import com.ricka.princy.fjpa.exceptions.NotImplementedException;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.util.List;

@AllArgsConstructor
public class FJPARepository<Type, Id> {
    final private Connection connection;

    public List<Type> getAll(){
        throw new NotImplementedException();
    }

    public List<Type> getById(Id id){
        throw new NotImplementedException();
    }
}