package sistema.de.vendas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistema.de.vendas.database.ItemService;
import sistema.de.vendas.models.Item;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    
    @Autowired
    private ItemService itemService;

    @PostMapping
    public Item createItem(@RequestBody Item item){
        return this.itemService.createItem(item);
    }

    @GetMapping
    public List<Item> getAllItens(){
        return this.itemService.getAllItens();
    }

    //@GetMapping("/{id}")
    public Item getItemById(@PathVariable Integer id){
        return this.itemService.getItemById(id);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Integer id, @RequestBody Item item){
        return this.itemService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Integer id){
        this.itemService.deleteItem(id);
    }

    @GetMapping("/{idOrder}")
    public List<Item> getItensByIdOrder(@PathVariable Integer idOrder){
        return this.itemService.getItensByIdOrder(idOrder);
    }
}
