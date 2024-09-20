package telldontaskkata.useCase;

import telldontaskkata.domain.Order;
import telldontaskkata.domain.OrderStatus;
import telldontaskkata.repository.OrderRepository;
import telldontaskkata.service.ShipmentService;

import static telldontaskkata.domain.OrderStatus.CREATED;
import static telldontaskkata.domain.OrderStatus.REJECTED;
import static telldontaskkata.domain.OrderStatus.SHIPPED;

public class OrderShipmentUseCase {
    private final OrderRepository orderRepository;
    private final ShipmentService shipmentService;

    public OrderShipmentUseCase(OrderRepository orderRepository, ShipmentService shipmentService) {
        this.orderRepository = orderRepository;
        this.shipmentService = shipmentService;
    }

    public void run(OrderShipmentRequest request) {
        final Order order = orderRepository.getById(request.getOrderId());

        if (order.getStatus().equals(CREATED) || order.getStatus().equals(REJECTED)) {
            throw new OrderCannotBeShippedException();
        }

        if (order.getStatus().equals(SHIPPED)) {
            throw new OrderCannotBeShippedTwiceException();
        }

        shipmentService.ship(order);

        order.setStatus(OrderStatus.SHIPPED);
        orderRepository.save(order);
    }
}
