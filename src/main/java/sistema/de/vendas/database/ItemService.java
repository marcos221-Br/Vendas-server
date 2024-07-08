package sistema.de.vendas.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.de.vendas.models.Item;

@Service
public class ItemService {
    
    @Autowired
    private ItemRepository itemRepository;

    public Item createItem(Item item){
        return this.itemRepository.save(item);
    }

    public List<Item> getAllItens(){
        return this.itemRepository.findAll();
    }

    public Item getItemById(Integer id){
        return this.itemRepository.findById(id).orElse(null);
    }

    public Item updateItem(Integer id, Item item){
        item.setId(id);
        return this.itemRepository.save(item);
    }

    public void deleteItem(Integer id){
        this.itemRepository.deleteById(id);
    }

    public List<Item> getItensByIdOrder(Integer idOrder){
        return this.itemRepository.findItensByOrder(idOrder);
    }
}
