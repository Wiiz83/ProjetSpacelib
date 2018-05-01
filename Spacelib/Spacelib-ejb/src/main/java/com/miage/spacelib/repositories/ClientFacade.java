/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Usager;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author uzanl
 */
@Stateless
public class ClientFacade extends AbstractFacade<Usager> implements ClientFacadeLocal {

    @PersistenceContext(unitName = "SpacelibPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Usager.class);
    }
    
}
