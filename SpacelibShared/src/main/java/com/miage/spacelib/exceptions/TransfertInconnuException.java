/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.exceptions;

/**
 *
 * @author uzanl
 */
public class TransfertInconnuException extends Exception {
    
    public TransfertInconnuException(){
        
    }
    
    public TransfertInconnuException(String msg) {
        super(msg);
    }
}