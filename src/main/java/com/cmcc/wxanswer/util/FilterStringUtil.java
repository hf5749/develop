package com.cmcc.wxanswer.util;

import java.util.regex.Pattern;

/**
 * <b>类名称：</b>FilterStringUtil
 * <b>类描述：</b>过滤string工具类
 * <b>创建人：</b>ChenSong
 * <b>修改人：</b>ChenSong
 * <b>修改时间：</b>2016-12-7 上午10:45:47
 * <b>修改备注：</b>
 * @version v1.0<br/>
 */
public class FilterStringUtil {
 /***
  * 此方法 过滤 html 标签代码 script 标签 style标签，
  * @param inputString 需要过滤的文本
  * @return 返回出去标签的字符串
  */
  public static String FilterHtmlScriptStyleText(String inputString) {

         String htmlStr = inputString; // 含html标签的字符串

         String textStr = "";

         java.util.regex.Pattern p_script;

         java.util.regex.Matcher m_script;

         java.util.regex.Pattern p_style;

         java.util.regex.Matcher m_style;

         java.util.regex.Pattern p_html;

         java.util.regex.Matcher m_html;
         try {

             String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?/script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>

                                                                                                         // }

             String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?/style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>

                                                                                                     // }

             String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

 

             p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);

             m_script = p_script.matcher(htmlStr);

             htmlStr = m_script.replaceAll(""); // 过滤script标签

             p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);

             m_style = p_style.matcher(htmlStr);

             htmlStr = m_style.replaceAll(""); // 过滤style标签

 

             p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);

             m_html = p_html.matcher(htmlStr);

             htmlStr = m_html.replaceAll(""); // 过滤html标签

             textStr = htmlStr;
         } catch (Exception e) {
             System.err.println("Html2Text: " + e.getMessage());
         }
         return textStr;// 返回文本字符串

     }
}