package co.inventorsoft.academy.spring.restfull.controller;

import co.inventorsoft.academy.spring.restfull.dto.ItemDto;
import co.inventorsoft.academy.spring.restfull.dto.WebResponse;
import co.inventorsoft.academy.spring.restfull.model.Item;
import co.inventorsoft.academy.spring.restfull.service.ItemService;
import co.inventorsoft.academy.spring.restfull.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
        Item updated = itemService.save(item);

        return mapperUtil.convertToDto(updated, ItemDto.class);
    }

    @GetMapping(value = "/items")
    public ResponseEntity<WebResponse<List<ItemDto>>> getAllItems() {
        List<Item> items = itemService.findAll();

        return getWebResponseEntityForAll(items);
    }

    //items?price=100
    @GetMapping(value = "/items",  params = "price")
    public ResponseEntity<WebResponse<List<ItemDto>>> getItemsByPrice(@RequestParam Double price) {
        List<Item> items = itemService.findAllByPrice(price);

        return getWebResponseEntityForAll(items);
    }

    @GetMapping(value = "/items/{id}")
    public ResponseEntity<WebResponse<ItemDto>> getItemById(@PathVariable Long id) {
        Optional<ItemDto> optionalDto = itemService.getById(id).map(item -> mapperUtil.convertToDto(item, ItemDto.class));

        boolean isSuccess = optionalDto.isPresent();
        String message = isSuccess ? "Item was fetched successfully" : "Item was not found!";
        Integer count = isSuccess ? 1 : 0;
        WebResponse<ItemDto> response = new WebResponse<>(optionalDto.orElse(null), message, isSuccess, count);
        HttpStatus status = isSuccess ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(response, status);
    }

    //items?name=Jedi
    @GetMapping(value = "/items", params = "name")
    public ResponseEntity<WebResponse<List<ItemDto>>> findItemsByNameContains(@RequestParam("name") String text) {
        List<Item> items = itemService.findAllByNameContains(text);

        return getWebResponseEntityForAll(items);
    }

    @PutMapping("/items/{id}")
    public ItemDto update(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        Item item = mapperUtil.convertToEntity(itemDto, Item.class);
        item.setId(id);
        Item updated = itemService.save(item);

        return mapperUtil.convertToDto(updated, ItemDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        itemService.deleteById(id);
    }

    private ResponseEntity<WebResponse<List<ItemDto>>> getWebResponseEntityForAll(List<Item> items) {
        List<ItemDto> itemsDto = mapperUtil.convertToDtoList(items, ItemDto.class);

        boolean isSuccess = !itemsDto.isEmpty();
        String message = isSuccess ? "Items were fetched successfully" : "Items were not found!";

        WebResponse<List<ItemDto>> response = new WebResponse<>(isSuccess ? itemsDto : null, message, isSuccess, itemsDto.size());
        HttpStatus status = isSuccess ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(response, status);
    }

}
