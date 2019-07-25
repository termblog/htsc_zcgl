package com.htsc.zcgl.dao;

import com.htsc.zcgl.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by admin on 2018/12/19.
 */
public interface TestRepository extends JpaRepository<Test, String> {

    Test findById(Integer id);

}
