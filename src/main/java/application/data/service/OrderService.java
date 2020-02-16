package application.data.service;

import application.constant.StatusConstant;
import application.data.model.Order;
import application.data.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired

    private static final Logger logger = LogManager.getLogger(OrderService.class);
    public void addNewOrder(Order order){
        orderRepository.save(order);
    }
    @Transactional
    public void addNewListOrder(List<Order> orderList){
        orderRepository.save(orderList);
    }
    public Order findOne(int id){
        return orderRepository.findOne(id);
    }
    public boolean updateOrder(Order order){
        try{
            orderRepository.save(order);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }
    public boolean deleteOrder(int id){
        try{
            orderRepository.delete(id);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }
    public List<Order> findOrderByGuidOrUserName(String guid, String userName) {
        return orderRepository.findOrderByGuidOrUserName(guid,userName);
    }
    public Page<Order> getListOrderByStatusUnconfirmed(Pageable page,Integer sort){
        return  orderRepository.getListOrderByStatusUnconfirmed(page,sort);
    }
    public int getAmountOrderUnconfirm(){
        return orderRepository.getAmountOrderUnconfirm();
    }
}
