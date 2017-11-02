package com.cmcc.wxanswer.util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.GZIPOutputStream;

public class StringUtil {

	/**
	 * 用于随机产生的字符串处理
	 */
	private static Random randGen = null;

	/**
	 * 数字和字母的最大集，用于随机产生字符串
	 */
	private static char numbersAndLetters[] = null;

	/**
	 * @roseuid 3F39FE460259
	 */
	public StringUtil() {

	}

	/**
	 * 按照Zip格式压缩字符串
	 * 
	 * @param upzipStr
	 *            压缩字符串
	 * @return 压缩字符串
	 * @roseuid 3F3A00B60256
	 */
	public static String compress(String upzipStr) {
		String c = "";
		try {
			ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(outBuffer);
			// 不能是：upzipStr.getBytes("ISO8859_1")
			gzip.write(upzipStr.getBytes());
			gzip.close();
			c = new String(outBuffer.toByteArray(), "ISO-8859-1");
			outBuffer.reset();
		} catch (IOException ex) {
			// System.out.println("compress - error:" + ex.getMessage());
		}
		return c;
	}

	/**
	 * 按照Zip格式解压字符串
	 * 
	 * @param zipStr
	 *            压缩字符串
	 * @return 结果
	 * @roseuid 3F3A00B602C4
	 */
	/*
	 * public static String decompress(String zipStr) { String c = ""; try {
	 * ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
	 * ByteArrayInputStream inBuffer = new ByteArrayInputStream(zipStr.getBytes(
	 * "ISO8859_1")); GZIPInputStream gunzip = new GZIPInputStream(inBuffer);
	 * byte[] buffer = new byte[256]; int n; while ( (n = gunzip.read(buffer))
	 * >= 0) { outBuffer.write(buffer, 0, n); } c = new
	 * String(outBuffer.toByteArray(), "ISO-8859-1"); } catch (IOException ex) {
	 * System.out.println("compress - error:" + ex.getMessage()); } return
	 * StringUtil.ISO2GBK(c); }
	 */

	/**
	 * 消除回车换行符
	 * 
	 * @param str
	 *            待处理的字符串
	 * @return finalStr 处理之后的字符串
	 * @roseuid 3F3A00B6033C
	 */
	public static String delEnter(String str) {
		String finalStr = "";
		if (str == null || str.equals("")) {
			return str;
		}
		for (int ii = 0; ii < str.length(); ii++) {
			if ((str.charAt(ii) != 13) && (str.charAt(ii) != 10)) {
				finalStr += str.charAt(ii);
			}
		} // end for
		return finalStr;
	}

	/**
	 * 静态方法，把原字符串（sourceString）以分割标志（divideFlag）分割，并返回分割后的
	 * 字符串数组。如果分割标志不存在，则放回的数组中仅仅有一个元素，该元素为原字符串（ sourceString）
	 * 
	 * @param source
	 *            原字符串
	 * @param divideFlag
	 *            分割标志
	 * @return String[] 返回分割后的字符串数组
	 * @roseuid 3F3A00B603AB
	 */
	public static String[] divideString(String source, String divideFlag) {
		StringTokenizer st = new StringTokenizer(source, divideFlag);
		// 求数组长度
		int count = st.countTokens();
		String apple[] = new String[count];
		// 填充数组
		for (int ii = 0; ii < count; ii++) {
			apple[ii] = st.nextToken();
		} // end for
		return apple;
	}

	/**
	 * End method null2Str 对字符串进行分割处理。以定义的分割符headStr,tailStr
	 * 作为标记记，把以该标记为头尾的字符串进行分割，返回 一个字符串数组。
	 * 
	 * @param str
	 *            待处理字符串
	 * @param head
	 *            头分割标记
	 * @param tail
	 *            尾分割标记
	 * @return finalStr 处理后的字符串数组
	 * @roseuid 3F3A00B70077
	 */
	public static String[] divideString(String str, String head, String tail) {
		java.util.Vector vector = new java.util.Vector(1);
		int start = str.indexOf(head);
		int end = str.indexOf(tail);
		// 求头标志和尾标志的长度
		int headLen = head.length();
		int tailLen = tail.length();
		int count = 0;
		// 查找头标志
		while (start >= 0 && end >= 0) {
			String tmpStr = str.substring(start + headLen, end);
			// 长度不为0则存储
			if (tmpStr != null && !tmpStr.equals("")) {
				vector.add(count++, tmpStr);
				// 查找下一个字符的头尾标志
			}
			start = str.indexOf(head, end + tailLen);
			end = str.indexOf(tail, start);
		} // end while
		// 无匹配条件则返回空
		if (count == 0) {
			return null;
		}
		String[] apple = new String[count];
		// 将vector中的数据项转化为数组
		for (int ii = 0; ii < count; ii++) {
			apple[ii] = (String) vector.get(ii);
		} // end for
		return apple;
	}

	/**
	 * 静态方法，把字符串内码从GB2312转化成为Iso8859_1。
	 * 
	 * @param strIn
	 *            处理前的字符串
	 * @return strOut 处理后的字符串
	 * @roseuid 3F3A00B70167
	 */
	public static String GB23122ISO(String strIn) {
		String strOut = null;
		if (strIn == null || (strIn.trim()).equals("")) {
			return strIn;
		}
		try {
			// 将字符串作内码转换
			byte[] b = strIn.getBytes("gb2312");
			strOut = new String(b, "ISO8859_1");
		} catch (java.io.UnsupportedEncodingException e) {
			return null;
		}
		return strOut;
	}

	/**
	 * 静态方法，把字符串内码从GBK转化成为Iso8859_1。
	 * 
	 * @param strIn
	 *            处理前的字符串
	 * @return strOut 处理后的字符串
	 * @roseuid 3F3A00B701DF
	 */
	/*
	 * public static String GBK2ISO(String strIn) { String strOut = null; if
	 * (TypeChecker.isEmpty(strIn)) { return strIn; } try { // 将字符串作内码转换 byte[]
	 * b = strIn.getBytes("GBK"); strOut = new String(b, "ISO8859_1"); } catch
	 * (java.io.UnsupportedEncodingException e) { return ""; } //end try - catch
	 * return strOut; }
	 */

	/**
	 * 判断在目标字符串中的每一个字符是否在源字符串中存在
	 * 
	 * @param sourceString
	 *            源字符串
	 * @param targetString
	 *            由目标字符组成的目标字符串
	 * @return true 有目标字符存在 false 无目标字符存在
	 * @roseuid 3F3A00B70258
	 */
	/*
	 * public static boolean getCharInString(String sourceString, String
	 * targetString) { //判断字符串是否合法，如果合法，返回 false if
	 * (TypeChecker.isEmpty(targetString) || TypeChecker.isEmpty(sourceString))
	 * { return false; } //将目标字符串转成字符数组 char[] charArray =
	 * targetString.toCharArray(); int length = charArray.length; for (int i =
	 * 0; i < length; i++) { if (sourceString.indexOf(charArray[i]) >= 0) {
	 * return true; } } //end for return false; }
	 */

	/**
	 * 将指定的字符串转化为货币格式 ￥12,333,333.00
	 * 
	 * @param data
	 *            指定的数字字符串，不能为空
	 * @return 返回格式字符串
	 * @roseuid 3F3A00B7030C
	 */
	public static String getCurrency(String data) {
		// if(!TypeChk.checkFloat(data)) return "";
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return numFilterSymbol(nf.format(Double.parseDouble(data)));
	}

	/**
	 * 将字符串转换为浮点数，如果值为空则返回默认值。
	 * 
	 * @param value
	 *            待转换的字符串
	 * @param defaultValue
	 *            默认值
	 * @return 浮点数
	 * @roseuid 3F3A00B7038E
	 */
	/*
	 * public static double getDouble(String value, double defaultValue) { if
	 * (TypeChecker.isEmpty(value) || !TypeChecker.isFloat(value)) { return
	 * defaultValue; } return Double.parseDouble(value); }
	 */

	/**
	 * 将指定的字符串转化为格式 12,333,333.00
	 * 
	 * @param data
	 *            指定的数字字符串，不能为空
	 * @return 返回格式字符串
	 * @roseuid 3F3A00B8005A
	 */
	public static String getFormatData(String data) {
		if ("".equals(data) || null == data)
			return "";
		String fh = data.substring(0, 1);
		if (!fh.equals("-")) {
			fh = "";
			return fh + getCurrency(data).substring(1);
		} else {
			String sum = getCurrency(data).substring(2);
//			LogUtils.info("StringUtil.getFormatData", "将指定的字符串转化为格式",
//					fh.concat(sum));
			return fh.concat(sum);
		}

	}

	/**
	 * 静态方法，先把字符串内码从Iso8859_1转化成为GBK，并 且求字符串的真实长度。
	 * 
	 * @param strIn
	 *            待处理的字符串
	 * @return length 处理后的字符串长度 -1失败
	 * @roseuid 3F3A00B800DC
	 */
	public static int getGBKLength(String strIn) {
		int length = 0;
		byte buff[];
		if (strIn == null || (strIn.trim()).equals("")) {
			return length;
		}
		try {
			buff = strIn.getBytes("GBK");
			length = buff.length;
		} catch (java.io.UnsupportedEncodingException e) {
			return -1;
		} // end try - catch
		return length;
	}

	/**
	 * 将目标字符串转换成十六进制字符串
	 * 
	 * @param sourceString
	 *            需要转换的目标字符串
	 * @return 十六进制字符串
	 * @roseuid 3F3A00B8015F
	 */
	public static String getHexString(String sourceString) {
		// 将目标字符串转换成字节数组
		byte[] hash = sourceString.getBytes();
		StringBuffer buf = new StringBuffer(hash.length * 2);
		for (int i = 0; i < hash.length; i++) {
			if (((int) hash[i] & 0xff) < 0x10) {
				buf.append("0");
			} // end if
			buf.append(Long.toString((int) hash[i] & 0xff, 16));
		} // end for
		return buf.toString();
	}

	/**
	 * 将字符串转换为数字，如果值为空则返回默认值。
	 * 
	 * @param value
	 *            待转换的字符串
	 * @param defaultValue
	 *            默认值
	 * @return 整形数
	 * @roseuid 3F3A00B801E1
	 */
	/*
	 * public static int getInt(String value, int defaultValue) { if
	 * (TypeChecker.isEmpty(value) || !TypeChecker.isInteger(value)) { return
	 * defaultValue; } return Integer.parseInt(value); }
	 */

	/**
	 * 将字符串转换为长整形数字，如果值为空则返回默认值。
	 * 
	 * @param value
	 *            待转换的字符串
	 * @param defaultValue
	 *            默认值
	 * @return 长整形数
	 * @roseuid 3F3A00B8029F
	 */
	/*
	 * public static final long getLong(String value, long defaultValue) { if
	 * (TypeChecker.isEmpty(value) || !TypeChecker.isLong(value)) { return
	 * defaultValue; } return Long.parseLong(value); }
	 */

	/**
	 * 静态方法，获得替换后的字符串。调用该方法时，需对StrPro 对象的三个参数进行初始化。
	 * 
	 * @param sourceString
	 *            原字符串
	 * @param targetString
	 *            被替换的字符串
	 * @param replaceString
	 *            替换字符串
	 * @return finalString 处理后的字符串
	 * @roseuid 3F3A00B8035D
	 */
	public static String getReplaceString(String sourceString,
			String targetString, String replaceString) {
		// 在sourceString字符串中，如果从s开始存在与targetString
		// 相同的子串时（e>=0），则在result中添加该子串之前的字符串，
		// 然后添加替换字符串，给s赋值。如此循环。
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();
		while ((e = sourceString.indexOf(targetString, s)) >= 0) {
			result.append(sourceString.substring(s, e));
			result.append(replaceString);
			s = e + targetString.length();
		} // End while
		result.append(sourceString.substring(s));
		String finalString = result.toString();
		return finalString;
	}

	/**
	 * 静态方法，获得替换后的字符串(替换时忽略大小写)。调用该方法时，需对StrPro 对象的三个参数进行初始化。
	 * 
	 * @param sourceString
	 *            原字符串
	 * @param targetString
	 *            被替换的字符串
	 * @param replaceString
	 *            替换字符串
	 * @return finalString 处理后的字符串
	 * @roseuid 3F3A00B9008E
	 */
	public static String getReplaceStringIgnoreCase(String sourceString,
			String targetString, String replaceString) {
		if (sourceString == null) {
			return null;
		}
		// 转成小写字符串
		String lcLine = sourceString.toLowerCase();
		String lcOldString = targetString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char[] line2 = sourceString.toCharArray();
			char[] newString2 = replaceString.toCharArray();
			int oLength = targetString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			} // end while
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		} // end if
		return sourceString;
	}

	/**
	 * 得到指定数目的空格
	 * 
	 * @param num
	 *            - 指定数目
	 * @return java.lang.String
	 * @roseuid 3F3A00B9019C
	 */
	public static String getSpace(int num) {
		return getString(num, " ");
	}

	/**
	 * end method getCurrency 将指定的字符串前面加上前导字符，使总长度为指定值
	 * 
	 * @param str
	 *            源字符串
	 * @param len
	 *            总长度
	 * @param head
	 *            前导字符
	 * @return 结果子符串
	 * @roseuid 3F3A00B90228
	 */
	/*
	 * public static String getStrAddHead(String str, int len, char head) { if
	 * (TypeChecker.isEmpty(str)) { str = ""; } int strLen = str.length(); for
	 * (int ii = 0; ii < (len - strLen); ii++) { str = String.valueOf(head) +
	 * str; } return str; }
	 */

	/**
	 * 静态方法，定义一个头字符串（headString）和一个尾字符串（tailString），
	 * 原字符串（sourceString）中包含目标字符串（targetString）的从头到尾的 字符串将被返回。
	 * 
	 * @param source
	 *            原字符串
	 * @param target
	 *            目标字符串
	 * @param head
	 *            头字符串
	 * @param tail
	 *            尾字符串
	 * @return finalString 处理之后的字符串
	 * @roseuid 3F3A00B90341
	 */
	public static String getStrFromTo(String source, String target,
			String head, String tail) {
		// 为空则返回
		if (source == null || source.equals("")) {
			return "";
		}

		String currStr = source;
		int index = 0, s = 0, e = 0;
		// 查找head
		index = currStr.indexOf(head);
		if (index >= 0) {
			s = index + head.length();
			e = s;
			currStr = currStr.substring(e);
			// 查找target
			index = currStr.indexOf(target);
		}
		if (index >= 0) {
			e += index + target.length();
			currStr = currStr.substring(e - s);
			// 查找tail
			index = currStr.indexOf(tail);
		}
		// 是否有目标字串
		if (index >= 0) {
			e += index;
		} else {
			s = 0;
			e = 0;
		} // end if

		return source.substring(s, e);
	}

	/**
	 * 静态方法，定义一个头字符串（headString）和一个尾 字符串（tailString），原字符串（sourceString）中
	 * 的从头到尾的字符串将被返回。
	 * 
	 * @param source
	 *            原字符串
	 * @param head
	 *            头字符串
	 * @param tail
	 *            尾字符串
	 * @return finalString 处理之后的字符串
	 * @roseuid 3F3A00BA00C1
	 */
	public static String getStrFromTo(String source, String head, String tail) {
		String finalString = "";
		if (source == null || source.equals("")) {
			return finalString;
		}
		int e = 0, s = 0;
		e = source.indexOf(head);
		s = source.indexOf(tail, e + head.length());
		// 有一个找不到则返回空
		if (e >= 0 && s > 0 && s > e) {
			finalString = source.substring(e + head.length(), s);
		} // end if
		return finalString;
	}

	/**
	 * 得到指定数目的字符串
	 * 
	 * @param num
	 *            - 字符数目
	 * @param str
	 *            - 重复字符串
	 * @return java.lang.String
	 * @roseuid 3F3A00BA01DA
	 */
	public static String getString(int num, String str) {
		String tmpStr = "";
		for (int ii = 0; ii < num; ii++) {
			str += tmpStr;
		} // end for
		return tmpStr;
	}

	/**
	 * 得到某一个字符串中指定子符串个数
	 * 
	 * @param srcStr
	 * @param subStr
	 * @return int
	 * @roseuid 3F3A00BA02B6
	 */
	/*
	 * public static int getSubStrNum(String srcStr, String subStr) { int count
	 * = 0; //判断字符串是否合法 if (TypeChecker.isEmpty(srcStr)) { return count; } if
	 * (subStr == null || subStr.equals("")) { return count; } int pos = 0;
	 * while ( (pos = srcStr.indexOf(subStr)) >= 0) { count++; srcStr =
	 * srcStr.substring(pos + 1); } //end while return count; }
	 */

	/**
	 * 返回指定哈西表中关键字的值，关键字或值不存在则返回空。次方法主要供处理字段行对象 时不返回null对象
	 * 
	 * @param Row
	 *            哈西表对象
	 * @param name
	 *            关键字名称
	 * @return 关键字值，关键字或值不存在则返回空。
	 * @roseuid 3F3A00BA0388
	 */
	/*
	 * public static String getValue(HashMap Row, String name) { if (Row == null
	 * || TypeChecker.isEmpty(name)) { return ""; } return (Row.get(name) ==
	 * null) ? "" : (String) Row.get(name); }
	 */

	/**
	 * 静态方法，把字符串内码从Iso8859_1转化成为GBK。
	 * 
	 * @param strIn
	 *            处理前的字符串
	 * @return strOut 处理后的字符串
	 * @roseuid 3F3A00BB0091
	 */
	/*
	 * public static String ISO2GB2312(String strIn) { String strOut = null; if
	 * (TypeChecker.isEmpty(strIn)) { return strIn; } try { // 将字符串作内码转换 byte[]
	 * b = strIn.getBytes("ISO8859_1"); strOut = new String(b, "gb2312"); }
	 * catch (java.io.UnsupportedEncodingException e) { return ""; } return
	 * strOut; }
	 */

	/**
	 * 静态方法，把字符串内码从Iso8859_1转化成为GBK。
	 * 
	 * @param strIn
	 *            处理前的字符串
	 * @return strOut 处理后的字符串
	 * @roseuid 3F3A00BB0127
	 */
	/*
	 * public static String ISO2GBK(String strIn) { String strOut = null; if
	 * (TypeChecker.isEmpty(strIn)) { return strIn; } try { // 将字符串作内码转换 byte[]
	 * b = strIn.getBytes("ISO8859_1"); strOut = new String(b, "GBK"); } catch
	 * (java.io.UnsupportedEncodingException e) { return ""; } //end try_catch
	 * return strOut; }
	 */

	/**
	 * 判断targetString是否是sourceString的子串
	 * 
	 * @param sourceString
	 *            原字符串
	 * @param targetString
	 *            目的字符串
	 * @return boolean 是，返回true，否则返回false
	 * @roseuid 3F3A00BB01B3
	 */
	public static boolean isSubString(String sourceString, String targetString) {
		int e = 0;
		e = sourceString.indexOf(targetString);
		if (e >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 如果字符串（str）为null，则将其转化成为空字符串""。
	 * 
	 * @param str
	 *            待处理的字符串
	 * @return str 处理后的字符串
	 * @roseuid 3F3A00BB02A3
	 */
	public static String null2Str(String str) {
		return (str == null) ? "" : str;
	}

	/**
	 * End method null2Str 如果字符串（str）为null，则将其转化成为其他字符串（otherStr）。
	 * 
	 * @param str
	 *            待处理字符串
	 * @param otherStr
	 *            转化后的字符串
	 * @return str 处理后的字符串
	 * @roseuid 3F3A00BB033A
	 */
	public static String null2Str(String str, String otherStr) {
		return (str == null) ? otherStr : str;
	}

	/**
	 * 返回一个定义长度的随机字符串
	 * 
	 * @param length
	 *            返回的随机字符串的长度
	 * @return 返回随机字符串
	 * @roseuid 3F3A00BC0038
	 */
	public static String randomString(int length) {
		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new Random();
			// 数字和字母的集合
			numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
					+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		} // end if
		// 产生随机字符串
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		} // end for
		return new String(randBuffer);
	}

	/**
	 * 替换欲放置到HTML页面中字符串中所有的\n,\t,空格符号
	 * 
	 * @param str
	 *            将要处理的字符串
	 * @return str 处理之后的字符串
	 * @roseuid 3F3A00BC00D8
	 */
	public static String replaceAllHtmlEnter(String str) {
		if (str == null || str.equals("")) {
			return str;
		}
		str = getReplaceString(str, "\n", "<br>");
		str = getReplaceString(str, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		str = getReplaceString(str, "  ", "&nbsp;&nbsp;");
		return str;
	}

	/**
	 * 替换字符串中所有的<,>,'符号
	 * 
	 * @param str
	 *            将要处理的字符串
	 * @return str 处理之后的字符串
	 * @roseuid 3F3A00BC0178
	 */
	public static String replaceAllHtmlStr(String str) {
		if (str == null || str.equals("")) {
			return "";
		}
		str = getReplaceString(str, "<", "&lt");
		str = getReplaceString(str, ">", "&gt");
		str = getReplaceString(str, "\"", "&quot;");
		return str;
	}
	
	/**
	 * 替换字符串中所有的<,>,'符号
	 * 
	 * @param str
	 *            将要处理的字符串
	 * @return str 处理之后的字符串
	 * @roseuid 3F3A00BC0178
	 */
	public static String replaceAllHtmlStr2(String str) {
		if (str == null || str.equals("")) {
			return "";
		}
		str = str.replace("&lt;", "<");
		str = str.replace("&gt;", ">");
		str = str.replace("&quot;", "\"");
		str = str.replace("&rdquo;", "\"");
		str = str.replace("&ldquo;", "\"");
		return str;
	}


	/**
	 * 把回车换行符替换为特定的字符串
	 * 
	 * @param str
	 *            待处理的字符串
	 * @param repStr
	 *            替换的目标字符串
	 * @return str 处理之后的字符串
	 * @roseuid 3F3A00BC020F
	 */
	public static String replaceEnter(String str, String repStr) {

		String strLeft = "";
		String strRight = "";
		if (str == null || str.equals("")) {
			return str;
		}

		int length = str.length();
		char a;
		for (int ii = 0; ii < str.length(); ii++) {
			a = str.charAt(ii);
			if (a == 10) {
				strLeft = str.substring(0, ii);
				strRight = str.substring(ii + 1);
				str = strLeft + repStr + strRight;
			} // end if
			else if (a == 13) {
				strLeft = str.substring(0, ii);
				strRight = str.substring(ii + 1);
				str = strLeft + strRight;
			} // end if
		} // end for
		return str;
	}

	/**
	 * 使用指定的哈西容器中的字符替换源字符串中使用"#"夹起的部分，如果内容在 哈西容器中没有定义，则替换为""。
	 * 
	 * @param str
	 *            源字符串
	 * @param ht
	 *            内容容器。
	 * @return 替换结果
	 * @roseuid 3F3A00BC02FF
	 */
	/*
	 * public static String repStr4List(String str, java.util.HashMap ht) {
	 * String tmpStr, value; while (!TypeChecker.isEmpty(tmpStr =
	 * getStrFromTo(str, "#", "#"))) { value = (String) ht.get(tmpStr); if
	 * (TypeChecker.isEmpty(value)) { value = ""; } str = getReplaceString(str,
	 * "#" + tmpStr + "#", value); } //end while return str; }
	 */

	/**
	 * 字符串数组中是否包含指定的字符串。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param string
	 *            字符串
	 * @param caseSensitive
	 *            是否大小写敏感
	 * @return 包含时返回true，否则返回false
	 * @since 0.4
	 * @roseuid 3F3A00BD0007
	 */
	public static boolean contains(String[] strings, String string,
			boolean caseSensitive) {
		for (int i = 0; i < strings.length; i++) {
			if (caseSensitive == true) {
				if (strings[i].equals(string)) {
					return true;
				}
			} else {
				if (strings[i].equalsIgnoreCase(string)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 字符串数组中是否包含指定的字符串。大小写敏感。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param string
	 *            字符串
	 * @return 包含时返回true，否则返回false
	 * @since 0.4
	 * @roseuid 3F3A00BD0152
	 */
	public static boolean contains(String[] strings, String string) {
		return contains(strings, string, true);
	}

	/**
	 * 不区分大小写判定字符串数组中是否包含指定的字符串。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param string
	 *            字符串
	 * @return 包含时返回true，否则返回false
	 * @since 0.4
	 * @roseuid 3F3A00BD0242
	 */
	public static boolean containsIgnoreCase(String[] strings, String string) {
		return contains(strings, string, false);
	}

	/**************************************************
	 * 
	 * 以下代码由蔡春焰整理添加
	 * 
	 **************************************************/

	/**
	 * 默认日期字符串格式。
	 */
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * 默认日期字符串格式。
	 */
	public static final SimpleDateFormat DEFAULT_TIMESTAMP_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss:SSS");

	public static final SimpleDateFormat JSP_FORM_NULL_TIME = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	/**
	 * 取得long值
	 * 
	 * @param oo
	 *            字符串数值
	 * @return long类型的数值
	 * @see
	 */
	public static long getLongValue(String oo) {
		try {
			return Long.parseLong(oo);
		} catch (Exception ex) {
		}

		return 0;
	}

	/**
	 * 取得int值
	 * 
	 * @param oo
	 *            字符串数值
	 * @return int类型的数值
	 * @see
	 */
	public static int getIntValue(String oo) {
		try {
			return Integer.parseInt(oo);
		} catch (Exception ex) {
		}

		return 0;
	}

	/**
	 * 取得float值
	 * 
	 * @param oo
	 *            字符串数值
	 * @return float类型的数值
	 * @see
	 */
	public static float getFloatValue(String oo) {
		try {
			return Float.parseFloat(oo);
		} catch (Exception ex) {
		}

		return 0;
	}

	/**
	 * 取得double值
	 * 
	 * @param oo
	 *            字符串数值。
	 * @return double类型的数值
	 * @see
	 */
	public static double getDoubleValue(String oo) {
		try {
			return Double.parseDouble(oo);
		} catch (Exception ex) {
		}

		return 0;
	}

	/**
	 * 根据年月日字符串获取Timestamp对象！
	 * 
	 * @param year
	 *            年（yyyy）。
	 * @param month
	 *            月（MM）。
	 * @param day
	 *            日（dd）。
	 * @return Timestamp 解析后的日期时间对象；null，如果解析有错误。
	 */
	public static Timestamp getTimestampByYMD(String year, String month,
			String day) {

		try {
			String strDate = year + "-";
			if (month.length() < 2) {
				strDate += "0";
			}
			strDate += month;
			if (day.length() < 2) {
				strDate += "0";
			}
			strDate += day;
			return new Timestamp(DEFAULT_DATE_FORMAT.parse(
					year + "-" + month + "-" + day).getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得date值,用默认的参数。
	 * 
	 * @param oo
	 *            字符串日期值。
	 * @return java.util.Date 类型日期值。
	 * @see
	 */
	public static java.util.Date getDateValue(String oo) {
		try {
			return DEFAULT_DATE_FORMAT.parse(oo);
		} catch (Exception ex) {
		}

		return new java.util.Date();
	}

	public static String getStrValue(Object objTime) {
		return JSP_FORM_NULL_TIME.format(objTime);
	}

	/**
	 * 取得date值,用两个的参数
	 * 
	 * @param oo
	 *            字符串值
	 * @param dateString
	 *            日期字符串格式
	 * @return java.util.Date类型日期值
	 * @see
	 */
	public static java.util.Date getDateValue(String oo, String dateString) {
		java.util.Date a = null;
		try {
			java.text.SimpleDateFormat smf = new java.text.SimpleDateFormat(
					dateString);
			a = smf.parse(oo);

			return a;
		} catch (Exception ex) {
		}

		return new java.util.Date();
	}

	/**
	 * 将指定字符集的字符串转化为另一个字符集的字符串。 如果系统不支持GBK字符集，将返回输入的字符串。（该情况应该不会出现）
	 * 
	 * @param ss
	 *            需要编码转换的字符串。
	 * @return 转换过的字符串。null，如果传入的字符串是null。
	 * @see
	 * @author caicai_dev@yahoo.com.cn
	 */
	public static String encode(String ss, String from_encoding,
			String to_encoding) {
		if (ss == null) {
			return null;
		}

		String returnValue = ss;
		try {
			byte[] temp = ss.getBytes(from_encoding);
			returnValue = new String(temp, to_encoding);
		} catch (Exception ex) {
		}

		return returnValue;
	}

	/**
	 * 将字符串中的回车键（0x0A0D,0x0A,0x0D）都转换为空格。
	 * 
	 * @param value
	 *            需要处理的字符串。
	 * @return 不包含回车、换行符的字符串
	 * @author caicai_dev@yahoo.com.cn
	 */
	public static String trimEnterCode(String value) {
		if (value == null) {
			return value;
		}

		String returnValue = value.replace((char) 0x0D, ' ');
		returnValue = returnValue.replace((char) 0x0A, ' ');

		return returnValue;
	}

/**
	     * 为HTML编码转义字符串。
	     * '&'-->'&amp;'
	     * '>'-->'&gt;'
	     * '<'-->'&lt;'
	     * '"'-->'&quot;'
	     * @param value 需要转义的字符串
	     * @return 转义后的字符串。
	     * @author caicai_dev@yahoo.com.cn
	     */
	public static String escapeForHTML(String value) {
		if (value == null) {
			return value;
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);
			switch (ch) {
			case '"': // '"'-->'&quot;' (&#34;)
				buffer.append("&quot;");
				break;
			case '&': // '&'-->'&amp;' (&#38;)
				buffer.append("&amp;");
				break;
			case '<': // '<'-->'&lt;' (&#60;)
				buffer.append("&lt;");
				break;
			case '>': // '>'-->'&gt;' (&#62;)
				buffer.append("&gt;");
				break;
			case (char) 0x0D: // 回车
			case (char) 0x0A: // 换行
				buffer.append("&#" + (int) ch + ";");
				break;
			default:
				buffer.append(ch);
			}
		}
		return buffer.toString();
	}

	/**
	 * 为JavaScript中字符串转义“"”“\”字符。
	 * 
	 * @param value
	 *            需要转义的字符串
	 * @return 转义后的字符串。
	 * @author caicai_dev@yahoo.com.cn
	 */
	public static String escapeForJavascript(String value) {
		if (value == null) {
			return value;
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);
			switch (ch) {
			case '"': // transfer '"' --> '\"'
				buffer.append("\\\"");
				break;
			case '\'': // transfer "'" --> "\'"
				buffer.append("\\'");
				break;
			case '\\': // transfer '\' --> '\\'
				buffer.append("\\\\");
				break;
			case (char) 0x0D: // 回车
				if (i < value.length() - 1
						&& value.charAt(i + 1) != (char) 0x0A) {
					buffer.append("\\n");
				}
				break;
			case (char) 0x0A: // 换行
				buffer.append("\\n");
				break;
			default:
				buffer.append(ch);
			}
		}
		return buffer.toString();
	}

	/**
	 * 为JavaScript中字符串转义“"”“\”字符。
	 * 
	 * @param value
	 *            需要转义的字符串
	 * @return 转义后的字符串。
	 * @author caicai_dev@yahoo.com.cn
	 */
	public static String escapeForSQL(String value) {
		if (value == null) {
			return value;
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);
			switch (ch) {
			case '\'': // transfer "'" --> "''"
				buffer.append("''");
				break;
			default:
				buffer.append(ch);
			}
		}
		return buffer.toString();
	}

	/**
	 * 替换字符串中指定的字符序列
	 * 
	 * @param originalValue
	 *            需要被处理的字符串。
	 * @param from
	 *            需要被替换的字符序列
	 * @param to
	 *            要替换的字符序列
	 * @return 转换后的字符串
	 * @author caicai_dev@yahoo.com.cn
	 */
	public static String replaceString(String originalValue, String from,
			String to) {
		if (originalValue == null || from == null || to == null) {
			return originalValue;
		}

		int index = -1;
		String returnValue = originalValue;
		while ((index = originalValue.indexOf(from)) > -1) {
			String before = returnValue.substring(0, index);
			String after = returnValue.substring(index + from.length());
			returnValue = before + to + after;
		}

		return returnValue;
	}

	/**
	 * 比较两个字符串是否相同，如果两个字符串都是null，两个字符串的值也视为相同。
	 * 
	 * @param str1
	 *            字符串1
	 * @param str2
	 *            字符串2
	 * @return boolean true，相同；false，不同。
	 * @author caicai_dev@yahoo.com.cn
	 */
	public static boolean compareStrings(String str1, String str2) {
		boolean result = false;
		// 都为空时相同
		if (str1 == null && str2 == null)
			result = true;
		// 都不为空的时候，两个空字符串相同如："   "=" "
		else if (str1 != null && str2 != null
				&& str1.trim().equals(str2.trim()))
			result = true;
		return result;
	}

	/**
	 * 使用给定的切分符，切分字符串。
	 * 
	 * @param source
	 *            待处理字符串。
	 * @param separator
	 *            指定的切分符。
	 * @return String[] 切分后的字符串数组。
	 * @author caicai_dev@yahoo.com.cn
	 */
	public static String[] splitBySeparator(String source, String separator) {
		if (source == null) {
			return null;
		}
		if (separator == null) {
			return new String[] { source };
		}
		int startIndex = 0;
		int endIndex = source.indexOf(separator);
		Vector vector = new Vector();
		while (endIndex >= startIndex) {
			vector.add(source.substring(startIndex, endIndex));
			startIndex = endIndex + separator.length();
			endIndex = source.indexOf(separator, startIndex);
		}
		vector.add(source.substring(startIndex));
		String[] returnValue = new String[vector.size()];
		vector.copyInto(returnValue);
		return returnValue;
	}

	/**
	 * 从请求中根据指定参数名称，获取明细列表地数据源。
	 * 
	 * @param request
	 *            请求
	 * @param paramName
	 *            参数名称
	 * @param required
	 *            是否是必须提供的参数
	 * @return String[][] 切分好的明细列表数据。第一维：行，第二维：列。 null，如果传入的字符串是null。
	 * @author caicai_dev@yahoo.com.cn
	 */
	public static String[][] getTableDataSource(String source) {
		if (source == null) {
			return null;
		}
		String[] lineData = splitBySeparator(source, DATASOURCE_LINE_SEPARATOR);
		String[][] dataSource = new String[lineData.length][];
		for (int i = 0; i < dataSource.length; i++) {
			dataSource[i] = StringUtil.splitBySeparator(lineData[i],
					DATASOURCE_CELL_SEPARATOR);
		}
		return dataSource;
	}

	/**
	 * 在字符串的指定位置填充多个字符
	 * 
	 * @param str
	 *            要填充的字符串
	 * @param filledChar
	 *            填充的字符
	 * @param pos
	 *            填充的开始位置
	 * @param totalNum
	 *            填充后的字符个数
	 * @return Strng 填充后的字符串
	 * 
	 * @author caicai_dev@yahoo.com.cn
	 */
	public static String fillInChar(String str, char filledChar, int pos,
			int totalNum) {
		int size = str.length();
		if (pos > size)
			pos = size;
		if (totalNum <= size)
			return str;
		char[] chars = str.toCharArray();
		int filledNum = totalNum - str.length();
		char[] destChars = new char[totalNum];
		for (int i = 0; i < pos; i++) {
			destChars[i] = chars[i];
		}
		for (int i = pos; i < filledNum + pos; i++) {
			destChars[i] = filledChar;
		}
		for (int i = filledNum + pos; i < totalNum; i++) {
			destChars[i] = chars[i - filledNum];
		}
		return new String(destChars);
	}

	/**
	 * 提交列表数据时的行分隔符。
	 */
	public final static String DATASOURCE_LINE_SEPARATOR = "^$$";

	/**
	 * 提交列表数据时，每行的单元格数据分隔符。
	 */
	public final static String DATASOURCE_CELL_SEPARATOR = "$^^";

	/**
	 * 将timestamp类型转换为string类型！
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String timestampToString(java.sql.Timestamp timestamp) {
		if (timestamp == null) {
			return "";
		}
		return DEFAULT_TIMESTAMP_FORMAT.format(timestamp);
	}

	public static String getZhgxsj() {
		return timestampToString(new Timestamp(System.currentTimeMillis()));
	}

	public static String max(String str1, String str2) {
		if (str1 == null) {
			if (str2 != null) {
				return str2;
			}
			return null;
		} else if (str2 == null) {
			return str1;
		}
		String maxValue = str2;
		if (str1.compareTo(str2) > 0) {
			maxValue = str1;
		}
		return maxValue;
	}

	public static String maxIgnoreCase(String str1, String str2) {
		if (str1 == null) {
			if (str2 != null) {
				return str2;
			}
			return null;
		} else if (str2 == null) {
			return str1;
		}
		String maxValue = str2;
		if (str1.compareToIgnoreCase(str2) > 0) {
			maxValue = str1;
		}
		return maxValue;
	}

	/**
	 * Method declaration
	 * 
	 * @param args
	 * @see
	 */
	public static void main1(String[] args) {
		String str1 = "";
		String str2 = "aBc002";
		System.out.println("max(" + str1 + "," + str2 + ")=" + max(str1, str2));
		System.out.println("maxIgnoreCase(" + str1 + "," + str2 + ")="
				+ maxIgnoreCase(str1, str2));
		System.out.println("compareto=" + "1234".compareTo(""));

		String jsValue = "abcdefg" + (char) 0x0D + "++" + (char) 0x0A
				+ "hijklmn" + (char) 0x0D + (char) 0x0A + "opqrst"
				+ (char) 0x0D + (char) 0x0A + "uvwxyz" + (char) 0x0D;

		System.out.println("before:" + jsValue);
		System.out.println("escapeForHTML:" + escapeForHTML(jsValue));
		System.out.println("escapeForJavascript:"
				+ escapeForJavascript(jsValue));

		String stvalue = "$1234$5$6$$7$8$$$9$";
		java.util.StringTokenizer st = new java.util.StringTokenizer(stvalue,
				"$");
		int count = 0;
		System.out.println("source=" + stvalue);
		System.out.println("==========================");
		while (st.hasMoreTokens()) {
			System.out
					.println("String[" + count + "]='" + st.nextToken() + "'");
			count++;
		}
		System.out.println("==========================");
		String[] splitValue = StringUtil.splitBySeparator(stvalue, "$");
		for (int i = 0; i < splitValue.length; i++) {
			System.out.println("String[" + i + "]='" + splitValue[i] + "'");
		}
		System.out.println("==========================");

		// String[][] a =
		// strTokUtil1.analyze("$^^aaaa$^^bbbb$^^cccc$^^ddd$^^33**200$^^201$^^202$^^203",
		// "**", "$^^");
		String b = "$^^aaaa$^^$^^$^^bbbb$^^cccc$^^ddd$^^33$$$^^200$^^201$^^202$^^203^$$$^^aaaa";

		String[][] la = StringUtil.getTableDataSource(b);
		for (int i = 0; i < la.length; i++) {
			for (int j = 0; j < la[i].length; j++) {
				String temp = la[i][j];
				System.out.println("i:" + i + ",j=" + j + ":" + temp + "   ");
			}

			System.out.println();
		}

		// 测试字符的填充
		String str = fillInChar("AAAAA", ' ', 0, 10);
		System.out.println("===:" + str);
		str = fillInChar("AAAAA", ' ', 2, 10);
		System.out.println("===:" + str);
		str = fillInChar("AAAAA", ' ', 5, 10);
		System.out.println("===:" + str);

	}

	/**
	 * xml to bean
	 * 
	 * @param name
	 * @return
	 */
	public static String getFiledName(String name) {
		if (name.indexOf("_") > 0) {
			String begin = name.substring(0, name.indexOf("_"));
			String middle = name.substring(name.indexOf("_") + 1,
					name.indexOf("_") + 2);
			String end = name.substring(name.indexOf("_") + 2, name.length());
			return begin + middle + end;
		} else {
			return name;
		}
	}

	public static String getDBInStr(String str) {
		if (str == null || "".equals(str))
			return "''";
		String[] args = str.split(",");
		String tmp = "";
		if (args.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (String s : args) {
				sb.append("'").append(s).append("'").append(",");
			}
			tmp = sb.toString().substring(0, sb.toString().trim().length() - 1);
			return tmp;
		} else {
			return str;
		}
	}

	public static String getDBInStr(String str, String flag) {
		if (str == null || "".equals(str))
			return "''";
		String[] args = str.split(flag);
		String tmp = "";
		if (args.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (String s : args) {
				sb.append("'").append(s).append("'").append(",");
			}
			tmp = sb.toString().substring(0, sb.toString().trim().length() - 1);
			return tmp;
		} else {
			return str;
		}
	}

	public static String getApos(String input) {
		if (null == input && "".equals(input))
			return "";
		String tmp = "";
		if (input.indexOf("'") > 0) {
			tmp = input.replace("'", "&apos;");
		} else
			tmp = input;
		return tmp;
	}

	/**
	 * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零 要用到正则表达式 适用于十亿级数据
	 */
	public static String digitUppercase10(double n) {
		String fraction[] = { "角", "分" };
		String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String unit[][] = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };

		String head = n < 0 ? "负" : "";
		n = Math.abs(n);

		String s = "";
		for (int i = 0; i < fraction.length; i++) {
			s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i])
					.replaceAll("(零.)+", "");
		}
		if (s.length() < 1) {
			s = "整";
		}
		int integerPart = (int) Math.floor(n);

		for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
			String p = "";
			for (int j = 0; j < unit[1].length && n > 0; j++) {
				p = digit[integerPart % 10] + unit[1][j] + p;
				integerPart = integerPart / 10;
			}
			s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i]
					+ s;
		}
		return head
				+ s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "")
						.replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
	}

	/**
	 * 格式化
	 * 
	 * @param str
	 *            123456789
	 * @return 1234 5678 9
	 */
	public static String formatAccount(String str) {
		if (null == str || "".equals(str))
			return "";
		String tmp = "";
		if (str != null)
			tmp = str.replaceAll(" ", "");
		String allStr = "";
		while (tmp.length() > 4) {
			allStr += tmp.substring(0, 4) + " ";
			tmp = tmp.substring(4, tmp.length());
		}
		allStr += tmp;
		return allStr;
	}

	/**
	 * 4位数字转化成金额大写。
	 * 
	 * @return java.lang.String
	 */
	public static String charToString(char[] CharArrayL) {
		if (CharArrayL.length > 4)
			return "";
		String lStr = "";
		int j = 0;
		String[] chUnit = { "", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾",
				"佰", "仟" };
		for (int i = CharArrayL.length - 1; i >= 0; i--) {
			if (CharArrayL[i] == '0' && lStr.equals("")) {
				j++;
				continue;
			} else {
				if (i > 0 && CharArrayL[i - 1] == '0' && CharArrayL[i] == '0') {
					j++;
					continue;
				}
				if (CharArrayL[i] == '0') {
					lStr = getCH(CharArrayL[i]) + lStr;
				} else {
					lStr = getCH(CharArrayL[i]) + chUnit[j] + lStr;
				}
			}
			j++;
		}
		return lStr;
	}

	static String getCH(char i) {
		switch (i) {
		case '0':
			return "零";
		case '1':
			return "壹";
		case '2':
			return "贰";
		case '3':
			return "叁";
		case '4':
			return "肆";
		case '5':
			return "伍";
		case '6':
			return "陆";
		case '7':
			return "柒";
		case '8':
			return "捌";
		case '9':
			return "玖";
		default:
			return "";
		}
	}

	/*
	 * 格式化数字：将科学计数法转化为通常形式 【与formatToNum的区别：根据传入的参数exa[int]补足小数位；】 说明：
	 * 1.如果用科学计数法表示的数值(以下简称"科学计数数")的指数为正数[如3.3E9]，又分为下列几种处理方式 1.1 科学计数数的尾数为正数
	 * 1.2 科学计数数的尾数为负数
	 * 
	 * 2.如果用科学计数法表示的数值(以下简称"科学计数数")的指数为负数[如3.3E-9]，又分为下列几种处理方式 2.1 科学计数数的尾数为正数
	 * 2.2 科学计数数的尾数为负数
	 * 
	 * 注意：因为如果没有人为的因素(现在也不可能存在)，不可能存在科学计数数的尾数为0.000..的情况，在此忽略
	 * 
	 * @param curNumber String 需要转换的数字
	 */
	public static String formatToNum(String curNumber, int dec) {

		// 如果不是科学计数法，直接返回
		// 确认为科学计数法的初步条件：
		// 1. 不为空； 2. 不等于0；
		if (curNumber == null) {
			return curNumber;
		}

		// 检查参数是否为数字
		// 确认为科学计数法的第二个条件，可以最终确定。
		try {
			new Double(curNumber);
		} catch (NumberFormatException e) {
			return curNumber;
		}

		// 3. 有字符e； 4. 但是只有一个字符e。
		if (curNumber.trim().equals("0")
				|| curNumber.toLowerCase().indexOf("e") == -1
				|| curNumber.toLowerCase()
						.substring(curNumber.toLowerCase().indexOf("e") + 1)
						.indexOf("e") != -1) {
			boolean isScienceNumber = false;

			// 判断小数位是否和传入的参数dec一致，如果不一致，补足小数位
			int pos = curNumber.indexOf(".");
			if (pos != -1 && dec > -1) {
				if (dec == 0) { // 如果dec为0，首先进行四舍五入，去掉小数位
					curNumber = myround(
							new Double(curNumber.substring(0, pos))
									.doubleValue(),
							0)
							+ "";

					if (curNumber.indexOf(".") != -1) {
						curNumber = curNumber.substring(0,
								curNumber.indexOf("."));
					}
				} else {
					String decBit = curNumber.substring(pos); // 小数位
					int bitMis = dec - (decBit.length() - 1); // dec与小数位的差值
					if (bitMis > 0) {
						while (bitMis > 0) {
							curNumber = curNumber + "0";
							bitMis--;
						}
					} else if (bitMis < 0) {
						// 假设已经使用了myround进行了四舍五入，该场景忽略
						// 该场景不能忽略

						double tempValue = myround(
								Double.parseDouble(curNumber), dec);
						curNumber = tempValue + "";
						if (curNumber.toLowerCase().indexOf("e") < 0) {
							// 说明这是个非科学计数法表示的小数，而该小数又是经过myround()处理过的，因此它就是我们需要的结果
							// 注意myround方法并不可靠，比如myround(0.0050,3)=0.0050,因此这里要做一点处理
							int bitDifference = curNumber.substring(
									curNumber.lastIndexOf(".") + 1).length()
									- dec;
							// 条件说明:
							// -1 和+1 之间的小数，并且小数位数比我们需要保留的位数多，并且小数的最后一位为0，则砍掉这个0
							if (tempValue > -1
									&& tempValue < 1
									&& bitDifference == 1
									&& curNumber.substring(
											curNumber.length() - 1).equals("0")) {
								return curNumber.substring(0,
										curNumber.length() - 1);
							} else {
								return curNumber;
							}
						} else {
							// 经过myround()处理后，此数仍然是科学计数法，则走后面的程序代码，为达到此目的，specialFlag设置为true;
							isScienceNumber = true;
						}

					}
				}
			}

			if (!isScienceNumber) {
				return curNumber;
			}
		}

		// 判断是否为负数
		String sufTag = ""; // 负数标志
		if (curNumber.length() > 1 && curNumber.substring(0, 1).equals("-")) {
			sufTag = "-";
			curNumber = curNumber.substring(1);
		}

		// 下面进行科学计算法的转换
		String curValue = "";
		int pos = curNumber.toLowerCase().indexOf("e");
		String tail = "1"; // 尾数
		int exponent = 1; // 指数
		if (pos != -1) {
			exponent = new Integer(curNumber.substring(pos + 1)).intValue();
			tail = curNumber.substring(0, pos);
		}

		// 如果exponent为正数，说明为整数
		// boolean isLeft = false; //是否为负数
		int pointPos = tail.indexOf(".");
		int minLocation = 0; // 共有多少个小数位
		int lastMinLocation = 0; // 小数位后的数字个数
		if (pointPos > 0) {
			minLocation = tail.substring(0, pointPos).length();
			lastMinLocation = tail.substring(pointPos + 1).length();
			tail = tail.substring(0, pointPos) + tail.substring(pointPos + 1);
		}

		// 循环设置数字后的"0"
		String zeroStr = ""; // 所有的零位
		int exponentNum = exponent;
		if (exponent < 0) {
			zeroStr = "0.";
			exponentNum = -exponentNum;
		}

		// 将指数转换为'0'字符窜
		for (int i = 0; i < exponentNum; i++) {
			zeroStr += "0";
		}

		if (exponent > 0) {
			// 1.1 指数大于小数点后数字个数
			if (exponentNum > lastMinLocation) {
				curValue = tail
						+ zeroStr.substring(0, exponentNum - lastMinLocation);
			} else if (exponentNum == lastMinLocation) { // 1.2 等于
				curValue = tail;
			} else { // 1.3 小于
				int curLastIndex = minLocation + exponentNum;
				curValue = tail.substring(0, curLastIndex) + "."
						+ tail.substring(curLastIndex);
			}
		} else {
			// 2.1 指数大于小数点后数字个数
			if (exponentNum > minLocation) {
				zeroStr = zeroStr.substring(0, zeroStr.length() - minLocation);
				if (zeroStr.equals("0")) {
					zeroStr = "0.0";
				}
				curValue = zeroStr + tail;
			} else if (exponentNum == minLocation) { // 2.2 等于
				curValue = "0." + tail;
			} else { // 2.3 小于
				if (minLocation - exponentNum > 0) {
					minLocation = minLocation - exponentNum;
					curValue = tail.substring(0, minLocation) + "."
							+ tail.substring(minLocation);
				}
			}
		}

		// 判断小数位是否和传入的参数dec一致，如果不一致，补足小数位
		pos = curValue.indexOf(".");
		if (pos != -1 && dec > -1) {
			if (dec == 0) { // 如果dec为0，返回整数
				curValue = myround(
						new Double(curValue.substring(0, pos)).doubleValue(), 0)
						+ "";

				if (curValue.indexOf(".") != -1) {
					curValue = curValue.substring(0, curValue.indexOf("."));
				}
			} else {
				String decBit = curValue.substring(pos + 1); // 小数位
				int bitMis = dec - decBit.length(); // dec与小数位的差值
				if (bitMis > 0) {
					while (bitMis > 0) {
						curValue = curValue + "0";
						bitMis--;
					}
				} else if (bitMis < 0) {
					// 假设已经使用了myround进行了四舍五入，该场景忽略
					// 该场景绝对不可以忽略。因为无法预知别人如何使用你的代码。此外，即使使用了myround
					// ,仍然会出现这样的场景，比如5.0E-5.
					double temp = Double.parseDouble(curValue);
					if (temp < 1 && temp > -1 && bitMis == -1
							&& curValue.length() > 1) {
						String lastBit = curValue
								.substring(curValue.length() - 1);
						if (lastBit != null && lastBit.equals("0")) {
							curValue = curValue.substring(0,
									curValue.length() - 1);
						}
					}
				}
			}
		}

		// 没有小数点，却要求保留dec位小数
		if (pos == -1 && dec > 0) {
			curValue += ".";
			for (int i = 0; i < dec; i++) {
				curValue += "0";
			}
		}

		return sufTag + curValue;

	}

	/*
	 * 四舍五入 @param m double 需要转换的数据 @param n int 精度
	 */
	public static double myround(double m, int n) {
		return Math.round(m * Math.pow(10, n) + Math.pow(0.1, 6)
				* ((m > 0) ? 1 : -1))
				/ Math.pow(10, n);
	}

	/**
	 * 将一个double型的金额转换成大写金额，适用于千亿级数据
	 * 
	 * @param curSum
	 *            double
	 * @return java.lang.String
	 */
	public static String digitUppercase12(double curSum) {
		String _rtn = "";
		String lStr = "", rStr = "";
		final String[] chUnit = { "", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿",
				"拾", "佰", "仟" };
		// final String[] chJF = { "角", "分" };

		_rtn = formatToNum(Double.toString(curSum), 2);

		int pos = _rtn.indexOf(".");
		if (pos == -1) {
			lStr = _rtn;
			char[] CharArrayL = lStr.toCharArray();
			int l = lStr.length(); // 整数部分的长度
			if (l <= 4) {
				_rtn = charToString(CharArrayL);
			} else if (l <= 8) {
				char[] ca1 = lStr.substring(l - 4, l).toCharArray();
				_rtn = charToString(ca1);
				char[] ca2 = lStr.substring(0, l - 4).toCharArray();
				_rtn = charToString(ca2) + "万" + _rtn;
			} else if (l <= 12) {
				char[] ca1 = lStr.substring(l - 4, l).toCharArray();
				_rtn = charToString(ca1);
				char[] ca2 = lStr.substring(l - 8, l - 4).toCharArray();

				_rtn = charToString(ca2)
						+ (charToString(ca2).equals("") ? "零" : "万") + _rtn;

				char[] ca3 = lStr.substring(0, l - 8).toCharArray();
				_rtn = charToString(ca3)
						+ (charToString(ca3).equals("") ? "" : "亿") + _rtn;
			}
			lStr = _rtn + "元整"; // 整数部分拼好的串
		} else {
			rStr = _rtn.substring(pos + 1, _rtn.length()); // 小数点后面的数字
			lStr = _rtn.substring(0, pos); // 小数点前面的数字

			char[] CharArrayL = lStr.toCharArray();
			char[] CharArrayR = rStr.toCharArray();

			int l = lStr.length(); // 整数部分的长度
			if (l <= 4) {
				_rtn = charToString(CharArrayL);
			} else if (l <= 8) {
				char[] ca1 = lStr.substring(l - 4, l).toCharArray();
				_rtn = charToString(ca1);
				char[] ca2 = lStr.substring(0, l - 4).toCharArray();
				_rtn = charToString(ca2) + "万" + _rtn;
			} else if (l <= 12) {
				char[] ca1 = lStr.substring(l - 4, l).toCharArray();
				_rtn = charToString(ca1);
				char[] ca2 = lStr.substring(l - 8, l - 4).toCharArray();

				_rtn = charToString(ca2)
						+ (charToString(ca2).equals("") ? "零" : "万") + _rtn;

				char[] ca3 = lStr.substring(0, l - 8).toCharArray();
				_rtn = charToString(ca3)
						+ (charToString(ca3).equals("") ? "" : "亿") + _rtn;
			}
			if (l == 1 && lStr.equals("0")) {
				lStr = "";

			} else {
				lStr = _rtn + "元"; // 整数部分拼好的串
			}
			// 小数部分
			if (rStr.length() == 2) {
				if (rStr.equals("00"))
					rStr = "整";
				else {
					if (CharArrayR[0] == '0') { // 角部分是“0”
						if (l == 1 && lStr.equals("")) { // 整数部分为0
							rStr = getCH(CharArrayR[1]) + "分";
						} else { // 整数部分不为0
							rStr = "零" + getCH(CharArrayR[1]) + "分";
						}

					} else {
						if (CharArrayR[1] == '0')
							rStr = getCH(CharArrayR[0]) + "角";
						else
							rStr = getCH(CharArrayR[0]) + "角"
									+ getCH(CharArrayR[1]) + "分";
					}
				}
			} else {
				rStr = getCH(CharArrayR[0]) + "角";
			}
		}
		_rtn = lStr + rStr;
		if ("整".equals(_rtn)) {
			_rtn = "零元整";
		}
		return _rtn;
	}

	/**
	 * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零，适用于千亿级数据 要用到正则表达式
	 */
	public static String digitUppercase(double n) {
		String fraction[] = { "角", "分" };
		String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String unit[][] = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };

		String head = n < 0 ? "负" : "";
		n = Math.abs(n);

		String s = "";
		for (int i = 0; i < fraction.length; i++) {
			s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i])
					.replaceAll("(零.)+", "");
		}
		if (s.length() < 1) {
			s = "整";
		}
		long integerPart = (long) Math.floor(n);

		for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
			String p = "";
			for (int j = 0; j < unit[1].length && n > 0; j++) {
				p = digit[(int) (integerPart % 10)] + unit[1][j] + p;
				integerPart = integerPart / 10;
			}
			s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i]
					+ s;
		}
		return head
				+ s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "")
						.replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
	}

	public static void main(String[] args) {

		// System.out.println(formatAccount("76870188000014529"));

		// System.out.println(digitUppercase(1046418468.78));
		System.out.println(digitUppercase(1046418468.00));
		System.out.println(digitUppercase10(1046418468.00));
		System.out.println(digitUppercase(0.00));
		System.out.println(digitUppercase10(0.00));
		System.out.println(digitUppercase(0.10));
		System.out.println(digitUppercase10(0.10));
		System.out.println(digitUppercase(1046418468.78));
		System.out.println(digitUppercase10(1046418468.78));
		System.out.println(digitUppercase(42046418468.78));
		System.out.println(digitUppercase10(42046418468.78));

		System.out.println(digitUppercase(33));
		System.out.println(digitUppercase10(33));
		System.out.println(digitUppercase(987222932222.88));
		System.out.println(digitUppercase10(322222932222.88));
		System.out.println(digitUppercase(0.21));
		System.out.println(digitUppercase10(0.21));
		System.out.println(digitUppercase(3456733330.00));
		System.out.println(digitUppercase10(3456733330.00));
		System.out.println(digitUppercase10(322222932222.88));
		System.out.println(digitUppercase(322222932222.88));
		System.out.println(digitUppercase12(322222932222.88));
		System.out.println(digitUppercase12(33));
		System.out.println(digitUppercase12(42046418468.78));
		System.out.println(digitUppercase12(0.21));
		System.out.println(digitUppercase12(0.00));

	}

	/**
	 * 数值过虑多余符号,注要处理负值多")
	 * 
	 * @return returnNum 过虑后的字符串数值
	 */
	public static String numFilterSymbol(String num) {
		String returnNum = num;
		try {
			if (null != num && num.indexOf(")") > 0) {
				returnNum = num.substring(0, num.length() - 1);
			}
		} catch (Exception e) {

		}

		return returnNum;
	}

	// 将Unicode字符串转换成bool型数组
	public static boolean[] StrToBool(String input) {
		boolean[] output = Binstr16ToBool(BinstrToBinstr16(StrToBinstr(input)));
		return output;
	}

	// 将bool型数组转换成Unicode字符串
	public static String BoolToStr(boolean[] input) {
		String output = BinstrToStr(Binstr16ToBinstr(BoolToBinstr16(input)));
		return output;
	}

	// 将字符串转换成二进制字符串，以空格相隔
	public static String StrToBinstr(String str) {
		char[] strChar = str.toCharArray();// 以二进制（基数 2）无符号整数形式返回一个整数参数的字符串表示形式
		String result = "";
		for (int i = 0; i < strChar.length; i++) {
			result += Integer.toBinaryString(strChar[i]) + " ";
		}
		return result;
	}

	// 将二进制字符串转换成Unicode字符串
	public static String BinstrToStr(String binStr) {
		String[] tempStr = StrToStrArray(binStr);
		char[] tempChar = new char[tempStr.length];
		for (int i = 0; i < tempStr.length; i++) {
			tempChar[i] = BinstrToChar(tempStr[i]);
		}
		return String.valueOf(tempChar);
	}

	// 将二进制字符串格式化成全16位带空格的Binstr
	public static String BinstrToBinstr16(String input) {
		StringBuffer output = new StringBuffer();
		String[] tempStr = StrToStrArray(input);
		for (int i = 0; i < tempStr.length; i++) {
			for (int j = 16 - tempStr[i].length(); j > 0; j--)
				output.append('0');
			output.append(tempStr[i] + " ");
		}
		return output.toString();
	}

	// 将全16位带空格的Binstr转化成去0前缀的带空格Binstr
	public static String Binstr16ToBinstr(String input) {
		StringBuffer output = new StringBuffer();
		String[] tempStr = StrToStrArray(input);
		for (int i = 0; i < tempStr.length; i++) {
			for (int j = 0; j < 16; j++) {
				if (tempStr[i].charAt(j) == '1') {
					output.append(tempStr[i].substring(j) + " ");
					break;
				}
				if (j == 15 && tempStr[i].charAt(j) == '0')
					output.append("0" + " ");
			}
		}
		return output.toString();
	}

	// 二进制字串转化为boolean型数组 输入16位有空格的Binstr
	public static boolean[] Binstr16ToBool(String input) {
		String[] tempStr = StrToStrArray(input);
		boolean[] output = new boolean[tempStr.length * 16];
		for (int i = 0, j = 0; i < input.length(); i++, j++)
			if (input.charAt(i) == '1')
				output[j] = true;
			else if (input.charAt(i) == '0')
				output[j] = false;
			else
				j--;
		return output;
	}

	// boolean型数组转化为二进制字串 返回带0前缀16位有空格的Binstr
	public static String BoolToBinstr16(boolean[] input) {
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < input.length; i++) {
			if (input[i])
				output.append('1');
			else
				output.append('0');
			if ((i + 1) % 16 == 0)
				output.append(' ');
		}
		output.append(' ');
		return output.toString();
	}

	// 将二进制字符串转换为char
	public static char BinstrToChar(String binStr) {
		int[] temp = BinstrToIntArray(binStr);
		int sum = 0;
		for (int i = 0; i < temp.length; i++) {
			sum += temp[temp.length - 1 - i] << i;
		}
		return (char) sum;
	}

	// 将初始二进制字符串转换成字符串数组，以空格相隔
	public static String[] StrToStrArray(String str) {
		return str.split(" ");
	}

	// 将二进制字符串转换成int数组
	public static int[] BinstrToIntArray(String binStr) {
		char[] temp = binStr.toCharArray();
		int[] result = new int[temp.length];
		for (int i = 0; i < temp.length; i++) {
			result[i] = temp[i] - 48;
		}
		return result;
	}
	
	//判断当前系统，根据不同系统进行不同处理方式
	public static String[] StrToSysSplit(String c) {
		//判断当前系统版本
		String OS = System.getProperty("os.name").toLowerCase();
		String[] str=null;
        if(OS.indexOf("windows")>=0){
        	str = c.split("\r\n");
        }else if(OS.indexOf("linux")>=0||OS.indexOf("unix")>0){
        	str = c.split("\n");
        }
		return str;
	}

}
