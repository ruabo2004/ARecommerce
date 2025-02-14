package com.ecommerce.vn.service.variant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.ecommerce.vn.entity.attribute.AttributeValue;
import com.ecommerce.vn.entity.product.Variant;

public interface VariantService {

	Variant createVariant(Variant variant);
	
	Variant getVariantById(UUID id);
	
	Variant updateVariant(Variant variant);
	
	void deleteVariant(UUID variantId);
	
	List<Variant> findAllPriceBetween(BigDecimal minPrice,BigDecimal maxPrice);
	
	List<Variant> findAll();
	
	List<Variant> findAllVariantsByProduct(UUID ProductId);
	
	List<Variant> findVariantsByAttributeValues(Set<AttributeValue> attributeValues);
	
	Integer findVariantQuantity(UUID variantId);
	
	boolean isVariantInStock(UUID variantId);
	
	Variant decreaseStock(UUID variantId, Integer quantity);
	
	BigDecimal caculateDiscountPrice(UUID variantId);
	
	boolean isVariantOfProduct(UUID variantId, UUID productId);
    
} 
