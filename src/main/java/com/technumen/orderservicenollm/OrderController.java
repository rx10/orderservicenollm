package com.technumen.orderservicenollm;

import com.technumen.orderservicenollm.model.Order;
import com.technumen.orderservicenollm.repository.OrderRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.technumen.orderservicenollm.service.OrdServ;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://apifrontend.up.railway.app")
@RestController
public class OrderController {
    private final OrderRepo repository;

    private OrdServ ordserv = new OrdServ();

    OrderController(OrderRepo repository) { this.repository = repository; }

    //Create
    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        if (ordserv.doesExist(order.getCustId())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(order));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(order);
        }
    }

    //Read
    @GetMapping("/")
    public List<Order> getAll() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable Long id) {
        return repository.findById(id);
    }

    //Update
    @PutMapping("/{id}")
    public Order updateOrder(@RequestBody Order order, @PathVariable Long id) {
        order.setId(order.getId());
        return repository.findById(id)
                .map(ord -> {
                    ord.setId(order.getId());
                    ord.setCustId(order.getCustId());
                    ord.setItemName(order.getItemName());
                    ord.setItemPrice(order.getItemPrice());
                    ord.setItemQty(order.getItemQty());
                    return repository.save(ord);
                })
                .orElseGet(() -> repository.save(order));
    }

    //Delete
    @DeleteMapping("{id}")
    public boolean deleteOrder(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}