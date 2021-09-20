package com.example.fgm.service;

import com.example.fgm.model.Item;
import com.example.fgm.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    @Override
    public void saveItem(Item item) {
        this.itemRepository.save(item);
    }

    @Override
    @Transactional
    public void saveAllItem(List<Item> items) {
        this.itemRepository.saveAll(items);
        this.itemRepository.flush();
    }

    @Override
    public Item getItemById(long id) {
        Optional<Item> optional = itemRepository.findById(id);
        Item item = null;
        if (optional.isPresent()) {
            item = optional.get();
        } else {
            throw new RuntimeException(" Item not found for id :: " + id);
        }
        return item;
    }

    @Override
    public void deleteItemById(long id) {
        this.itemRepository.deleteById(id);
    }
}
