$(function(){
    doLoded ();
    //is_weixn();
})
function doLoded (){
  var url = location.href.split('#')[0];
  var params = {
      url:url
       };
  var post = $.post(jsCtx + '/activity/share', params);
  post.done(function(data){
  wx.config({
      debug: false,
      appId: 'wx71f744857965ffa9', // 必填，公众号的唯一标识
      timestamp: data.data.timestamp, // 必填，生成签名的时间戳
      nonceStr: data.data.nonceStr, // 必填，生成签名的随机串
      signature: data.data.signature,// 必填，签名，见附录1
      jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareQZone'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
  });
  wx.onMenuShareTimeline({
      title: '凝聚共识增合力  喜迎党的 十九大——党建工建知识有奖问答', // 分享标题
      link: `${window.location.protocol}//${window.location.host}`+jsCtx+'/activity/', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
      imgUrl: `${window.location.protocol}//${window.location.host}`+jsCtx +'/resources/images/wxshare.png', // 分享图标
      success: function () {
          // 用户确认分享后执行的回调函数
      },
      cancel: function () {
          // 用户取消分享后执行的回调函数
      }
  });
  wx.onMenuShareAppMessage({
      title: '凝聚共识增合力  喜迎党的 十九大——党建工建知识有奖问答', // 分享标题
      desc: '全部答对12道题者有机会获赠150M国内手机流量', // 分享描述
      link: `${window.location.protocol}//${window.location.host}`+jsCtx+'/activity/', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
      imgUrl: `${window.location.protocol}//${window.location.host}`+jsCtx +'/resources/images/wxshare.png', // 分享图标
      type: '', // 分享类型,music、video或link，不填默认为link
      dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
      success: function () {
          // 用户确认分享后执行的回调函数
      },
      cancel: function () {
          // 用户取消分享后执行的回调函数
      }
  });
  wx.onMenuShareQQ({
      title: '凝聚共识增合力  喜迎党的 十九大——党建工建知识有奖问答', // 分享标题
      desc: '全部答对12道题者有机会获赠150M国内手机流量', // 分享描述
      link: `${window.location.protocol}//${window.location.host}`+jsCtx+'/activity/', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
      imgUrl: `${window.location.protocol}//${window.location.host}`+jsCtx +'/resources/images/wxshare.png', // 分享图标
      success: function () {
         // 用户确认分享后执行的回调函数
      },
      cancel: function () {
         // 用户取消分享后执行的回调函数
      }
  });
  wx.onMenuShareQZone({
      title: '凝聚共识增合力  喜迎党的 十九大——党建工建知识有奖问答', // 分享标题
      desc: '全部答对12道题者有机会获赠150M国内手机流量', // 分享描述
      link: `${window.location.protocol}//${window.location.host}`+jsCtx+'/activity/', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
      imgUrl: `${window.location.protocol}//${window.location.host}`+jsCtx +'/resources/images/wxshare.png', // 分享图标
      success: function () {
         // 用户确认分享后执行的回调函数
      },
      cancel: function () {
          // 用户取消分享后执行的回调函数
      }
  });
  });
}
//function is_weixn(){
//    var ua = navigator.userAgent.toLowerCase();
//    if(ua.match(/MicroMessenger/i)=="micromessenger") {
//        return true;
//    } else {
//      window.location = jsCtx + '/pages/error.jsp';
//    }
//}
