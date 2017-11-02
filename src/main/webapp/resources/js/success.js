$(function(){
  $(document).on('click','.js-inviBtn',function(){
	$('.maskbody1').show();
  })
  $('.maskbody1').on('click',function(){
	$('.maskbody1').hide();
  })
  $(document).on('click','.js-nSubmit',function(){
    var phoneNum = $('.js-telNum').val();
    var  accessToken= $('.js-accessToken').data('accesstoken')
    if(!(/^1[34578]\d{9}$/.test(phoneNum))){
        $('.telTips').removeClass('hiden');
    }else{
      $('.telTips').addClass('hiden');
       var post = $.post(jsCtx + '/exchange/exchange', {phone:phoneNum,accessToken:accessToken});
       post.done(function(data){
           if (data && data.code === 0) {
            window.location = jsCtx + '/pages/drawSuccess.jsp';
           }else if (data && data.code === 1) {
             $('.maskbody').show();
             $('.prompt p').html("该手机号已兑换过")
           }else if(data && data.code === 2){
             $('.maskbody').show();
             $('.prompt p').html("今天的流量已经被抢光了,<br/>明天早点来吧！")
          }else if(data && data.code === 3){
            $('.maskbody').show();
            $('.prompt p').html("该微信号已兑换过")
          }else{
            $('.maskbody').show();
            $('.prompt p').html(data.desc)
          }
       });
    }
  });
  $(document).on('click','.js-proBtn',function(){
    $(this).parents('.maskbody').hide();
  });
})
