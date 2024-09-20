package telldontaskkata.doubles;

import telldontaskkata.domain.Order;
import telldontaskkata.service.ShipmentService;

public class TestShipmentService implements ShipmentService {
    private Order shippedOrder = null;

    public Order getShippedOrder() {
        return shippedOrder;
    }

    @Override
    public void ship(Order order) {
        this.shippedOrder = order;
    }
}
