package com.mouse.api.client.mall;

import com.mouse.api.base.GlobalExceptionHandler;
import com.mouse.api.feign.mall.TopicFeign;
import com.mouse.api.service.GoodsService;
import com.mouse.api.service.TopicService;
import com.mouse.core.base.BusinessException;
import com.mouse.core.base.R;
import com.mouse.core.utils.PageNation;
import com.mouse.dao.entity.operate.TopicEntity;
import com.mouse.dao.entity.resource.GoodsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.*;

/**
 * @author ; lidongdong
 * @Description 首页服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("topic")
public class TopicClient extends GlobalExceptionHandler implements TopicFeign {

    @Autowired
    TopicService topicService;
    @Autowired
    GoodsService goodsService;


    @Override
    public R findPage(@Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                      @RequestParam(name = "sort", defaultValue = "add_time", required = false) String sort,
                      @RequestParam(name = "order", defaultValue = "desc", required = false) String order) {
        Page<TopicEntity> page = topicService.findPage(null, pageNum, pageSize);
        List<TopicEntity> content = page.getContent();
        if (CollectionUtils.isEmpty(content)) {
            return R.success(PageNation.of(page, content));
        }

        return R.success(PageNation.of(page, content));
    }

    @Override
    public R detail(@RequestParam(defaultValue = "id") Integer id) {
        TopicEntity topicEntity = topicService.findById(id).orElseThrow(() -> new BusinessException("专题记录不存在"));

        List<String> goodsIds = Arrays.asList(topicEntity.getGoods());

        List<GoodsEntity> goodsEntities = goodsService.findByIsOnSaleAndIdIn(goodsIds, true).orElseGet(() -> new ArrayList());

        Map<String, Object> entity = new HashMap<String, Object>(8);
        entity.put("topic", topicEntity);
        entity.put("goods", goodsEntities);
        return R.success(entity);
    }

    @Override
    public R related(@RequestParam(defaultValue = "id") Integer id) {
        Page<TopicEntity> topicEntities = topicService.findPage(id, 0, 4);
        return R.success(topicEntities.getContent());
    }
}
