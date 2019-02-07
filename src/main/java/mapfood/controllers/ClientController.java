package mapfood.controllers;

import mapfood.models.Client;
import mapfood.repositories.ClientRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository repository;

    @GetMapping("")
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable ("id") ObjectId id) {
        return repository.findBy_id(id);
    }
}
