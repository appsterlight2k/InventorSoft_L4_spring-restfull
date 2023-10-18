package co.inventorsoft.academy.spring.restfull.controller;

import co.inventorsoft.academy.spring.restfull.dto.ItemDto;
import co.inventorsoft.academy.spring.restfull.dto.WebResponse;
import co.inventorsoft.academy.spring.restfull.model.Item;
import co.inventorsoft.academy.spring.restfull.service.ItemService;
import co.inventorsoft.academy.spring.restfull.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private MapperUtil mapperUtil;


    @PostMapping("/items/new")
    public ItemDto create(@RequestBody ItemDto itemDto) {
        Item item = mapperUtil.convertToEntity(itemDto, Item.class);
        Item updated = itemService.update(item);
        return mapperUtil.convertToDto(updated, ItemDto.class);
    }

    @PutMapping("/items/{id}")
    public ItemDto update(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        Item item = mapperUtil.convertToEntity(itemDto, Item.class);
        Item updated = itemService.update(item);

        return mapperUtil.convertToDto(updated, ItemDto.class);
    }

    @GetMapping(value = "/items")
    public ResponseEntity<WebResponse<List<ItemDto>>> getAllItems() {
        List<Item> items = itemService.findAll();
        return getWebResponseEntityFoAll(items);
    }

    @GetMapping(value = "/items",  params = "price")
    public ResponseEntity<WebResponse<List<ItemDto>>> getItemsByPrice(@RequestParam Double price) {
        List<Item> items = itemService.findAllByPrice(price);
        return getWebResponseEntityFoAll(items);
    }

    @GetMapping(value = "/items/{id}")
    public ResponseEntity<WebResponse<ItemDto>> getItemById(@PathVariable Long id) {
        Optional<Item> optionalItem = itemService.getById(id);

        if (optionalItem.isEmpty()) {
            WebResponse<ItemDto> response = new WebResponse<>(null, "Item was not found!", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Item item = optionalItem.get();
        ItemDto dto = mapperUtil.convertToDto(item, ItemDto.class);

        WebResponse<ItemDto> response = new WebResponse<>(dto, "Item fetched successfully", true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public boolean deleteById(@PathVariable Long id) {
        itemService.deleteById(id);

        return itemService.getById(id).isEmpty();
    }

    private ResponseEntity<WebResponse<List<ItemDto>>> getWebResponseEntityFoAll(List<Item> items) {
        List<ItemDto> itemsDto = mapperUtil.convertToDtoList(items, ItemDto.class);

        boolean isSuccess = !itemsDto.isEmpty();
        String message = isSuccess ? "Items were fetched successfully" : "Items were not found!";

        WebResponse<List<ItemDto>> response = new WebResponse<>(isSuccess ? itemsDto : null, message, isSuccess);
        HttpStatus status = isSuccess ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(response, status);
    }

}
