package mapfood.service;

import com.google.maps.model.DirectionsResult;
import mapfood.model.*;
import mapfood.repository.ClientRepository;
import mapfood.repository.MotoboyRepository;
import mapfood.repository.OrderRepository;
import mapfood.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private MotoboyRepository motoBoyRepository;

    @Autowired
    private FindNearestService findNearestService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private DirectionsService directionsService;

    @Override
    public Order createOrder(String idClient, String idRestaurant, List<OrderItem> orderItemList) {

        Optional<Client> client = clientRepository.findById(Integer.valueOf(idClient));
        Optional<Restaurant> restaurant = restaurantRepository.findById(Integer.valueOf(idRestaurant));
        int totalQuantity = 0;

        if (client.isPresent() && restaurant.isPresent()) {
            Client clientFinded = client.get();
            Restaurant restaurantFinded = restaurant.get();

            for (OrderItem orderItem : orderItemList) {
                totalQuantity += orderItem.getQuantity();
            }

            if (!orderItemList.isEmpty() && totalQuantity <= 5) {

                Order order = new Order();
                order.setClient(clientFinded);
                order.setOrderItems(orderItemList);
                order.setRestaurant(restaurantFinded);
                order.setOrderStatus(OrderEnum.NOVO);

                return orderRepository.save(order);
            }
        }
        return null;
    }
    
    @Override
    public List<DirectionsResult> getOrderDirections(String idMotoBoy, String idOrder) {
        List<DirectionsResult> routes = new ArrayList<>();
        List<Double> motoboyLocation;
        List<Double> restaurantLocation;
        List<Double> clienteLocation;
    
        if(!idMotoBoy.isEmpty() && !idOrder.isEmpty()){
    
            Optional<Motoboy> motoboy = this.motoBoyRepository.findById(Integer.parseInt(idMotoBoy));
            Optional<Order> order = this.orderRepository.findById(idOrder);
            
            if(motoboy.isPresent() && order.isPresent()){
                motoboyLocation = motoboy.get().getLoc().getCoordinates();
                restaurantLocation = order.get().getRestaurant().getLoc().getCoordinates();
            // rout motoboy to restaurant
                StringBuilder motoSb = new StringBuilder();
                StringBuilder restSb = new StringBuilder();
                if(motoboyLocation!=null && motoboyLocation.size()==2 && restaurantLocation!=null && restaurantLocation.size()==2){
                    motoSb.append(motoboyLocation.get(0)+","+motoboyLocation.get(1));
                    restSb.append(restaurantLocation.get(0)+","+restaurantLocation.get(1));
                }
                routes.add(this.directionsService.getDirections(motoSb.toString(), restSb.toString()));
                // route restaurant to client
                clienteLocation = order.get().getClient().getLoc().getCoordinates();
                StringBuilder clientSb = new StringBuilder();
                if(clienteLocation!=null && clienteLocation.size()==2){
                    clientSb.append(clienteLocation.get(0)+","+clienteLocation.get(1));
                }
                routes.add(this.directionsService.getDirections(restSb.toString(), clientSb.toString()));
            }
    
        
            
        }
        
        return routes;
    }
    
    
}
