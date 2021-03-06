package com.sk.mall.controller.font;


import com.sk.mall.entity.Goods;
import com.sk.mall.entity.ShopCart;
import com.sk.mall.entity.User;
import com.sk.mall.service.GoodsService;
import com.sk.mall.service.ShopCartService;
import com.sk.mall.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ShopCartService shopCartService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/addCart")
    public String addCart(ShopCart shopCart, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        //判断是否已经加入购物车
        ShopCart shopCart1 = shopCartService.selectCartByKey(new ShopCart(user.getId(), shopCart.getGoodsId()));
        if (shopCart1 != null) {
            return "redirect:/showcart";
        }
        //用户
        shopCart.setUserId(user.getId());
        //加入时间
        shopCart.setCateDate(new Date());
        shopCartService.addShopCart(shopCart);
        //返回到购物车页面
        return "redirect:/showcart";
    }

    @RequestMapping("/showcart")
    public String showCart(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        return "shopcart";
    }

    @RequestMapping("/cartjson")
    @ResponseBody
    public Msg getCart(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Msg.fail("请先登录");
        }
        //获取当前用户的购物车信息
        List<ShopCart> shopCart = shopCartService.selectByExample(user.getId());
        //获取购物车中的商品信息
        List<Goods> goodsAndImage = new ArrayList<>();
        for (ShopCart cart : shopCart) {
            Goods goods = goodsService.selectById(cart.getGoodsId());
            goods.setNum(cart.getGoodsNum());
            goodsAndImage.add(goods);
        }
        return Msg.success("查询成功").add("shopcart", goodsAndImage);
    }

    @RequestMapping(value = "/deleteCart/{goodsid}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteCart(@PathVariable("goodsid") Integer goodsId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Msg.fail("请先登录");
        }
        shopCartService.deleteByKey(new ShopCart(user.getId(), goodsId));
        return Msg.success("删除成功");
    }

    @RequestMapping("/update")
    @ResponseBody
    public Msg updateCart(Integer goodsId, Integer num, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Msg.fail("请先登录");
        }
        ShopCart shopCart = new ShopCart();
        shopCart.setUserId(user.getId());
        shopCart.setGoodsId(goodsId);
        shopCart.setGoodsNum(num);
        shopCartService.updateCartByKey(shopCart);
        return Msg.success("更新购物车成功");
    }
}
