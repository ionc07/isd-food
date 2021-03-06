package com.example.isdbackend.controller;


import com.example.isdbackend.dto.ProviderAvailableDTO;
import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.model.Provider;
import com.example.isdbackend.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/provider")
public class ProviderController {
    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping
    public void addProvider(@RequestBody Provider provider) {
        providerService.addProvider(provider);
    }

    @GetMapping("/getProvider/{id}")
    public Provider getProviderById(@PathVariable Integer id) {
        return providerService.findById(id);
    }

    @GetMapping("/all")
    public List<Provider> getAllProviders() {
        return providerService.findAllProviders();
    }

    @GetMapping("/available")
    public List<ProviderAvailableDTO> getProviders(OrderFilter orderFilter) {
        return providerService.getProviders(orderFilter);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteProvider(@PathVariable Integer id) {
        providerService.delete(id);
    }

    @PutMapping("/edit/{id}/{name}/{contact}/{price}/{active}/{description}/{img}")
    public void editProvider(@PathVariable Integer id, @PathVariable String name, @PathVariable String contact, @PathVariable Integer price, @PathVariable Boolean active, @PathVariable String description, @PathVariable String img) {
        Provider provider = providerService.findById(id);
        provider.setName(name);
        provider.setContactInfo(contact);
        provider.setDeliveryPrice(price);
        provider.setActive(active);
        provider.setImage(img);
        provider.setDescription(description);
        providerService.addProvider(provider);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateProvider(@RequestBody Provider provider) {
        providerService.updateProvider(provider);
    }

    @DeleteMapping("/{id}")
    public void deleteProvider(@PathVariable("id") int id) {
        providerService.deleteProvider(id);
    }

}
