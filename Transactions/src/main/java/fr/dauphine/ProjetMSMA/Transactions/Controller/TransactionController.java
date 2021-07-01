package fr.dauphine.ProjetMSMA.Transactions.Controller;

import fr.dauphine.ProjetMSMA.Transactions.Model.Transaction;
import fr.dauphine.ProjetMSMA.Transactions.Repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;


@RestController
public class TransactionController {

    // Spring se charge de la création d'instance
    @Autowired
    private Environment environment;

    // Spring se charge de la création d'instance
    @Autowired
    private TransactionRepository repository;


    @GetMapping("/transactions")
    public List<Transaction> afficherTaux(){
        List<Transaction> transaction = repository.findAll();

        return transaction;
    }

    @DeleteMapping("/transactions/delete={id}")
    public void supprimerTaux(@PathVariable Long id){
        repository.deleteById(id);
    }


    @GetMapping("/transaction/source/{source}/dest/{dest}")
    public List<Transaction> retrouveTransactionParSourceEtDest
            (@PathVariable String source, @PathVariable String dest) {

        List<Transaction> transaction =
                repository.findBySourceAndDest(source, dest);


        return transaction;
    }

    @GetMapping("/transaction/date/{date}")
    public List<Transaction> retrouveTransactionParDate
            (@PathVariable Date date) {

        List<Transaction> transaction =
                repository.findByDateCotation(date);


        return transaction;
    }

    @RequestMapping
    public void ajoutTransaction(Transaction transaction){

    }



}