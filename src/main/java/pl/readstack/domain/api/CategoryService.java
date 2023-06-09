package pl.readstack.domain.api;

import pl.readstack.domain.category.Category;
import pl.readstack.domain.category.CategoryDao;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();

    public List<CategoryName> findAllCategoryNames() {
        return categoryDao.findAll()
                .stream().map(CategoryNameMapper::map)
                .collect(Collectors.toList());
    }

    private static class CategoryNameMapper {
        static CategoryName map (Category c) {
            return new CategoryName(
                    c.getId(),
                    c.getName()
            );
        }
    }
}
