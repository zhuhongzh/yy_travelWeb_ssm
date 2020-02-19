package com.itzh.service.impl;

import com.itzh.dao.IRemarkDao;
import com.itzh.domain.Remark;
import com.itzh.service.IRemarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("remarkService")
public class RemarkServiceImpl implements IRemarkService {

    @Resource(name = "remarkDao")
    private IRemarkDao remarkDao;

    @Override
    public List<Remark> findRemarkByRand() {
        return remarkDao.findRemarkByRand();
    }

    @Override
    public Remark findRemarkById(int id) {
        return remarkDao.findRemarkById(id);
    }

    @Override
    public Remark saveRemark(Remark remark) {
        remarkDao.saveRemark(remark);
        int id = remark.getRemarkId();
        return remarkDao.findRemarkById(id);
    }
}
