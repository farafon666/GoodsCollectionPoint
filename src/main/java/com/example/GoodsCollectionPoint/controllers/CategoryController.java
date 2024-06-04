package com.example.GoodsCollectionPoint.controllers;

import com.example.GoodsCollectionPoint.models.Category;
import com.example.GoodsCollectionPoint.services.CategoryService;
import com.example.GoodsCollectionPoint.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping("/categories")
    public String getCategories(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/categories/create")
    public String showCreateForm(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("category", new Category());
        return "create_category";
    }

    @PostMapping("/categories/create")
    public String createCategory(@ModelAttribute Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, Principal principal) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("category", category);
        return "edit_category";
    }

    @PostMapping("/categories/edit")
    public String editCategory(@ModelAttribute Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
