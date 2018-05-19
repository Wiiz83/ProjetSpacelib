/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Conducteur;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.repositories.ConducteurFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author uzanl
 */
@Stateless
public class GestionConducteur implements GestionConducteurLocal {

    @EJB
    private ConducteurFacadeLocal conducteurFacade;
    
    
    @Override
    public Long login(String login, String motdepasse) throws UsagerInconnuException {
        final Conducteur conducteur = this.conducteurFacade.findByLoginAndPassword(login, motdepasse);
        if(mecanicien == null) throw new MecanicienInconnuException("Ce compte de mécanicien n'existe pas.")
    }

    @Override
    public void creerCompte(String nom, String prenom, String login, String motdepasse) {
    
    }


}
