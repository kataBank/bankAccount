package com.bank.account.controler.client;

import com.bank.account.business.cleint.ClientBusiness;
import com.bank.account.entity.Client;
import com.bank.account.model.ClientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@CrossOrigin(value = "*")
public class ClientControler {

  @Autowired private ClientBusiness clientBusiness;

  @PostMapping
  public ResponseEntity<Client> saveClient(@RequestBody ClientModel clientModel) {
    return ResponseEntity.ok(clientBusiness.save(clientModel));
  }

  @PatchMapping
  public ResponseEntity<Client> updateClient(@RequestBody ClientModel clientModel) {
    return ResponseEntity.ok(clientBusiness.updateClient(clientModel));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Client> findClientById(@PathVariable Long id) {
    return ResponseEntity.ok(clientBusiness.findClientByid(id).get());
  }

  @GetMapping("/all")
  public ResponseEntity<List<Client>> findAllClient() {
    return ResponseEntity.ok(clientBusiness.findClientAll());
  }
}
