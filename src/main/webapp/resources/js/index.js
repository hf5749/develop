$(function(){
  $(document).on('click','.js-answerBtn',function(){
    var post = $.post(jsCtx + '/activity/isStart');
    post.done(function(data){
      if (data && data.code === 0) {
        window.location = jsCtx + '/problem/problemList';
      }else{
        $('.maskbody').show();
        $('.prompt p').html("现在不是活动时间，还不能答题！")
      }
    });
  });
  $('.js-proBtn').on('click',function(){
    $(this).parents('.maskbody').hide();
  });
})
