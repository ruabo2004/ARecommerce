package com.ecommerce.vn.entity.coupon;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.ecommerce.vn.entity.product.DiscountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "coupon")
public class Coupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "code", nullable = false)
	private String code;
	
	@Column(name = "coupon_description", nullable = false)
	private String couponDescription;
	
	@Column(name = "discount_value", nullable = false)
	private BigDecimal discountValue;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "discount_type",nullable = false)
	private DiscountType discountType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "coupon_type",nullable = false)
	private CouponType couponType;
	
	@Column(name = "minimum_order_amount")
	private Double minimumOrderAmount;
	
	@Column(name = "time_used")
	private Integer timeUsed = 0;
	
	@Column(name = "max_usage",nullable = false)
	private Integer maxUsage;
	
	@Column(name = "coupon_start_date", nullable = false)
	private LocalDateTime couponStartDate;
	
	@Column(name = "coupon_end_date", nullable = false)
	private LocalDateTime couponEndDate;
	
	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updateAt;
	
	@Column(name = "created_by")
	private UUID createdBy;
	
	@Column(name = "updated_by")
	private UUID updatedBy;
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	public CouponType getCouponType() {
		return couponType;
	}

	public void setCouponType(CouponType couponType) {
		this.couponType = couponType;
	}

	public Double getMinimumOrderAmount() {
		return minimumOrderAmount;
	}

	public void setMinimumOrderAmount(Double minimumOrderAmount) {
		this.minimumOrderAmount = minimumOrderAmount;
	}

	public Integer getTimeUsed() {
		return timeUsed;
	}

	public void setTimeUsed(Integer timeUsed) {
		this.timeUsed = timeUsed;
	}

	public Integer getMaxUsage() {
		return maxUsage;
	}

	public void setMaxUsage(Integer maxUsage) {
		this.maxUsage = maxUsage;
	}

	public LocalDateTime getCouponStartDate() {
		return couponStartDate;
	}

	public void setCouponStartDate(LocalDateTime couponStartDate) {
		this.couponStartDate = couponStartDate;
	}

	public LocalDateTime getCouponEndDate() {
		return couponEndDate;
	}

	public void setCouponEndDate(LocalDateTime couponEndDate) {
		this.couponEndDate = couponEndDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public UUID getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UUID createdBy) {
		this.createdBy = createdBy;
	}

	public UUID getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UUID updatedBy) {
		this.updatedBy = updatedBy;
	}

    public String getCouponDescription() {
        return couponDescription;
    }

    public void setCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription;
    }

}
