package com.meteorsky.datadesign.Service;

import com.meteorsky.datadesign.Model.Newspaper;
import com.meteorsky.datadesign.Model.NewspaperClass;
import com.meteorsky.datadesign.Repository.NewspaperClassRepository;
import com.meteorsky.datadesign.Repository.NewspaperRepository;
import com.meteorsky.datadesign.Utils.STATUS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewspaperService {

    @Autowired
    private NewspaperRepository newspaperRepository;

    @Autowired
    private NewspaperClassRepository newspaperClassRepository;

    public List<Newspaper> getAll(){
        return newspaperRepository.findAll();
    }

    public int add(String id,String name, String publisher, String publishBetween, float price, String content,int newspaperclass){
        try {
            Newspaper newspaper = new Newspaper();
            newspaper.setId(id);
            newspaper.setName(name);
            newspaper.setPublisher(publisher);
            newspaper.setPublishBetween(publishBetween);
            newspaper.setPrice(price);
            newspaper.setContent(content);
            newspaper.setNewspaperClass(newspaperClassRepository.findById(newspaperclass));
            newspaperRepository.save(newspaper);
            return STATUS.SUCCESS;
        }catch (Exception e){
            return STATUS.FAIL;
        }
    }

    public int update(String id,String name, String publisher, String publishBetween, float price, String content,int newspaperclass){
        try {
            Newspaper newspaper = newspaperRepository.findById(id);
            if(newspaper == null)
                return STATUS.FAIL;
            newspaper.setName(name);
            newspaper.setPublisher(publisher);
            newspaper.setPublishBetween(publishBetween);
            newspaper.setPrice(price);
            newspaper.setContent(content);
            newspaper.setNewspaperClass(newspaperClassRepository.findById(newspaperclass));
            newspaperRepository.save(newspaper);
            return STATUS.SUCCESS;
        }catch (Exception e){
            return STATUS.FAIL;
        }
    }

    public int delete(String id){
        try {
            Newspaper newspaper = newspaperRepository.findById(id);
            if(newspaper == null)
                return STATUS.FAIL;
            newspaperRepository.delete(newspaper);
            return STATUS.SUCCESS;
        }catch (Exception e){
            return STATUS.FAIL;
        }
    }

    public List<String> getAllId(){
        return newspaperRepository.findAllId();
    }

}
