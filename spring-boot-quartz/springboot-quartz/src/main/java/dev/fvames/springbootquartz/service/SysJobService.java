package dev.fvames.springbootquartz.service;

import dev.fvames.springbootquartz.entity.SysJob;
import dev.fvames.springbootquartz.mapper.SysJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * TODO 类描述
 *
 * @author
 * @version 2019/12/31 17:55
 */
@Service
public class SysJobService {
	@Autowired
	private SysJobMapper sysJobMapper;

	public int getJobCount() {
		return sysJobMapper.getJobCount();
	}

	public List<SysJob> querySysJobList(HashMap<String, String> map) {
		return sysJobMapper.querySysJobList(map);
	}

	public int insertSelective(SysJob record) {
		return sysJobMapper.insertSelective(record);
	}

	public int deleteByPrimaryKey(Integer id) {
		return sysJobMapper.deleteByPrimaryKey(id);
	}

	public SysJob selectByPrimaryKey(Integer id) {
		return sysJobMapper.selectByPrimaryKey(id);
	}

	public SysJob selectByBean(SysJob bean) {
		return sysJobMapper.selectByBean(bean);
	}

	public int updateByPrimaryKeySelective(SysJob bean) {
		return sysJobMapper.updateByPrimaryKeySelective(bean);
	}
}
