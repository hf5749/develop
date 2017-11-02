package com.cmcc.wxanswer.controller.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cmcc.wxanswer.cache.CacheKey;
import com.cmcc.wxanswer.cache.CustomUSERMemcachedClient;
import com.cmcc.wxanswer.model.Analysis;
import com.cmcc.wxanswer.model.Problem;
import com.cmcc.wxanswer.model.ProblemVO;
import com.cmcc.wxanswer.service.AnalysisService;
import com.cmcc.wxanswer.service.ProblemService;
import com.cmcc.wxanswer.util.Page;


/**
 * @title 用于获取相关问题信息
 * @author HF
 * @version 2017年6月6日 上午10:44:17
 */
@Controller("ProblemContrller")
@RequestMapping(value="/problem")
public class ProblemContrller {
	private static Logger logger = LoggerFactory.getLogger(ProblemContrller.class);
	@Autowired
	private ProblemService ps;
	@Autowired
	private AnalysisService analysisService;
	@Autowired
	private CustomUSERMemcachedClient customUSERMemcachedClient;
	/**
	 * @title 用于获取题目列表
	 * @author HF
	 * @version 2017年6月6日 上午10:44:17
	 */
	@RequestMapping(value="problemList")
	public String getProblemList(HttpServletRequest request,Model model){
		//获取题目列表
		List<Problem> list = null;
		try {
			Page<Problem> page = ps.getProblemList(1, 12);
			if(null != page){
				list = page.getList();
				list = ps.getProblemList(list);
			}
		} catch (Exception e) {
			logger.debug("获取题目列表异常 "+e.getMessage());
		}
		model.addAttribute("problemList", list);
		model.addAttribute("count", list.size());
		return "/pages/answer";	
	}
	/**
	 * @title 题目提交处理
	 * @author HF
	 * @version 2017年6月6日 
	 */
	@RequestMapping(value="solveProblem")
	public String solveProblem(HttpServletRequest request,Model model){
		String problemIds = request.getParameter("problemIds");//问题id
		String answers = request.getParameter("answers");//问题回答
		ProblemVO vo = new ProblemVO();
		
			String openId = (String) request.getSession().getAttribute("openId");
			String privince = (String) customUSERMemcachedClient.get(CacheKey.getAddressKey(openId));
			//更新完成答题的人数
			try {
				//判断省份是否存在
				boolean res = analysisService.privinceIsExist(privince);
				if(res){
					//存在的话更新对应答题人数
					int updateRes = analysisService.updateOverAnswerCount(privince);
					if(updateRes > 0){
						logger.debug("更新答题人数成功 ");
					}else{
						logger.debug("更新答题人数失败 ==>privince="+privince);
					}
				}else{
				   //不存在新建一条记录
					Analysis analysis = new Analysis();
					analysis.setPrivince(privince);
					analysis.setCount(0l);
					analysis.setOveranswercount(0l);
					int saveRes = analysisService.saveAnalysis(analysis);
					if(saveRes > 0){
						logger.debug("新增一条省份数据成功");
					}else{
						logger.debug("新增一条省份数据失败==>analysis="+analysis.toString());
					}
				}
						
			} catch (Exception e) {
				logger.debug("更新答题人数异常 "+e.getMessage()+"==>privince="+privince);
			}
			
		try {
		    //处理提交题目
			vo = ps.getProblemVo(problemIds, answers);
		} catch (Exception e) {
			logger.debug("获取题目列表异常 "+e.getMessage());
		}
		model.addAttribute("vo", vo);
		if(vo.getWrongCount() > 0 || vo.getRightCount() < vo.getProblems().size()){
			String resStr = "";
			List<Long> resWrong = vo.getWrongProblems();
			for (int i = 0; i < vo.getWrongProblems().size(); i++) {
				if(i != vo.getWrongProblems().size()-1){
					resStr = resStr+resWrong.get(i)+",";
				}else{
					resStr = resStr+resWrong.get(i);
				}
			}
			model.addAttribute("resStr", resStr);
			return "/pages/failanswer";
		}else{
			String accessToken = UUID.randomUUID().toString();
			request.getSession().setAttribute("accessToken", accessToken);
			model.addAttribute("accessToken", accessToken);
		   return "/pages/success";	
		}
	}
}
