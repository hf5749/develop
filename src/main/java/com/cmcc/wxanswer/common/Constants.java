package com.cmcc.wxanswer.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sbyang on 2016/7/5 
 */
public class Constants {
    /**
     * 访问微服务路径
     */
    public static final String RESOURSE_URL = "resource_url";//资源接口路径
    public static final String USER_URL = "user_url";//用户接口路径
    public static final String LOG_URL = "log_url";//日志接口路径
    public static final String BASE_URL = "base_url";//基础资源
    public static final String SEARCH_ONLINE = "search_online";//全站搜索直播课堂
    public static final String MSG_URL = "msg_url";//短信下发
    public static final String APP_URL = "app_url";//应用产品接口路径
    public static final String PAY_URL = "pay_url";//支付接口路径
    public static final String SELFAPP_URL = "edu_selfapp_url";//自研应用路径
    public static final String KAPTCHA_SESSION_KEY = "key"; //验证码 
    public static final String INTEGRAL_URL = "integral_url";//积分接口路径
    /**
     * 用于第三方应用客户端 OAUTH_CLIENT_${client_id}
     */
    public static final String CACHE_OAUTH_CLIENT_PRE = "OAUTH_CLIENT_";
    /**
     * 用于当前WEB端角色 USER_ROLE_FOR_API_${userId}
     */
    public static final String CACHE_WEB_USER_CURRENT_ROLE_PRE = "USER_ROLE_FOR_API_";
    
    /*  短信模块 变量定义开始  add by chensong  begin*/
    /**
     * 短信类别
     */
    public static final Long MSG_TYPE_INFORM = 1L;//通知
    public static final Long MSG_TYPE_COMMENT = 2L;//评语
    public static final Long MSG_TYPE_WORK = 3L;//作业
    
    /**
     * 短信发送类型
     */
    public static final Long MSG_SEND_TIMER_TYPE = 2L;//定时短信
    public static final Long MSG_SEND_COMMON_TYPE = 1L;//普通短信
    
    
    /**
     * 短信接收方类型
     */
    public static final Long MSG_RECEIVED_PERSION_TYPE = 1L;//个人
    public static final Long MSG_RECEIVED_GRADE_TYPE = 2L;//班级
    
    
    /**
     * 短信发送状态
     */
    public static final Long MSG_SEND_SUCCESS_STATUS = 1L;//发送成功
    public static final Long MSG_SEND_DEFAULT_STATUS = 2L;//发送失败
    public static final Long MSG_SEND_DELETE_TIMER_STATUS = 3L;//定时删除
    public static final Long MSG_SEND_TIMER_CANCEL_STATUS = 4L;//定时发送取消
    public static final Long MSG_SEND_TIMER_WAIT_STATUS = 5L;//等待状态
    
    /**
     * 学段：0 小学，1 初中，2 高中
     */
    public static final Long STEP_PRIMARY_SCHOOL = 0L;//小学
    public static final Long STEP_JUNIOR_MIDDLE_SCHOOL = 1L;//初中
    public static final Long STEP_HIGH_SCHOOL = 2L;//高中
    
    
    /* 短信模块 变量定义结束 end */
    
    /**
     * 用户角色
     * 1：教师；2：学生；3：家长
     * add by chensong
     */
    public static final String USER_ROLE_TEACHER = "1";//教师
    public static final String USER_ROLE_STUDENT = "2";//学生
    public static final String USER_ROLE_PARENTS = "3";//家长
    
    /**订购子组合包id配置*/
    /*新东方-考点精讲*/
    public static final long EDU_ORDER_PRODUCT_NEWORIENTAL_JJ = 5000006;
    /*新东方-名师优课*/
    public static final long EDU_ORDER_PRODUCT_NEWORIENTAL_YK = 5000007;
    
    
    
    //////////一期
    


    public static final int SYS_CLASSSPACE=1001;   // 动态归属系统类型-班级空间
    public static final int SYS_PERSONCENTER=1002; // 动态归属系统类型-个人中心
    public static final int SYS_RESOURCE=1003;     // 动态归属系统类型-资源

    public static final int MODULE_TYPE_CLASS_DAILY=2001;     // 动态归属模块类型-班级日志
    public static final int MODULE_TYPE_CLASS_PHOTO=2002;     // 动态归属模块类型-班级相册
    public static final int MODULE_TYPE_CLASS_NOTICE=2003;    // 动态归属模块类型-班级公告
    public static final int MODULE_TYPE_CLASS_HONOR=2004;     // 动态归属模块类型-班级荣誉
    public static final int MODULE_TYPE_CLASS_WEIBO=2005;     // 动态归属模块类型-班级微博
    public static final int MODULE_TYPE_CLASS_PERSON_WEIBO=2006;     // 动态归属模块类型-个人说说班级空间产生动态
    public static final int MODULE_TYPE_CLASS_SHARE_ZY=2007;//分享资源
    public static final int MODULE_TYPE_CLASS_SHARE_BKB=2008;//第三方分享备课包
    
    //20161107新增
    public static final int MODULE_TYPE_CLASS_CSHARE=2011;//动态归属模块类型-班级网盘
    //20161103新增
    public static final int MODULE_TYPE_PERSON_DAILY=2009;//动态归属模块类型-个人日志
    public static final int MODULE_TYPE_PERSON_PHOTO=2010;//动态归属模块类型-个人相册
 
    
    
    public static final int OPTCATEGORY_CLASS_DAILY_PUBLISH=3001;     // 动态类别-写班级日志
    public static final int OPTCATEGORY_CLASS_DAILY_SHARE=3002;     // 动态类别-分享班级日志
    public static final int OPTCATEGORY_CLASS_PHOTO_UPLOAD_SINGLE=3003;     // 动态类别-上传单张照片
    public static final int OPTCATEGORY_CLASS_PHOTO_UPLOAD_BATCH=3004;     // 动态类别-上传多张照片
    public static final int OPTCATEGORY_CLASS_NOTICE_PUBLISH=3005;    // 动态类别-发布班级公告（弃用）
    public static final int OPTCATEGORY_CLASS_HONOR_GRANT=3006;    // 动态类别-授予班级荣誉（弃用）
    public static final int OPTCATEGORY_CLASS_PERSON_WEIBO_SHARE=3007;//个人说说产生班级动态
    public static final int OPTCATEGORY_CLASS_SHARE_ZY=3008;    //第三方分享资源（弃用）
    public static final int OPTCATEGORY_CLASS_SHARE_BKB=3009;    //第三方分享备课包（弃用）
    public static final int OPTCATEGORY_CLASS_SHARE=3010;    //分享资源（教学资源）
    public static final int OPTCATEGORY_CLASS_SHARE_JPKC=3013;    //分享资源（精品课程）
    //演示实验
    public static final int OPTCATEGORY_CLASS_SHARE_YSSY=3014;    //分享资源（演示实验）
    public static final int OPTCATEGORY_CLASS_UPLOADFILE=3011;    //分享资源（教学资源，精品课程）
    public static final int OPTCATEGORY_CLASS_WEIBO_PUBLISH=3012;//发表动态; 
    
    public static final int OPTCATEGORY_PERSON_DAILY_PUBLISH=100002;//个人发日志
    public static final int OPTCATEGORY_PERSON_PHOTO_UPLOAD_SINGALE=100008;//上传单张照片
    public static final int OPTCATEGORY_PERSON_PHOTO_UPLOAD_BATCH=100009;     // 动态类别-上传多张照片
    public static final int OPTCATEGORY_PERSON_SHARE_SINGLE_RESOURCE=100046;//分享资源
    public static final int OPTCATEGORY_PERSON_SHARE_WEIBO_SHARE=100048;//个人说说产生个人动态
    public static final int OPTCATEGORY_PERSON_ADD_FRIEND=100041;//加好友 （弃用）
    public static final int OPTCATEGORY_PERSON_ASSIGN_HOMEWORK=100023;//布置作业（弃用）
    public static final int OPTCATEGORY_PERSON_ASKING=100031;//提问（弃用）
    public static final int OPTCATEGORY_PERSON_ANSWER =100032;//答疑（弃用）
    public static final int OPTCATEGORY_PERSON_VIEW_DOC_RESOURCE=102011;//查看文档资源（弃用）
    public static final int OPTCATEGORY_PERSON_VIEW_VIDEO_RESOURCE=102012;//查看视频资源（弃用）
    public static final int OPTCATEGORY_PERSON_SHARE_ZX=100044;//分享资讯
    public static final int OPTCATEGORY_PERSON_SHARE_RESOURCE=100045;//分享资源(包)
    public static final int OPTCATEGORY_PERSON_SHARE_BKB=100047;//分享备课包
    public static final int OPTCATEGORY_PERSON_SHARE_JPKC=100049;//分享精品课程
    //演示实验
    public static final int OPTCATEGORY_PERSON_SHARE_YSSY=100050;//分享演示实验
    
    //20161024新增
    public static final int OPTCATEGORY_PERSON_PHOTO_UPLOAD=100009;//上传照片
    
    public static final int OPTTYPE_ORIGINAL=1; // 动态操作类型-原创
    public static final int OPTTYPE_FORWARD=2; // 动态操作类型-转发
    public static final int OPTTYPE_SHARE=3; // 动态操作类型-分享

    //班级角色转换成客户端识别的代码
    public static Map<String,Object> classRole = new HashMap<String,Object>();
    static{
    	classRole.put("ROLE_CLASS_TEACHER", 1);
    	classRole.put("ROLE_CLASS_PARENT", 3);
    	classRole.put("ROLE_CLASS_STUDENT", 2);
    	classRole.put("ROLE_CLASS_MASTER", 1);
    	classRole.put("ROLE_UNKNOWN", 0);
    	classRole.put("ROLE_MULTI", 5);
    }

    public static Map<String,String> djgExtMap = new HashMap<String,String>();//电教馆作品格式map
	 static{
		 //图片
		 djgExtMap.put("jpg", "pic");
		 djgExtMap.put("jpeg", "pic");
		 djgExtMap.put("png", "pic");
		 djgExtMap.put("gif", "pic");
		 
		 //视频
		 djgExtMap.put("swf", "video");
		 djgExtMap.put("3gp", "video");
		 djgExtMap.put("mpg", "video");
		 djgExtMap.put("mpeg", "video");
		 djgExtMap.put("avi", "video");
		 djgExtMap.put("mov", "video");
		 djgExtMap.put("mp4", "video");
		 
		 //zip压缩包
		 djgExtMap.put("zip", "zip");
	 }
    public static String smstemplateValidcodeRegister="尊敬的用户，您的短信验证码是${validcode}，5分钟内有效。此验证码只用于您的“和教育”云平台，请勿转发。";
    public static String smstemplateResetPassword="您的新密码为:${password}";
    public static String smstemplateResetChildPassword="您的小smstemplateResetPassword孩${childName}的新密码为:${password}";
    public static String smstemplateRegistChildPassword="您的小孩${childName}的密码为:${password}";

    public static String hsstemplateOrderCreate="尊敬的用户，您的短信验证码是${validcode}，5分钟内有效。此验证码只用于您的“和教育”云平台，请勿转发。";
    public static String hsstemplateSendMsg = "";
    
    public static final int IS_PUBLISH_WB_TO_CACTION = 0;//发个人说说的时候是否产生班级动态，1表示产生，0表示不产生

    /**论文大赛投票下行**/
    //(1).用户在投票期之外投票时,系统短信提示：
    public static String sendSmsForThesisVoteOne = "“中国移动'和教育'杯全国教育技术论文活动”的论文投票时间为：${voteStartTime}-${voteEndTime},今天不在投票期内。";
    //(2).用户在投票期之内投票时：
    //①.一天内对一个作品投票一次时,系统短信提示:
    public static String sendSmsForThesisVoteTwo = "感谢您参与“中国移动'和教育'杯全国教育技术论文活动”用户投票（注：每个手机号码每天仅能给同一作品投一票）。";
    //②.一天内对一个作品投票一次以上时，系统短信提示:
    public static String sendSmsForThesisVoteThree = "您今天已经对该作品投过票，本次投票将不被系统统计，请明天再来。投票规则：一个手机号一天只能对同一个作品投票一次。";

    /**电教馆短信下行**/
    //(1).用户在投票期之外投票时,系统短信提示：
    public static String sendSmsForDjgVoteOne = "第${th}届全国中小学电脑制作活动的作品投票时间为：${voteStartTime}至${voteEndTime},今天不在投票期内。";
    //(2).用户在投票期之内投票时：
    //①.一天内对一个作品投票一次时,系统短信提示:
    public static String sendSmsForDjgVoteTwo = "感谢您对编号为${productId}的作品投票。投票规则：一个手机号一天只能对同一个作品投票一次。";
    //②.一天内对一个作品投票一次以上时，系统短信提示:
    public static String sendSmsForDjgVoteThree = "您今天已经对该作品投过票，本次投票将不被系统统计，请明天再来。投票规则：一个手机号一天只能对同一个作品投票一次。";
    //客户端发通知短信提示
    public static String sendSmsForClassNotice = "${userName}发来通知：${count}。详细内容请登录“和教育”客户端查看。";
    
    //客户端发通知短信提示
    public static String sendSmsForClassWork = "${userName}发来作业通知：${count}。详细内容请登录“和教育”客户端查看。";
    
    public static Map<String,String> roleMap = new HashMap();
	static{
		roleMap.put("ROLE_CLASS_TEACHER", "教师");
		roleMap.put("ROLE_CLASS_PARENT", "家长");
		roleMap.put("ROLE_CLASS_STUDENT", "学生");
    }
	
	public static Map<Integer,String> achieveTypeMap = new HashMap();
	
	static{
		achieveTypeMap.put(1, "\"和教育\"体验券");
		achieveTypeMap.put(2, "精美礼品");
    }
	
	 
    /**在线作业发短信通知**/
    //教师发布新作业后通知学生
    public static String sendSmsFormOnlieToStudentNotic = "在线作业提醒，你有新的作业：${homeworkName}，快去完成吧";
    //教师发布新作业后通知学生家长  
    public static String sendSmsFormOnlieToParentNotic = "在线作业：您的孩子有新作业了：${homeworkName}，快去看看吧";
    //教师一键提醒学生
    public static String sendSmsFormOnlieToNoSendStudentNotic ="${teacherName}老师发来作业提醒：${homeworkName}作业还没提交，请尽快完成。";
    
    
    /**开学季活动下发短信通知内容 **/
    //京东优惠券下发短信内容
    public static String sendJdcoupon = "您好！恭喜您成功领取京东图书商城满100减20优惠券，券码${couponCode}，下载“和教育”赢iPhone6！edu.10086.cn/educloud/act/msg";
    //途牛五星会员权益礼包下发短信内容
    public static String sendTuNiuFiveStars = "您好！恭喜您获得途牛300元旅游礼包，券码${couponCode}，点击t.cn/RLeM8LK 马上绑定账户！下载“和教育”赢iPhone6！edu.10086.cn/educloud/act/msg";
    //途牛旅游红包下发短信内容
    public static String sendTuNiuRedEnvelope = "您好！恭喜您获得途牛五星会员权益礼包，券码${couponCode}，点击t.cn/RLeM8LK 成为五星会员，下载“和教育”赢iPhone6！edu.10086.cn/educloud/act/msg";
    //转盘京东满200减80优惠券下发短信内容
    public static String sendJDturnTable = "您好！恭喜您获得京东图书商城满200减80优惠券，券码${couponCode}，下载“和教育”赢iPhone6！edu.10086.cn/educloud/act/msg";
    //转盘苏宁满1000减300优惠券下发短信内容
    public static String sendSuningTurnTable = "您好！恭喜您获得苏宁易购数码电子教育类满1000减300优惠券，券码${couponCode}，密码${password}，下载“和教育”赢iPhone6！edu.10086.cn/educloud/act/msg";
    //返回活动聚合页短链接指令
    public static String yx = "YX0001";
    //返回客户端下载
    public static String xz = "XZ0000";
    //短信推广模版（下行短信）
    public static String tgmbxxdx = "开学领好礼，就在“和教育”！苏宁、途牛、京东千万礼包大派送！点击edu.10086.cn/educloud/act/s/000003，还有机会赢iPhone6！更多精彩:edu.10086.cn/educloud/act/msg";


    /**订购子组合包id配置*/
    /*学科资源*/
    public static final long EDU_ORDER_PRODUCT_SUBJECTRESOURCE = 5000013;
}
