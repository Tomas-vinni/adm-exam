package ru.dvinni.davito.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.dvinni.davito.dto.AddItemDTO;
import ru.dvinni.davito.services.ItemService;
import ru.dvinni.davito.services.UserService;

/**
 * Контроллер предметов.
 */
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemsController {

    private final ItemService itemService;
    private final UserService userService;

    @ModelAttribute("addItemDTO")
    public AddItemDTO initForm() {
        return new AddItemDTO();
    }

    @GetMapping("/add")
    public String addItem() {
        return "item-add";
    }

    @PostMapping("/add")
    public String addItem(@Valid AddItemDTO itemDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addItemDTO", itemDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addItemDTO", bindingResult);

            return "redirect:/items/add";
        }

        itemService.addItem(itemDTO);
        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllItems(Model model) {
        model.addAttribute("allItems", itemService.getAllItems());
        return "item-all";
    }

    @GetMapping("/all-product")
    public String showAllProducts(Model model) {
        model.addAttribute("allProducts", itemService.getAllProductItems());
        return "item-all-product";
    }

    @GetMapping("/item-details/{item-id}")
    public String showItemDetails(@PathVariable("item-id") String itemId, Model model) {
        model.addAttribute("itemDetails", itemService.getItem(itemId));
        return "item-details";
    }

    @GetMapping("/item-delete/{item-id}")
    public String deleteItem(@PathVariable("item-id") String itemId) {
        itemService.deleteItem(itemId);
        return "redirect:/items/all-ownership-owner";
    }

    @GetMapping("/item-buy/{item-id}")
    public String buyItem(@PathVariable("item-id") String itemId) {
        userService.buyItem(itemId);
        return "redirect:/users/profile";
    }
}
