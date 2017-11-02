<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>活动数据分析</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/base.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/analyData.css">
  </head>
  <body>
    <div class="analyContainer">
      <div class="dataTab clearfix">
          <a href="${ctx}/activityAnalyData" class="active">活动数据分析</a>
          <a href="${ctx}/exchangeSet/exchangeSet">活动设置</a>
      </div>
      <p class="p20 endTime">数据截止 ${showTime}</p>
      <div class="tableMain">
        <ul class="tableUl tableUl1 clearfix">
          <li>活动访问量</li>
          <li>完成答题人数</li>
          <li>获奖人数</li>
        </ul>
        <ul class="tableUl clearfix">
          <li> ${visitCount}</li>
          <li>${map.overanswercount}</li>
          <li>${map.count}</li>
        </ul>
      </div>
      <div class="bottomBgcolor clearfix">
        <div class="contrast clearfix">
          <p class="contrastLeft fl">各省数据对比</p>
          <div class="contrastRight fr">
            <dl class="contrastlist clearfix">
              <dt class="winNum"></dt>
              <dd class="fl">获奖人数</dd>
            </dl>
            <dl class="contrastlist clearfix">
              <dt class="actNum"></dt>
              <dd class="fl">完成答题人数</dd>
            </dl>
          </div>
        </div>
        <div class="percentage1 percentage clearfix">
          <ul class="perLeft fl">
          <c:forEach items="${map.list}" var="analysis" varStatus="index">
            <li>${analysis.privince}</li>
            </c:forEach>
          </ul>
          <ul class="perRight fr">
          <c:forEach items="${map.list}" var="analysis">
            <li style="width:<fmt:formatNumber value="${analysis.overanswercount/map.maxCount}" type="percent"/>">
              <span style="width:<fmt:formatNumber value="${analysis.count/analysis.overanswercount}" type="percent"/>">${analysis.count}</span>
              ${analysis.overanswercount}
            </li>
            </c:forEach>
          </ul>
        </div>
        <a href="##" class="openAll js-openAll fr">显示全部省份</a>
      </div>
    </div>
    <script src="${ctx}/resources/lib/jquery.min.js" charset="utf-8"></script>
    <script>
      $(function(){
        $(document).on('click','.js-openAll',function(){
          if($('.percentage1').hasClass('percentage')){
            $('.percentage1').removeClass('percentage')
            $(this).text("收起全部省份")
          }else{
            $('.percentage1').addClass('percentage')
            $(this).text("显示全部省份")
          }
        })
      })
    </script>
  </body>
</html>
