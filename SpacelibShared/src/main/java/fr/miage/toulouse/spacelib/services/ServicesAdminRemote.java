/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.spacelib.services;

import javax.ejb.Remote;

/**
 *
 * @author mahdi
 */
@Remote
public interface ServicesAdminRemote {
    public void creerStation (Long id ) ;
    
}
