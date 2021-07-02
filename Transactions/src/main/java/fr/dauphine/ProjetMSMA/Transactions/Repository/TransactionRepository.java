package fr.dauphine.ProjetMSMA.Transactions.Repository;

import fr.dauphine.ProjetMSMA.Transactions.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;
import java.util.Optional;

@Repository
public interface TransactionRepository extends  JpaRepository<Transaction, Long>{

    List<Transaction> findAll();
    void deleteById(Long id);
    Optional<Transaction> findById(Long id);
    List<Transaction> findBySourceAndDest(String source, String dest);
    List<Transaction> findByDateCotation(Date date);
    List<Transaction> findByMontant(int montant);
    Transaction save(Transaction transaction);

}