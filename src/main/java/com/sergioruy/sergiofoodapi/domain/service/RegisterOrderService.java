package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.exception.BusinessException;
import com.sergioruy.sergiofoodapi.domain.exception.OrderNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.City;
import com.sergioruy.sergiofoodapi.domain.model.Order;
import com.sergioruy.sergiofoodapi.domain.model.PaymentMethod;
import com.sergioruy.sergiofoodapi.domain.model.Product;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import com.sergioruy.sergiofoodapi.domain.model.User;
import com.sergioruy.sergiofoodapi.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterOrderService {

    @Autowired
    private RegisterProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RegisterRestaurantService restaurantService;

    @Autowired
    private RegisterPaymentMethodService paymentMethodService;

    @Autowired
    private RegisterCityService cityService;

    @Autowired
    private RegisterUserService userService;

    @Transactional
    public Order issueOrder(Order order) {
        validOrder(order);
        validItems(order);

        order.setTaxDelivery(order.getRestaurant().getTaxDelivery());
        order.calcTotalAmount();

        return orderRepository.save(order);
    }

    private void validOrder(Order order) {
            City city = cityService.findOrFail(order.getAddress().getCity().getId());
            User customer = userService.findOrFail(order.getCustomer().getId());
            Restaurant restaurant = restaurantService.findOrFail(order.getRestaurant().getId());
            PaymentMethod paymentMethod = paymentMethodService.findOrFail(order.getPaymentMethod().getId());


            order.getAddress().setCity(city);
            order.setCustomer(customer);
            order.setRestaurant(restaurant);
            order.setPaymentMethod(paymentMethod);

            if (restaurant.notAcceptPaymentMethod(paymentMethod)) {
                throw new BusinessException(String.format("Payment Method '%s' is not accept.",
                        paymentMethod.getDescription()));
            }
    }

    private void validItems(Order order) {
        order.getItems().forEach(items -> {
            Product product = productService.findOrFail(
                    order.getRestaurant().getId(), items.getProduct().getId());
            items.setOrder(order);
            items.setProduct(product);
            items.setUnitPrice(product.getPrice());
        });
    }

    public Order findOrFail(String orderCode) {
        return orderRepository.findByCode(orderCode)
                .orElseThrow(() -> new OrderNotFoundException(orderCode));
    }
}
