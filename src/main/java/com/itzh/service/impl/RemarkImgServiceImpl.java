package com.itzh.service.impl;

import com.itzh.dao.IRemarkImgDao;
import com.itzh.service.IRemarkImgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("remarkImgService")
public class RemarkImgServiceImpl implements IRemarkImgService {
    @Resource(name = "remarkImgDao")
    private IRemarkImgDao remarkImgDao;

    @Override
    public void saveRemarkImg(String remarkImgUrl, int remarkId) {
        remarkImgDao.saveRemarkImg(remarkImgUrl,remarkId);
    }
}
