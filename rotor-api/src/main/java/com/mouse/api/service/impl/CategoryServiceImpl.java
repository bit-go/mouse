package com.mouse.api.service.impl;

import com.mouse.api.commons.enums.CategoryLevelEnum;
import com.mouse.api.service.CategoryService;
import com.mouse.dao.entity.resource.CategoryEntity;
import com.mouse.dao.repository.resource.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description 类目表
 * @Date 2019-12-15
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Optional<CategoryEntity> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    /**
     * 根据类目等级查询类目
     *
     * @param categoryLevelEnum 类目等级
     * @return
     */
    @Override
    public Optional<List<CategoryEntity>> findByLevel(CategoryLevelEnum categoryLevelEnum) {
        return categoryRepository.findByLevelAndDeleted(categoryLevelEnum.getCode(), false);
    }

    @Override
    public Optional<List<CategoryEntity>> findByLevelAndNameNotAnd(Integer pageNum, Integer pageSize) {

        return categoryRepository.findByLevelAndDeleted(CategoryLevelEnum.L1.getCode(), false);
    }

    @Override
    public Optional<List<CategoryEntity>> findByPid(Integer pId) {
        return categoryRepository.findByPidAndDeleted(pId, false);
    }

    @Override
    public Optional<List<CategoryEntity>> findByLevelAndIdIn(String level, List<Integer> goodsCatIds) {
        return categoryRepository.findByLevelAndIdIn(level, goodsCatIds);
    }
}
