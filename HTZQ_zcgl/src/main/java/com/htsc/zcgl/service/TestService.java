package com.htsc.zcgl.service;

import com.htsc.zcgl.dao.TestRepository;
import com.htsc.zcgl.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2018/12/19.
 */
@Service("testService")
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public List<Test> findAll() {
        return testRepository.findAll();
    }

    public void save(Test test) {
        this.testRepository.save(test);
    }

    public Test findOne(Integer id) {
        return this.testRepository.findById(id);
    }

    public void delete(Integer id) {
        Test test = new Test();
        test.setId(id);
        this.testRepository.delete(test);
    }

    public void edit(Test test) {
        Test mod = this.findOne(test.getId());
        mod.setName(test.getName());
        this.testRepository.save(mod);
    }
}
