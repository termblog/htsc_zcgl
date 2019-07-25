package com.htsc.zcgl.controller;

import com.htsc.zcgl.model.Test;
import com.htsc.zcgl.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Swagger 配置 
 * @author wangdancheng on 2019/7/14.
 * http://owa.htsc.com.cn:8081/
 * http://122.96.150.163:8081/
 */
@Api(value = "Swagger Rest风格", description = "配置验证")
@RestController
@RequestMapping("api/swagger")
public class SwaggerTestController {
    @Autowired
    private TestService testService;

    @ApiOperation(value="获取用户列表")
    @GetMapping("/getList")
    public List<Test> getList() {
        List<Test> r = testService.findAll();
        return r;
    }



    @ApiOperation(value="删除用户")
//    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "integer")
    @DeleteMapping("/{id}")
    public String delete(@ApiParam(value = "用户Id", required = true) @PathVariable Integer id) {
        testService.delete(id);
        return "success";
    }

}
