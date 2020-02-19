package com.itzh.dao;

import com.itzh.domain.Goods;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

public interface IGoodsDao {
    /**
     * 根据景点id查找goods
     * @param id
     * @return
     */
    @Select("select * from goods where goods_id in (select goods_id from goodstoscenery where scenery_id = #{id})")
    @Results(value = {
            @Result(id = true,property = "goodsId",column = "goods_id"),
            @Result(property = "goodsName",column = "goods_name"),
            @Result(property = "goodsImg",column = "goods_id",
            one = @One(select = "com.itzh.dao.IGoodsImgDao.findGoodsImgByGoodsId",
                    fetchType = FetchType.EAGER))
    })
    Goods findGoods(int id);

}
