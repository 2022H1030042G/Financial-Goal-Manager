package com.example.fgm.service;

import com.example.fgm.model.Item;
import java.util.List;

public interface ItemService {
    List<Item> getAllItem();
    void saveItem(Item item);
    void saveAllItem(List<Item> items);
    Item getItemById(long id);
    void deleteItemById(long id);
}