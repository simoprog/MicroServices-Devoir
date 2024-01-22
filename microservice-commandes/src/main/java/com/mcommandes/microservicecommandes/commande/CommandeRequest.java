package com.mcommandes.microservicecommandes.commande;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public record CommandeRequest
        (
        String description,
        Integer quantité,
        Double montant

) {}
