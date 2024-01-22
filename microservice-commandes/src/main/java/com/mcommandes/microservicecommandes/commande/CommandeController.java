package com.mcommandes.microservicecommandes.commande;


import lombok.RequiredArgsConstructor;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/commandes")
@RequiredArgsConstructor
public class CommandeController implements HealthIndicator {

    private final CommandeService commandeService;
    private final CommandeRepository commandeRepository;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCommande(@RequestBody CommandeRequest commandes) {
        commandeService.createCommande(commandes);
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCommandes(@RequestBody List<CommandeRequest> commandes) {
        commandeService.createCommandes(commandes);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommandeResponse> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<CommandeResponse> getCommandeById(@PathVariable("id") Integer id) {
        return commandeService.getCommandeById(id);
    }

    @Override
    public Health health() {
        System.out.println("****** Actuator : CommandeController health() ");
        List<Commande> commandes = commandeRepository.findAll();
        if (commandes.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }
}

