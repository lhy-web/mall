package com.sk.mall.controller.font;


import com.sk.mall.entity.Goods;
import com.sk.mall.entity.User;
import com.sk.mall.service.CateService;
import com.sk.mall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private CateService cateService;

    @Autowired
    private GoodsService goodsService;


    @RequestMapping("/")
    public String showAdmin(Model model, HttpSession session) {
        Integer userid;
        User user = (User) session.getAttribute("user");
        if (user == null) {
            userid = null;
        } else {
            userid = user.getId();
        }

        //数码分类
        List<Goods> digGoods = getCateGoods("数码", userid);
        model.addAttribute("digGoods", digGoods);

        //家电
        List<Goods> houseGoods = getCateGoods("家电", userid);
        model.addAttribute("houseGoods", houseGoods);

        //服饰
        List<Goods> colGoods = getCateGoods("服饰", userid);
        model.addAttribute("colGoods", colGoods);

        //书籍
        List<Goods> bookGoods = getCateGoods("书籍", userid);
        model.addAttribute("bookGoods", bookGoods);

        return "main";
    }


    @RequestMapping("/main")
    public String showAllGoods(Model model, HttpSession session) {
        Integer userid;
        User user = (User) session.getAttribute("user");
        if (user == null) {
            userid = null;
        } else {
            userid = user.getId();
        }
        //数码分类
        List<Goods> digGoods = getCateGoods("数码", userid);
        model.addAttribute("digGoods", digGoods);
        //家电
        List<Goods> houseGoods = getCateGoods("家电", userid);
        model.addAttribute("houseGoods", houseGoods);
        //服饰
        List<Goods> colGoods = getCateGoods("服饰", userid);
        model.addAttribute("colGoods", colGoods);
        //书籍
        List<Goods> bookGoods = getCateGoods("书籍", userid);
        model.addAttribute("bookGoods", bookGoods);

        return "main";
    }

    public List<Goods> getCateGoods(String cate, Integer userid) {
        List<Goods> goodsAndImage = new ArrayList<>();
//        //查询分类
//        CategoryExample digCategoryExample = new CategoryExample();
//        digCategoryExample.or().andCatenameLike(cate);
//        List<Category> digCategoryList = cateService.selectByExample(digCategoryExample);
//
//        if (digCategoryList.size() == 0) {
//            return null;
//        }
//
//        //查询属于刚查到的分类的商品
//        GoodsExample digGoodsExample = new GoodsExample();
//        List<Integer> digCateId = new ArrayList<>();
//        for (Category tmp : digCategoryList) {
//            digCateId.add(tmp.getCateid());
//        }
//        digGoodsExample.or().andCategoryIn(digCateId);
//
//        List<Goods> goodsList = goodsService.selectByExampleLimit(digGoodsExample);
//
//
//        //获取每个商品的图片
//        for (Goods goods : goodsList) {
//            //判断是否为登录状态
//            if (userid == null) {
//                goods.setFav(false);
//            } else {
//                Favorite favorite = goodsService.selectFavByKey(new FavoriteKey(userid, goods.getGoodsid()));
//                goods.setFav(favorite != null);
//            }
//
//            List<ImagePath> imagePathList = goodsService.findImagePath(goods.getGoodsid());
//            goods.setImagePaths(imagePathList);
//            goodsAndImage.add(goods);
//        }
        return goodsAndImage;
    }
}
