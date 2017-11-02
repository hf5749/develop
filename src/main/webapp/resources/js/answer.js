"use strict";
$(function(){
  $(document).on('click','.answersList',function(){
    $(this).toggleClass('active');
    $(this).siblings().removeClass('active');
  });
  $(document).on('click','.js-answerBtn',function(){
    var proIds = "";
    var ansChioses = "";
    for (var i = 0; i < $('.question').length; i++) {
      if(!$('.question').eq(i).find('li').hasClass('active')){
        $('.maskbody1').show();
        return;
      }else{
        if (i == $(".question").length - 1) {
          proIds = proIds+ $(".question").eq(i).data('id');
          ansChioses = ansChioses+ $(".question").eq(i).find('.active span').text();
        }else{
          proIds = proIds+ $(".question").eq(i).data('id')+",";
          ansChioses = ansChioses+ $(".question").eq(i).find('.active span').text()+",";
        }
      }
    }
     var params = {
         problemIds:proIds,
         answers:ansChioses
     };
     var post = $.post(jsCtx + "/problem/solveProblem",params)
	   post.done(function(data){
		 $("body").html(data)
	   })
  })
  $('.js-proBtn').on('click',function(){
    $(this).parents('.maskbody').hide();
  });
})
