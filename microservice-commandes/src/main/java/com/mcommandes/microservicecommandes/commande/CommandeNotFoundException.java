package com.mcommandes.microservicecommandes.commande;

public class CommandeNotFoundException extends RuntimeException{
    public CommandeNotFoundException(String message) {
        super(message);
    }
}
