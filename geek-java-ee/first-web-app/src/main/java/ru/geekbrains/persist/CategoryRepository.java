package ru.geekbrains.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CategoryRepository {
    private Map<Long, Category> categoryMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    public List<Category> findAll() {
        return new ArrayList<>(categoryMap.values());
    }

    public Category findById(long id) {
        return categoryMap.get(id);
    }

    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            Long id = identity.incrementAndGet();
            category.setId(id);
        }
        categoryMap.put(category.getId(), category);
    }

    public void deleteById(Long id) {
        categoryMap.remove(id);
    }

}
