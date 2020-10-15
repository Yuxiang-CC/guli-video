package com.yuxiang.guli.service.trade.service;

import com.yuxiang.guli.service.trade.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author Yuxiang
 * @since 2020-07-02
 */
public interface OrderService extends IService<Order> {

    String saveOrder(String courseId, String memberId);

    Order getOrderById(String orderId, String memberId);

    boolean isBuyByCourseId(String courseId, String memberId);
}
