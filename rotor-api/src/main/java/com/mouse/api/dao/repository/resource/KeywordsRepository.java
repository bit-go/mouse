package com.mouse.api.dao.repository.resource;

import com.mouse.api.dao.entity.resource.KeywordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 热闹关键词表实体
 * @Date 2019-11-26
 */
@Repository
public interface KeywordsRepository extends JpaRepository<KeywordsEntity, Integer>, JpaSpecificationExecutor<KeywordsEntity> {
}