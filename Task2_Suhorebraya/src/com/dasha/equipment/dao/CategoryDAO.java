package com.dasha.equipment.dao;


import com.dasha.equipment.bean.Category;
import com.dasha.equipment.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Даша on 12.01.2017.
 */
public interface CategoryDAO {
    List<Category> getAllCategories() throws DAOException;
    void addCategory(Category category) throws DAOException;
    void deleteCategory(int id) throws DAOException;
}
