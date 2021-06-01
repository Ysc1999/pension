package io.renren;

import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class SysTest {
    @Autowired
    SysUserService sysUserService;
    @Test
    void sysTest(){
        Map<String,Object> params = new HashMap<>();
        params.put("params",null);
        PageUtils page = sysUserService.queryPage(params);
        System.out.println(page.getTotalPage());
    }
}
