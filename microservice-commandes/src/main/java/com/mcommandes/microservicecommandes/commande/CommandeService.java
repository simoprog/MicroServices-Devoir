package com.mcommandes.microservicecommandes.commande;

import com.mcommandes.microservicecommandes.configurations.ApplicationPropertiesConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandeService {

    private final ApplicationPropertiesConfiguration appProperties;

    private final CommandeRepository commandeRepository;
    public void createCommande(CommandeRequest commandeRequest){
        Commande commande = Commande.builder().
                description(commandeRequest.description()).
                quantité(commandeRequest.quantité()).
                date(LocalDate.now()).
                montant(commandeRequest.montant()).
                build();
        commandeRepository.save(commande);
        log.info("Commande {} ajoutée avec succès",commande.getId());

    }

    public void createCommandes(List<CommandeRequest> commandesRequest){
        commandesRequest.forEach(commandeRequest -> {
            Commande commande = Commande.builder().
                    description(commandeRequest.description()).
                    quantité(commandeRequest.quantité()).
                    date(LocalDate.now()).
                    montant(commandeRequest.montant()).
                    build();
            commandeRepository.save(commande);
            log.info("Commande {} ajoutée avec succès",commande.getId());
        });
    }


    public List<CommandeResponse> getAllCommandes() {;
       List<Commande> commandes =  commandeRepository.findAll();
         if(commandes.isEmpty()){
              throw new CommandeNotFoundException("Aucune commande n'est disponible");
            }
         List<Commande> listeLimitée = commandes.subList(0, appProperties.getCommandesLast());
         return listeLimitée.stream().map(this::mapCommandeToCommandeResponse).toList();
    }

    public Optional<CommandeResponse> getCommandeById(Integer id) {
        Optional<Commande> commande = commandeRepository.findById(id);
        if(commande.isEmpty()) {
            throw new CommandeNotFoundException("Commande introuvable");
        }
        return commande.map(this::mapCommandeToCommandeResponse);
    }
    public CommandeResponse mapCommandeToCommandeResponse( Commande commande){
        return CommandeResponse.builder().
                description(commande.getDescription()).
                quantité(commande.getQuantité()).
                date(commande.getDate()).
                montant(commande.getMontant()).
                build();
    }
}
