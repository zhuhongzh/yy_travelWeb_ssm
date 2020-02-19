package com.itzh.dao;

import com.itzh.domain.GoodsImg;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface IGoodsImgDao {
    @Select("select * from goodsimgs where goods_id=#{goodsId}")
    @Results(id="goodsimgMap",value = {
            @Result(id = true,property = "goodsImgId",column = "goodsimg_id"),
            @Result(property = "imgUrl",column = "img_url"),
            @Result(property = "goodsId",column = "goods_id")
    })
    GoodsImg findGoodsImgByGoodsId(int goodsId);
}
