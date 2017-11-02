<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
  <head>
    <meta charset="UTF-8">
    <script>var jsCtx = "${ctx}"</script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>活动数据分析</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/base.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/analyData.css">
  </head>
  <body class="laydate_body">
    <div class="analyContainer">
      <div class="dataTab clearfix">
          <a href="${ctx}/activityAnalyData">数据流量分析</a>
          <a href="${ctx}/exchangeSet/exchangeSet" class="active">活动设置</a>
      </div>
      <div class="activeBox">
          <div class="actTime"><span>活动时间</span><u class="js-timeset"></u><a href="javascript:;" class="bcss js-bcss" style="display:none">保存</a></div>
          <div class="timeSetting"><span class="laydate-icon" id="start">${stratDate}</span>至<span class="laydate-icon js-end" id="end">${endDate}</span></div>
          <div class="actTime"><span>活动规则</span><u class="js-hdgz"></u></div>
          <div class="acticontent js-acticontent">${ActivityRule}
          </div>
          <div class="ktiao"></div>
          <div class="actTime"><span>每日流量奖励数量控制</span></div>
          <p>已发放流量奖品数量：<span>${changeSum}</span> </p>
          <div class="tableBox">
              <ul class="ulTh clearfix"><li>日期</li><li>当日奖品数量</li><li>奖品剩余数量</li></ul>
            <c:forEach items="${list}" var="ActivitySet">
              <ul class="ultd clearfix"><li class="jsrq" data-time="${ActivitySet.getData()}">${ActivitySet.getShowTime()}</li><li><span>${ActivitySet.getSum()}</span><u class="js-xiu"></u></li><li class="jqsy">${ActivitySet.getOverplus()}</li></ul>
            </c:forEach>
          </div>
          <div class="footbotm">当前用户：<span>${User.getName()}</span><a href="${ctx}/pages/login.jsp">退出登录</a></div>
      </div>
      <div class="mask">
        <div class="tipsbor" style="display:none">
          <p>设置奖励数量</p>
          <p class="p2">当前日期：<span>x月x日</span></p>
          <input type="text">
          <a href="javascript:;" class="js-qd">确定</a>
        </div>
        <div class="modifyRule" style="display:none">
          <p>编辑活动规则</p>
          <textarea></textarea>
          <a href="javascript:;" class="js-gzqd">确定</a>
        </div>
        <div class="tipstitle js-tipstitle" style="display:none">请输入10的倍数</div>
      </div>
    </div>
    <input type="hidden" class="js-riqi">
    <script src="../resources/lib/jquery.min.js" charset="utf-8"></script>
    <script src="../resources/lib/laydate.js"></script>
    <script>
      var start = {
        elem: '#start',
        format: 'YYYY年MM月DD日',
        isclear: false, //是否显示清空
        istoday: false, //是否显示今天
        choose: function(datas){
           end.min = datas; //开始日选好后，重置结束日的最小日期
           end.start = datas //将结束日的初始值设定为开始日
        }
      };
      var end = {
        elem: '#end',
        format: 'YYYY年MM月DD日',
        isclear: false, //是否显示清空
        istoday: false, //是否显示今天
        fixed: true,
        choose: function(datas){
          start.max = datas; //结束日选好后，重置开始日的最大日期
        }
      };
      // laydate(start);
      laydate(end);
      // 打开时间保存
      $(document).on('click','.js-timeset',function(){
        $('.js-bcss').show();
      });
      // 点击保存
      // 传结束时间
      $(document).on('click','.js-bcss',function(){
        var endTime = $('#end').html();
        var post = $.post(jsCtx + '/exchangeSet/exchangeSet1', {endTime:endTime});
            post.done(function(data){
              if (data && data.code === 1) {
                window.location.reload();
              }else{
                console.log(data)
              }
            });
        $('.js-bcss').hide();
/*         window.location.reload(); */
      });
      // 打开奖品设置弹窗
      $(document).on('click','.js-xiu',function(){
        // 日期
        var day = $(this).parents('li').siblings('li.jsrq').text();
    	  var time = $(this).parents('li').siblings('li.jsrq').data('time');
        // 当日奖品数量
        var maxnum = $(this).siblings('span').text();
        // 奖品剩余数量
        var minnum = $(this).parents('li').siblings('li.jqsy').text();
        $('.js-riqi').val(time);

        // 修改当前弹窗日期
        $('.tipsbor p.p2 span').html(day);
        // 修改当日奖品数量
        $('.tipsbor input').val(maxnum)
        $('.mask').show();
        $('.tipsbor').show();
      });
        // 关闭奖品设置弹窗
      $(document).on('click','.js-qd',function(){
        var Time = $('.js-riqi').val();
        var num = $(this).siblings('input').val();
        if(num % 10 != 0){
        	$('.js-tipstitle').show();
        	var timer1 = window.setTimeout(function(){
        		$('.js-tipstitle').hide();
        	},2000)
        	return;
        }
        var post = $.post(jsCtx + '/exchangeSet/exchangeSet3', {time:Time,sum:num});
	        post.done(function(data){
	            if (data && data.code === 1) {
	              console.log('修改成功')
	              window.location.reload();
	            }else{
	              console.log(data)
	            }
	          });
	    window.clearTimeout(timer1)
        $('.mask').hide();
        $('.tipsbor').hide();
      });
      // 打开规则设置
      $(document).on('click','.js-hdgz',function(){
        var hdgz = $('.acticontent').html();
        $('.modifyRule textarea').val(hdgz);
        $('.mask').show();
        $('.modifyRule').show();
      });
      // 关闭规则设置
      $(document).on('click','.js-gzqd',function(){
        var hdgz =  $('.modifyRule textarea').val();
            var post = $.post(jsCtx + '/exchangeSet/exchangeSet2', {rule:hdgz});
            post.done(function(data){
              if (data && data.code === 1) {
                window.location.reload();
              }else{
                console.log(data)
              }
            });
        $('.modifyRule').hide();
        $('.mask').hide();
        /*  */
      });
      function bianse(){
    	  var acticontent = $('.js-acticontent').html();
    	  if(acticontent.split('2.').length != 1){
    		  var jie = acticontent.split('2.');
    		  var pin = '';
    		  $.each(jie,function(i){
    			  if(i==0){
    				  jie = jie[i].replace(jie[i],"<p style='color:#fffc00;'>"+jie[i]+"</p>");
    			  }else{
    				  pin = acticontent.split('2.')[i];
    			  }
    		  })
    		  var html = jie +="2."+pin;
    		  $('.js-acticontent').html(html);
    	  }
      }
      bianse();
    </script>
  </body>
</html>
