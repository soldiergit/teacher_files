package com.teacherfiles.models.sys.service.matchAnnex;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.MatchAnnexEntity;

import java.util.List;

/**
 * @author soldier
 * @title: MatchAnnexService
 * @projectName teacher_files
 * @date 19-7-22下午7:15
 * @Email： 583403411@qq.com
 * @description:
 */
public interface MatchAnnexService {

    /**
     * 添加
     * @param matchAnnexEntity
     */
    public void save(MatchAnnexEntity matchAnnexEntity);

    /**
     * 删除
     * @param matchAnnexEntity
     */
    public void delete(MatchAnnexEntity matchAnnexEntity);

    /**
     * 修改
     * @param matchAnnexEntity
     */
    public void update(MatchAnnexEntity matchAnnexEntity);

    /**
     * 查询
     * @param matchAnnexEntity
     * @return
     */
    public MatchAnnexEntity findById(MatchAnnexEntity matchAnnexEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<MatchAnnexEntity> pageBean);


    /**
     * 根据赛事查询所有附件
     * @param matchId
     * @return
     */
    public List<MatchAnnexEntity> findByMatchId(Integer matchId);

    /**
     * 查询
     * @return
     */
    public List<MatchAnnexEntity> findAll();

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
