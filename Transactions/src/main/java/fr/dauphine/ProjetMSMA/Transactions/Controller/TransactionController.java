package fr.dauphine.ProjetMSMA.Transactions.Controller;

import fr.dauphine.ProjetMSMA.Transactions.Model.Transaction;
import fr.dauphine.ProjetMSMA.Transactions.Model.TauxChange;
import fr.dauphine.ProjetMSMA.Transactions.Repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;


@RestController
public class TransactionController {

    // Spring se charge de la création d'instance
    @Autowired
    private Environment environment;

    // Spring se charge de la création d'instance
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/transactions")
    public List<Transaction> afficherTaux(){
        List<Transaction> transaction = repository.findAll();

        return transaction;
    }

    @RequestMapping(method={RequestMethod.DELETE,RequestMethod.GET},value = "/transactions/delete={id}")
    public void supprimerTaux(@PathVariable Long id){
        repository.deleteById(id);
    }


    @GetMapping("/transactions/source/{source}/dest/{dest}")
    public List<Transaction> retrouveTransactionParSourceEtDest
            (@PathVariable String source, @PathVariable String dest) {

        List<Transaction> transaction =
                repository.findBySourceAndDest(source, dest);


        return transaction;
    }

    @GetMapping("/transactions/date/{date}")
    public List<Transaction> retrouveTransactionParDate
            (@PathVariable Date date) {

        List<Transaction> transaction =
                repository.findByDateCotation(date);


        return transaction;
    }

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json",value = "/transactions/ajout/id/{id}/source/{source}/dest/{dest}/date/{dateCotation}/montant/{montant}")
    public void ajoutTransaction
            (@PathVariable Long id,@PathVariable String source,@PathVariable String dest,@PathVariable Date dateCotation,@PathVariable int montant){

        //Parsing the Date
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(dateCotation);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1; //We add 1 because Calendar.MONTH goes from 0 to 11
        int day = cal.get(Calendar.DAY_OF_MONTH);

        TauxChange taux = restTemplate.getForObject("http://localhost:8000/devise-change/source/"+source+"/dest/"+dest+"/date/"+year+"-"+month+"-"+day,TauxChange.class);
        System.out.println(cal.get(Calendar.YEAR)+" "+cal.get(Calendar.MONTH)+" "+cal.get(Calendar.DAY_OF_MONTH));
        Transaction t = new Transaction(id,source,dest,montant,dateCotation,taux.getTaux());

        repository.save(t);
    }

    @RequestMapping(method = {RequestMethod.PUT,RequestMethod.GET},produces = "application/json",value = "/transactions/modifier/id/{id}/montant/{montant}")
    public Optional<Transaction> modifierMontant
            (@PathVariable Long id,@PathVariable int montant) {
        return repository.findById(id).map(transaction -> {
            transaction.setMontant(montant);
            return repository.save(transaction);
        });
    }

    @GetMapping("/transaction/montant/{montant}")
    public List<Transaction> retrouveTransactionParMontant
            (@PathVariable int montant) {

        List<Transaction> transactions =
                repository.findByMontant(montant);


        return transactions;
    }
}