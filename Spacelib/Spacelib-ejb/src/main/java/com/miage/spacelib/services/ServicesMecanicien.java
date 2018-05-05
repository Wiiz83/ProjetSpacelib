/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.services;

import com.miage.spacelib.business.GestionMecanicienLocal;
import com.miage.spacelib.entities.Mecanicien;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author uzanl
 */
@Stateless
public class ServicesMecanicien implements ServicesMecanicienLocal {

    @EJB
    private GestionMecanicienLocal gestionMecanicien;
    
    @Override
    public String toto() {
        return "ça marche";
    }

    @Override
    public List<Mecanicien> findAll() {
        return this.gestionMecanicien.findAll();
    }

    
    
}
