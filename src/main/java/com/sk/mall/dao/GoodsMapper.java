package com.sk.mall.dao;

import com.sk.mall.entity.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
    //    long countByExample(GoodsExample example);
//
//    int deleteByExample(GoodsExample example);
//
    int deleteById(Integer id);
//
//    int insert(Goods record);
//

    int insertSelective(Goods record);

//
//    List<Goods> selectByExampleWithBLOBs(GoodsExample example);
//

    List<Goods> getAllGoods();


    Goods getById(Integer goodsid);

    //
//    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);
//
//    int updateByExampleWithBLOBs(@Param("record") Goods record, @Param("example") GoodsExample example);
//
//    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);
//
    int updateByPrimaryKeySelective(Goods record);

    //
//    int updateByPrimaryKeyWithBLOBs(Goods record);
//
//    int updateByPrimaryKey(Goods record);
//
    List<Goods> getBySearchName(String keyWord);
//

    /**
     * 根据用户id来查找喜欢的商品
     *
     * @param userId 用户id
     * @return List<Goods>
     */
    List<Goods> selectFavByUserId(Integer userId);
}