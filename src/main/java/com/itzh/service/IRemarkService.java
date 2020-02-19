package com.itzh.service;

import com.itzh.domain.Remark;
import com.itzh.domain.Scenery;

import java.util.List;

public interface IRemarkService {
    List<Remark> findRemarkByRand();

    Remark findRemarkById(int id);

    Remark saveRemark(Remark remark);
}
