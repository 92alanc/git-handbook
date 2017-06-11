package com.braincorp.githandbook.interfaces;

import java.util.ArrayList;

/**
 * Repository interface
 * Created by Alan Camargo - December 2016
 */
public interface Repository<T> {

    ArrayList<T> selectAll();
    T select(int id);
    ArrayList<T> selectWhere(String column, Object value);
    int getLastId();

}
