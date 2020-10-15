package com.yuxiang.guli.service.trade.controller.api;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.common.base.result.ResultCodeEnum;
import com.yuxiang.guli.common.base.util.JWTUtils;
import com.yuxiang.guli.common.base.util.JwtInfo;
import com.yuxiang.guli.service.base.exception.GuliException;
import com.yuxiang.guli.service.trade.entity.Order;
import com.yuxiang.guli.service.trade.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Yuxiang
 * @create: 2020-07-03
 **/
@CrossOrigin
@Api(description = "课程章节管理控制器")
@RestController
@RequestMapping("/api/trade/order")
@Slf4j
public class ApiOrderController {

    @Autowired
    private OrderService orderService;


    @ApiOperation("新增订单")
    @PostMapping("auth/save/{courseId}")
    public R save(@PathVariable("courseId") String courseId, HttpServletRequest request) {

        JwtInfo infoFromJWT = JWTUtils.getInfoFromJWT(request);
        if (infoFromJWT == null) {
            throw new GuliException(ResultCodeEnum.LOGIN_AUTH);
        }

        String orderId = orderService.saveOrder(courseId, infoFromJWT.getId());

        return R.ok().data("orderId", orderId);
    }

    @ApiOperation("根据订单ID获取订单信息")
    @GetMapping("auth/get/{orderId}")
    public R get(@ApiParam("订单ID") @PathVariable("orderId") String orderId, HttpServletRequest request) {
        JwtInfo infoFromJWT = JWTUtils.getInfoFromJWT(request);
        Order order = orderService.getOrderById(orderId, infoFromJWT.getId());
        return R.ok().data("item", order);
    }


    @ApiOperation("判断课程是否购买")
    @GetMapping("auth/is-buy/{courseId}")
    public R isBuy(@ApiParam("订单ID") @PathVariable("courseId") String courseId, HttpServletRequest request) {
        JwtInfo infoFromJWT = JWTUtils.getInfoFromJWT(request);
        boolean isBuy = orderService.isBuyByCourseId(courseId, infoFromJWT.getId());
        return R.ok().data("isBuy", isBuy);
    }

}
