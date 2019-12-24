package application.data.service;

import application.data.model.Publisher;
import application.data.repository.PubliserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PubliserService {
    @Autowired
    PubliserRepository publiserRepository;
    private static final Logger logger = LogManager.getLogger(PubliserService.class);
    public void addNewPubliser(Publisher publisher){
        publiserRepository.save(publisher);
    }
    @Transactional
    public void addNewListPubliser(List<Publisher> publisher){
        publiserRepository.save(publisher);
    }
    public  Publisher findOne(int id){
      return   publiserRepository.findOne(id);
    }
    public boolean updatePubliser(Publisher publisher){
        try{
            publiserRepository.save(publisher);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }
    public boolean deletePubliser(int id){
        try{
            publiserRepository.delete(id);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }
}
