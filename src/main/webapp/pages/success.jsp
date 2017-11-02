<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
  <head>
    <meta charset="UTF-8">
    <title>党建工建知识有奖问答</title>
    <script>var jsCtx = "${ctx}"</script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/base.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/success.css">
  </head>
  <body>
    <div class="tipsBox">
      <p>恭喜您全部答对！</p>
      <p>输入手机号码，领取150M流量吧~</p>
    </div>
    <div class="phoneNum">
      <input type="text"  class="js-telNum" placeholder="请输入手机号码">
      <p class="telTips hiden">输入的手机号有误！</p>
    </div>
    <div class="lnvitation lnvitationfail">
      <a href="javascript:;" class="tryagBtn js-nSubmit">提交</a>
      <a href="javascript:;" class="inviBtn js-inviBtn">邀请好友答题</a>
    </div>
    <div class="posFoot">
      <p>党建工建有奖知识问答</p>
      <p class="numColor">凝聚共识增合力  喜迎党的十九大</p>
      <p>由中移全通系统集成有限公司提供技术支持</p>
    </div>
    <input type="text"  class="hide js-accessToken" data-accesstoken = "${accessToken}">
    <!-- 领取流量弹窗 -->
    <div class="maskbody hide">
      <div class="prompt">
        <p></p>
        <button class="probtn js-proBtn">确认</button>
      </div>
    </div>
    <!-- 引导分享 -->
    <div class="maskbody1 hide">
        <img src="${ctx}/resources/images/share.png" class="shareImg" alt="" />
    </div>
    <script src="${ctx}/resources/lib/jquery.min.js" charset="utf-8"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js" charset="utf-8"></script>
    <script src="${ctx}/resources/js/comment.js" charset="utf-8"></script>
    <script src="${ctx}/resources/js/success.js" charset="utf-8"></script>
  </body>
</html>
