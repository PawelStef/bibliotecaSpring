package com.javagda25.biblioteka.controller;

import com.javagda25.biblioteka.model.PublishingHouse;
import com.javagda25.biblioteka.service.PublishingHouseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/ph/")
public class PublishingHouseController {
    private final PublishingHouseService publishingHouseService;

    @GetMapping(path = "/list")
    public String list (Model model){
        List<PublishingHouse> publishingHouseList = publishingHouseService.getAllHouse();
        model.addAttribute("phlist", publishingHouseList);
        return "ph-list";
    }

    @GetMapping(path = "/add")
    public String add (Model model, PublishingHouse publishingHouse){
        model.addAttribute("publishingHouse", publishingHouse);
        return "ph-add";
    }

    @PostMapping(path = "/add")
    public String add (PublishingHouse publishingHouse){
        publishingHouseService.add(publishingHouse);
        return "redirect:/ph/list";
    }

    @GetMapping(path = "/edit/{edit_ph_id}")
    public String edit(Model model,
                       @PathVariable(name = "edit_ph_id") Long phId){
        Optional<PublishingHouse> phOptional = publishingHouseService.findById(phId);
        if(phOptional.isPresent()){
            model.addAttribute("publishingHouse",phOptional.get());
            return "ph-add";
        }
        return "redirect:/ph/list";
    }

    @GetMapping(path = "/remove/{remove_ph_id}")
    public String remove(Model model,
                       @PathVariable(name = "remove_ph_id") Long phId){
        Optional<PublishingHouse> phOptional = publishingHouseService.findById(phId);
        if(phOptional.isPresent()){
            publishingHouseService.remove(phOptional.get());
        }
        return "redirect:/ph/list";
    }

    @GetMapping("/books/{id}")
    public String getPHBooks(Model model, @PathVariable(name = "id") Long id) {
        Optional<PublishingHouse> publishingHouseOptional = publishingHouseService.findById(id);
        if (publishingHouseOptional.isPresent()) {
            PublishingHouse publishingHouse = publishingHouseOptional.get();

            model.addAttribute("books", publishingHouse.getBooks());

            return "book-list";
        }
        return "redirect:/ph/list";
    }

}
