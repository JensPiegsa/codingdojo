package telldontaskkata.repository;


import telldontaskkata.domain.Order;

public interface OrderRepository {
    void save(Order order);

    Order getById(int orderId);
}
