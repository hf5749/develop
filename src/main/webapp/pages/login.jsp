<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
  <head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <script>var jsCtx = "${ctx}"</script>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/base.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/login.css">
  </head>
  <body>
    <div class="">
      <img class="Tlogo" src="${ctx}/resources/images/Tlogo.png" alt="">
      <div class="userpwd">
        <input type="text" placeholder="账号" class="username js-userName">
        <input type="password" placeholder="密码" class="pwd js-pwd">
      </div>
      <p class="loginTips hiden">账号不能为空!</p>
      <div class="subBtn">
        <a href="javascript:;" class="js-submitBtn">确定</a>
      </div>
    </div>
    <script src="${ctx}/resources/lib/jquery.min.js" charset="utf-8"></script>
    <script type="text/javascript">
    	$(function(){
    		$(document).on('click','.js-submitBtn',function(){
				$('.loginTips').addClass('hiden');
    			var userName = $('.js-userName').val();
    			var pwd = $('.js-pwd').val();
    			parms = {
    					name:userName,
    					pwd:pwd
    			}
    			var post = $.post(jsCtx + '/login', parms);
    			post.done(function(data){
    				if(data && data.code === 0){
    		            window.location.href = jsCtx + "/activityAnalyData";
    		          }else{
    		            $('.loginTips').removeClass('hiden');
    		            $('.loginTips').html(data.desc)
    		          }
    			})
    		})
    	})
    </script>
  </body>
</html>
