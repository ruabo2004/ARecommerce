package com.ecommerce.vn.service.coupon;

import java.util.List;
import java.util.UUID;

import com.ecommerce.vn.entity.coupon.Coupon;

public interface CouponService {
    
    Coupon createCoupon(Coupon coupon);

    Coupon updateCoupon(UUID couponId, Coupon updatedCoupon);

    void deleteCoupon(UUID couponId);

    List<Coupon> getAllCoupons();

    Coupon getCouponById(UUID couponId);
     
    List<Coupon> searchCoupons(String keyword);

    boolean isCouponValid(UUID couponId);

    void incrementUsageCount(UUID couponId);
    
    Coupon getCouponByCode(String code);


}