package com.meteorsky.datadesign.Service;

import com.meteorsky.datadesign.Model.NewspaperClass;
import com.meteorsky.datadesign.Repository.NewspaperClassRepository;
import com.meteorsky.datadesign.Utils.STATUS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewspaperClassService {

    @Autowired
    private NewspaperClassRepository newspaperClassRepository;

    public List<NewspaperClass> getAll() {
        return newspaperClassRepository.findAll();
    }

    public int add(int id, String name) {
        try {
            NewspaperClass newspaperClass = new NewspaperClass();
            newspaperClass.setId(id);
            newspaperClass.setName(name);
            newspaperClassRepository.save(newspaperClass);
            return STATUS.SUCCESS;
        } catch (Exception e) {
            return STATUS.FAIL;
        }
    }

    public int update(int id,String name) {
        try {
            NewspaperClass newspaperClass = newspaperClassRepository.findById(id);
            if(newspaperClass == null)
                return STATUS.FAIL;
            newspaperClass.setName(name);
            newspaperClassRepository.save(newspaperClass);
            return STATUS.SUCCESS;
        } catch (Exception e) {
            return STATUS.FAIL;
        }
    }

    public int delete(int id) {
        try {
            NewspaperClass newspaperClass = newspaperClassRepository.findById(id);
            if(newspaperClass == null)
                return STATUS.FAIL;
            newspaperClassRepository.delete(newspaperClass);
            return STATUS.SUCCESS;
        } catch (Exception e) {
            return STATUS.FAIL;
        }
    }

    public List<Integer> getAllId(){
        return newspaperClassRepository.findAllId();
    }

}
