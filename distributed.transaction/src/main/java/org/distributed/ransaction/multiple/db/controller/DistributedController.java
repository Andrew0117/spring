package org.distributed.ransaction.multiple.db.controller;

import jakarta.persistence.RollbackException;
import org.distributed.ransaction.multiple.db.services.DistributedTransaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Berezhnov Andrey on 2024-04-03 20:58
 * @see <a href="#">Andrey at andrew.my@yahoo.com</a>
 */
@RestController
@RequestMapping("/")
public class DistributedController {

    private DistributedTransaction distributedTransaction;

    public DistributedController(DistributedTransaction distributedTransaction) {
        this.distributedTransaction = distributedTransaction;
    }

    @GetMapping("/insert")
    public ResponseEntity<String> isInsert() {
        distributedTransaction.insertIntoMultipleDBs();

        return ResponseEntity.ok().body("Ok");
    }

    @GetMapping("rollback")
    public ResponseEntity<String> isRollback() {
        try{
            distributedTransaction.rollbackIntoMultipleDBs();
        } catch (RollbackException rollbackException) {
            System.out.println(rollbackException);
        }

        return ResponseEntity.ok().body("rollback");
    }

}
