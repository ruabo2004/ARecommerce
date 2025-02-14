package com.ecommerce.vn.service.order.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.vn.entity.coupon.Coupon;
import com.ecommerce.vn.entity.order.Order;
import com.ecommerce.vn.entity.order.OrderStatus;
import com.ecommerce.vn.entity.user.User;
import com.ecommerce.vn.repository.OrderRepository;
import com.ecommerce.vn.service.coupon.CouponService;
import com.ecommerce.vn.service.order.OrderService;
import com.ecommerce.vn.service.user.UserService;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private UserService userService;

	@Override
	public Order createOrder(Order order) {
		
		if(isOrderEmpty(order)) {
			throw new RuntimeException("Order is empty!");
		}
		Coupon coupon = order.getCoupon();
		if(coupon != null) {
			BigDecimal discountPrice = calculateTotalPriceWithCoupon(order,coupon);
			order.setDiscountPrice(discountPrice);
		}
		BigDecimal totalPrice = calculateTotalPrice(order);
		order.setTotalPrice(totalPrice);			
		
		return orderRepository.save(order);
	}

	@Override
	public Order getOrderById(UUID orderId) {
		if(orderId == null) {
			throw new RuntimeException("Order id is null!");
		}
		return orderRepository.findById(orderId).orElseThrow(
				() -> new RuntimeException("Cant not found order with id :" + orderId));
	}

	@Override
	public List<Order> getAllOrders() {
		
		return orderRepository.findAll();
	}

	@Override
	public Order updateOrder(Order updatedOrder) {
		if(updatedOrder.getId() == null) {
			throw new RuntimeException("Order id id null");
		}
		
		if(orderRepository.findById(updatedOrder.getId()).isEmpty()) {
			throw new RuntimeException("Order not found");
		}
		
		return orderRepository.save(updatedOrder);
	}

	@Override
	public void deleteOrder(UUID orderId) {
		Order order = getOrderById(orderId);
		
		orderRepository.delete(order);
	}

	@Override
	public Order updateOrderStatus(UUID orderId, OrderStatus orderStatus) {
		Order order = getOrderById(orderId);
		if(order != null) {
			order.setOrderStatus(orderStatus);
			updateOrder(order);
		}

		return order;
	}
	
	@Override
	public Order applyCouponToOrder(String couponCode, Order order) {
		if(couponCode.isEmpty()) {
			throw new RuntimeException("Invalid coupon code");
		}
		
		Coupon coupon = couponService.getCouponByCode(couponCode);
		if (coupon != null) {
			order.setCoupon(coupon);
			
			BigDecimal discountPrice = calculateTotalPriceWithCoupon(order, coupon);
			order.setDiscountPrice(discountPrice);
		}
		return updateOrder(order);
	}

	@Override
	public Order addCouponToOrder(UUID orderId, Coupon coupon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal calculateTotalPriceWithCoupon(Order order, Coupon coupon) {
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		if (coupon == null || coupon.getDiscountType() == null || coupon.getDiscountValue() == null) {
		    throw new IllegalArgumentException("Coupon is invalid");
		}
		
		switch (coupon.getDiscountType()) {
	        case ORDER_VALUE_BASED:
	            if (order.getTotalPrice().compareTo(BigDecimal.valueOf(coupon.getMinimumOrderAmount())) >= 0) {
	                totalPrice = order.getTotalPrice().subtract(coupon.getDiscountValue());
	            } else {
	                throw new IllegalArgumentException("Order total price must be higher than coupon minimum amount");
	            }
	            break;
	
	        case PERCENTAGE:
	            BigDecimal discount = order.getTotalPrice().multiply(coupon.getDiscountValue().divide(BigDecimal.valueOf(100)));
	            totalPrice = order.getTotalPrice().subtract(discount);
	            break;
	
	        default:
	            throw new IllegalArgumentException("Unsupported discount type");
		}
		
		return totalPrice;
	}

	@Override
	public Order addNotes(UUID orderId, String notes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrdersByUserId(UUID userId) {
		User user = userService.findUserByUuId(userId);
		
		return user.getOrders();
	}

	@Override
	public List<Order> getOrdersByStatus(OrderStatus status) {
		if(status == null) {
			throw new RuntimeException("Status is missing.");
		}
		return orderRepository.findByOrderStatus(status);
	}

	@Override
	public List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal calculateTotalShippingFee(UUID userId, LocalDateTime startDate, LocalDateTime endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order cancelOrder(UUID orderId) {
		Order order = getOrderById(orderId);
		
		order.setOrderStatus(OrderStatus.CANCELED);
		
		return updateOrder(order);
	}

	@Override
	public List<Order> getPaidOrders() {
		
		return getOrdersByStatus(OrderStatus.PAID);
	}

	@Override
	public BigDecimal calculateTotalPrice(Order order) {
		return order.getOrderItems().stream()
		        .map(orderItem -> {
		            BigDecimal price = orderItem.getVariant() != null ? orderItem.getVariant().getPrice() : BigDecimal.ZERO;
		            return price.multiply(BigDecimal.valueOf(orderItem.getQuantity()));
		        })
		        .reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public Order updateTotalPrice(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order updateTotalPriceWithCoupon(Order order, Coupon coupon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isOrderEmpty(Order order) {
		return order.getOrderItems().isEmpty();
	}


}
