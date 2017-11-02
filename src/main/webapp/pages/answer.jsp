<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
  <head>
    <meta charset="UTF-8">
    <title>党建工建知识有奖问答</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <script>var jsCtx = "${ctx}"</script>
    <link rel="stylesheet" href="${ctx}/resources/css/base.css">
    <link rel="stylesheet" href="${ctx}/resources/css/answer.css">
  </head>
  <body>
    <div class="ansMain">
      <h6>共${count}题</h6>
      <div>
      <c:forEach items="${problemList}" var="problem" varStatus="vs">
        <div class="question js-question" data-id="${problem.id }">
         <p>${vs.index +1}、${problem.content }</p>
         <ul class="answers">
             <c:forEach items="${problem.options}" var="option" >
               <li class="answersList"><span>${option.optionName}</span>${option.optionValue}</li>
             </c:forEach>
          </ul>
        </div>
      </c:forEach>
      </div>
      <button type="button" name="button" class="answerBtn js-answerBtn">提交</button>
      <!-- 弹出提示框 -->
      <div class="maskbody maskbody1 hide">
        <div class="prompt">
          <p>您还有题目没有完成呢！<br/><br/>快去完成全部题目再来提交吧~</p>
          <button class="probtn js-proBtn">确认</button>
        </div>
      </div>
    </div>
    <script src="${ctx}/resources/lib/jquery.min.js" charset="utf-8"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js" charset="utf-8"></script>
    <script src="${ctx}/resources/js/comment.js" charset="utf-8"></script>
    <script src="${ctx}/resources/js/answer.js" charset="utf-8"></script>
  </body>
</html>
