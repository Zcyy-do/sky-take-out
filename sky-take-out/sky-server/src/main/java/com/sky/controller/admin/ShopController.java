package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺管理")
@Slf4j
public class ShopController {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 修改店铺状态
     *
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    public Result setStatus(@PathVariable("status") Integer status) {
        log.info("修改店铺状态：{}", status == 1 ? "营业中" : "打烊中");
        redisTemplate.opsForValue().set("shopStatus", status);
        return Result.success();
    }

    /**
     * 获取店铺状态
     *
     * @return
     */
    @GetMapping("/status")
    public Result<Integer> getStatus() {
        Integer shopStatus = (Integer) redisTemplate.opsForValue().get("shopStatus");
        log.info("获取店铺状态：{}", shopStatus == 1 ? "营业中" : "打烊中");
        return Result.success(shopStatus);
    }


}
