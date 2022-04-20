package com.utcn.assignment2.Service;

import com.utcn.assignment2.Model.Category;
import com.utcn.assignment2.Model.Food;
import com.utcn.assignment2.Repo.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {
    private final CategoryRepo repo;

    public CategoryService(CategoryRepo repo) {
        this.repo = repo;
    }

    public List<Food> getAllFoods(Long id) {
        Category category = repo.getById(id);
        return category.getFoods();
    }

    public Boolean create(Category category) {
        for (Category menu : category.getBelongsTo().getMenus()) {
            if (Objects.equals(category.getName(), menu.getName()) && category != menu) {
                return Boolean.FALSE;
            }
        }
        repo.save(category);
        return Boolean.TRUE;
    }

    public Category find(Long id) {
        return repo.getById(id);
    }

}
