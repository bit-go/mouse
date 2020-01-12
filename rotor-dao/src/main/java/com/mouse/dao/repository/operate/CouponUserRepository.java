package com.mouse.dao.repository.operate;

import com.mouse.dao.entity.operate.CouponEntity;
import com.mouse.dao.entity.operate.CouponUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface CouponUserRepository extends JpaRepository<CouponUserEntity, Integer>, JpaSpecificationExecutor<CouponUserEntity> {

    /**
     * 根据用户ID查询用户优惠券
     *
     * @param userId  用户ID
     * @param deleted
     * @return
     */
    Optional<List<CouponUserEntity>> findByUserIdAndDeleted(Integer userId, Boolean deleted);

    /**
     * 查询优惠券集合
     *
     * @param deleted
     * @param couponIds
     * @return
     */
    Optional<List<CouponEntity>> findByDeletedAndIdIn(boolean deleted, List<Integer> couponIds);

    /**
     * 统计优惠券可领取数量
     *
     * @param couponId 优惠券ID
     * @return
     */
    Integer countByCouponIdAndDeleted(Integer couponId, boolean deleted);

    /**
     * 统计用户优惠券领取数量
     *
     * @param userId   用户ID
     * @param couponId 优惠券ID
     * @return
     */
    Integer countByUserIdAndCouponIdAndDeleted(Integer userId, Integer couponId, boolean deleted);
}