<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
  <head>
    <meta charset="UTF-8">
    <script>var jsCtx = "${ctx}"</script>
    <title>党建工建知识有奖问答</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" href="${ctx}/resources/css/base.css">
    <link rel="stylesheet" href="${ctx}/resources/css/index.css">
  </head>
  <body>
    <div class="container">
      <img class="Tlogo" src="../resources/images/index/Tlogo.png" alt="">
      <p class="Btitle">党建工建知识有奖问答</p>
      <p class="Twords">参加有奖问答活动</p>
      <p class="Twords">全部答对有机会获得国内手机流量</p>
      <p class="Twords">每日流量奖励有限，先到先得！</p>
      <a href="##" class="answerBtn js-answerBtn">开始答题</a>
      <div class="rulesMain">
        <p class="rulesTitle">活动规则</p>
        <div class="rules">
          ${activity.activityRule}
        </div>
      </div>
      <div class="participant clearfix">
        <li><img src="${ctx}/resources/images/index/mobile1.png" alt=""></li>
        <li><img src="${ctx}/resources/images/index/mobile2.png" alt=""></li>
        <li><img src="${ctx}/resources/images/index/mobile3.png" alt=""></li>
      </div>
      <p class="foot">由中移全通系统集成有限公司提供技术支持 </p>
      <!-- 开始答题弹窗 -->
      <div class="maskbody hide">
        <div class="prompt">
          <p></p>
          <button class="probtn js-proBtn">确认</button>
        </div>
      </div>
      <script src="${ctx}/resources/lib/jquery.min.js" charset="utf-8"></script>
      <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js" charset="utf-8"></script>
      <script src="${ctx}/resources/js/comment.js" charset="utf-8"></script>
      <script src="${ctx}/resources/js/index.js" charset="utf-8"></script>
    </div>
  </body>
</html>
