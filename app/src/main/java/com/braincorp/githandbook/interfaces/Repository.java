package com.braincorp.githandbook.interfaces;

import java.util.ArrayList;

/**
 * Repository interface
 * Created by Alan Camargo - December 2016
 */
public interface Repository<T>
{

    ArrayList<T> selectAll();
    T select(int id);
    ArrayList<T> selectWhere(Object[] columns, Object[] values);
    int getLastId();

}
