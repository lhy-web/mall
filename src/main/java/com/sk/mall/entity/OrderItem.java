package com.sk.mall.entity;

public class OrderItem {
    private Integer id;

    private Integer orderId;

    private Integer goodsId;

    private Goods goods;

    private Integer num;


    public OrderItem() {
    }

    public OrderItem(Integer id, Integer orderId, Integer goodsId, Integer num) {
        this.id = id;
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", goods=" + goods +
                ", num=" + num +
                '}';
    }
}