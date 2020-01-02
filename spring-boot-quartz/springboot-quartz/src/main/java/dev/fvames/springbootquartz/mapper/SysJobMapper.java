package dev.fvames.springbootquartz.mapper;

import dev.fvames.springbootquartz.entity.SysJob;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * TODO 类描述
 *
 * @author
 * @version 2019/12/31 17:51
 */
@Repository
public interface SysJobMapper {

	/**
	 * 获取任务数量
	 *
	 * @param
	 */
	int getJobCount();

	/**
	 * 查询定时任务列表
	 *
	 * @param map
	 */
	List<SysJob> querySysJobList(Map<String, String> map);

	/**
	 * 新增定时任务
	 *
	 * @param record
	 */
	int insertSelective(SysJob record);

	/**
	 * 删除定时任务
	 *
	 * @param id
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 根据主键查询定时任务详情
	 *
	 * @param id
	 */
	SysJob selectByPrimaryKey(Integer id);

	/**
	 * 根据bean查询定时任务详情
	 *
	 * @param
	 */
	SysJob selectByBean(SysJob bean);

	/**
	 * 更新定时任务详情
	 *
	 * @param
	 */
	int updateByPrimaryKeySelective(SysJob bean);

}
