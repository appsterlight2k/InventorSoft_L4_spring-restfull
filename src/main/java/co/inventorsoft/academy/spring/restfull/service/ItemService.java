package co.inventorsoft.academy.spring.restfull.service;

import co.inventorsoft.academy.spring.restfull.dao.ItemRepository;
import co.inventorsoft.academy.spring.restfull.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> findAllByPrice(Double price) {
        return itemRepository.findAllByPrice(price);
    }

    public Optional<Item> getById(Long id) {
        return itemRepository.findById(id);
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> findAllByNameContains(String text) {
        return itemRepository.findAllByNameContains(text);
    }

}
