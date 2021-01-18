package com.wugui.datax.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datax.admin.entity.LoginUser;
import com.wugui.datax.admin.service.DatasourceQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 查询数据库表名，字段的控制器
 *
 * @author jingwk
 * @ClassName MetadataController
 * @Version 2.1.2
 * @since 2020/05/31 20:48
 */
@RestController
@Api(tags = "登陆")
public class LoginController extends BaseController {

    @Autowired
    private DatasourceQueryService datasourceQueryService;

//    /**
//     *
//     * @param loginUser
//     * @return
//     */
//    @PostMapping("/login")
//    @ApiOperation("登陆")
//    public R login(@RequestBody LoginUser loginUser) throws IOException {
//        return success(loginUser);
//    }
}
