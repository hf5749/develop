package com.cmcc.wxanswer.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.wxanswer.cache.CacheKey;
import com.cmcc.wxanswer.cache.CustomUSERMemcachedClient;
import com.cmcc.wxanswer.dao.ProblemDao;
import com.cmcc.wxanswer.model.Option;
import com.cmcc.wxanswer.model.Problem;
import com.cmcc.wxanswer.model.ProblemVO;
import com.cmcc.wxanswer.util.Page;



@Service("ProblemService")
public class ProblemService {
	@Autowired
	private ProblemDao pd;
	@Autowired
	private CustomUSERMemcachedClient customUSERMemcachedClient;
     //题目保存	
	@Transactional
	public int saveUser(Problem problem){
		Problem save =  pd.save(problem);
		if(save != null){
			return 1;
		}
		return 0;
	}
	//题查询
	@SuppressWarnings("unchecked")
	public Page<Problem> getProblemList(Integer index,Integer pagesize) {
        Page<Problem> page = (Page<Problem>) customUSERMemcachedClient.get(CacheKey.getProblemListKey());
		if(null == page){
			String jpql = "select o from Problem o order by o.id asc";
			List<Object> params = new ArrayList<Object>();
			page = pd.getAllPage(jpql, params, pagesize, index);
			customUSERMemcachedClient.set(CacheKey.getProblemListKey(), 24*60*60, page);
			return page;
		}
		return page;
		
	}
	//根据题目Id获取题目详情
	public Problem getProblem(Long problemId) {
		Problem problem = (Problem) customUSERMemcachedClient.get(CacheKey.getProblemKey(problemId));
		if(problem == null){
			String jpql = "select o from Problem o where o.id=?1";
			List<Object> params = new ArrayList<Object>();
			params.add(problemId);
			problem = pd.findAllList(jpql, params).get(0);
			customUSERMemcachedClient.set(CacheKey.getProblemKey(problemId), 24*60*60, problem);
			return problem;
		}
		return problem;	
	}
	//获取错题展示
	public List<Problem> getProblemList(List<Problem> list) {
		if(null != list){
			for (Problem problem : list) {
				List<Option> optionList = new ArrayList<>();
				String[] res = problem.getOption().split(":;");
				for (int i = 0; i < res.length; i++) {
					Option option = new Option();
					switch (i) {
					case 0:
						option.setOptionName("A");
						option.setOptionValue(res[i]);
						optionList.add(option);
						break;
					case 1:
						option.setOptionName("B");
						option.setOptionValue(res[i]);
						optionList.add(option);
						break;
					case 2:
						option.setOptionName("C");
						option.setOptionValue(res[i]);
						optionList.add(option);
						break;
					case 4:
						option.setOptionName("D");
						option.setOptionValue(res[i]);
						optionList.add(option);
						break;
					case 5:
						option.setOptionName("E");
						option.setOptionValue(res[i]);
						optionList.add(option);
						break;
					case 6:
						option.setOptionName("F");
						option.setOptionValue(res[i]);
						optionList.add(option);
						break;
					default:
						option.setOptionName("H");
						option.setOptionValue(res[i]);
						optionList.add(option);
						break;
					}
				}
				problem.setOptions(optionList);
			}
		}
		
		return list;
	}
	//处理提交后的题目
	public ProblemVO getProblemVo(String problemIds,String answers) {
		Long rightCount = 0l;
		Long wrongCount = 0l;
		List<Long> wrongList = new ArrayList<>();
		List<Problem> list = new ArrayList<>();
		ProblemVO problemVo = new ProblemVO();
		if(!StringUtils.isBlank(problemIds)){
			String [] pros = problemIds.split(",");
			String [] ans = answers.split(",");
			for (int i = 0; i < pros.length; i++) {
				Problem problem = this.getProblem(Long.valueOf(pros[i]));
				if(problem.getRightanswer().equals(ans[i])){
					rightCount++;
					problem.setIsRight(0);
				}else{
					wrongCount++;
					problem.setIsRight(1);
					wrongList.add(Long.valueOf(i+1));
				}
				list.add(problem);
			}			
		}
		problemVo.setRightCount(rightCount);
		problemVo.setWrongCount(wrongCount);
		problemVo.setWrongProblems(wrongList);
		problemVo.setProblems(list);
		return problemVo;	
	}
}
