package com.mcommandes.microservicecommandes.commande;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;

@Builder
public record CommandeResponse
        (
                String description,
                Integer quantité,
                Double montant ,
                LocalDate date
        ) {}
