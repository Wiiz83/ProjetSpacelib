/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Station;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author uzanl
 */
@Local
public interface StationFacadeLocal {

    void create(Station station);

    void edit(Station station);

    void remove(Station station);

    Station find(Object id);

    List<Station> findAll();

    List<Station> findRange(int[] range);

    int count();
}