package application.data.service;

import application.data.model.Order;
import application.data.model.OrderProduct;
import application.data.repository.OrderProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class OrderProductService {
    @Autowired
    OrderProductRepository orderProductRepository;
    private static final Logger logger = LogManager.getLogger(OrderService.class);
    public void addNewOrder(OrderProduct order){
        orderProductRepository.save(order);
    }
    @Transactional
    public void addNewListOrder(List<OrderProduct> orderList){
        orderProductRepository.save(orderList);
    }
    public OrderProduct findOne(int id){
        return orderProductRepository.findOne(id);
    }
    public boolean updateOrder(OrderProduct order){
        try{
            orderProductRepository.save(order);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }
    public boolean deleteOrder(int id){
        try{
            orderProductRepository.delete(id);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }
}
