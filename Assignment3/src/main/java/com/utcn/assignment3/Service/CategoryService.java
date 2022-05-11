package com.utcn.assignment3.Service;

import com.utcn.assignment3.Model.Category;
import com.utcn.assignment3.Model.Food;
import com.utcn.assignment3.Repo.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Service class for category
 */
@Service
public class CategoryService {
    private final CategoryRepo repo;

    public CategoryService(CategoryRepo repo) {
        this.repo = repo;
    }

    /**
     * Gets all foods of a category
     *
     * @param id    category's id
     * @return      the list of foods belonging to the category
     */
    public List<Food> getAllFoods(Long id) {
        Category category = repo.getById(id);
        return category.getFoods();
    }

    /**
     * Saves to the database a new Category
     *
     * @param category  The category details to be added
     * @return          True if success, false otherwise
     */
    public Boolean create(Category category) {
        for (Category menu : category.getBelongsTo().getMenus()) {
            if (Objects.equals(category.getName(), menu.getName()) && category != menu) {
                return Boolean.FALSE;
            }
        }
        repo.save(category);
        return Boolean.TRUE;
    }

    /**
     * Finds by id
     *
     * @param id    the id of the category
     * @return      The category or null if not found
     */
    public Category find(Long id) {
        return repo.getById(id);
    }

}
