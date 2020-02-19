package com.itzh.service;

import org.apache.ibatis.annotations.Param;

public interface IRemarkImgService {
    void saveRemarkImg(String remarkImgUrl,int remarkId);
}
