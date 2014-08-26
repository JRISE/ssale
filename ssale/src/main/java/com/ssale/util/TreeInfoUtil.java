package com.ssale.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TreeInfoUtil {
	// 随机生成手机号码
	public static String ranTel() {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		int x = random.nextInt(10);
		sb.append(TELHEAD[random.nextInt(TELHEAD.length)]);
		if ((x) == 0) {
			sb.append(((10000000 + random.nextInt(100000000)) + "").replaceFirst("1", "0"));
		} else {
			sb.append((x * 10000000 + random.nextInt(10000000)) + "");
		}
		return sb.toString();
	}

	/**
	 * 
	 * @return 随机生成姓名
	 */
	public static String ranName() {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();

		sb.append(SURNAME[random.nextInt(SURNAME.length)]);
		for (int i = 0; i < random.nextInt(2) + 1; i++) {
			sb.append(ranChs());
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println(ranCardId(1960));
	}

	/**
	 * 
	 * @return 随机生成一个常用汉字
	 */
	public static String ranChs() {
		String str = null;
		Random random = new Random();

		int hightPos, lowPos; // 定义高低位
		hightPos = (176 + Math.abs(random.nextInt(39)));// 获取高位值
		lowPos = (161 + Math.abs(random.nextInt(93)));// 获取低位值

		byte[] b = new byte[2];
		b[0] = (new Integer(hightPos).byteValue());
		b[1] = (new Integer(lowPos).byteValue());

		try {
			// 转成中文
			str = new String(b, "GBk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return str;
	}

	/**
	 * 根据身份证号码获取出生年月
	 * 
	 * @param cardId
	 *            身份证号
	 * @return 出生日期
	 * @throws Exception
	 *             身份证错误信息
	 */
	public static Date getBirthday(String cardId) throws Exception {
		Date birthday;

		// 判断身份证号码长度
		if (cardId.length() != 18 && cardId.length() != 15) {
			throw new Exception("号码长度应该为15位或18位");
		}

		// ================ 数字 除最后以为都为数字 ================
		String sevenId = "";
		if (cardId.length() == 18) {
			sevenId = cardId.substring(0, 17);
		} else if (cardId.length() == 15) {
			sevenId = cardId.substring(0, 6) + "19" + cardId.substring(6, 15);
		}
		if (isNumeric(sevenId) == false) {
			throw new Exception("15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。");
		}

		// // 验证校验位
		// if (cardId.length() == 18) {
		// String verify = getVerify(sevenId);
		// if (!cardId.substring(17, 18).toLowerCase().equals(verify)) {
		// throw new Exception("身份证校验位有误");
		// }
		// }
		// 根据身份证号获取出生年月
		String dateString;
		dateString = sevenId.substring(6, 10);
		dateString = dateString + "-" + sevenId.substring(10, 12);
		dateString = dateString + "-" + sevenId.substring(12, 14);
		birthday = StringToDate(dateString);
		return birthday;

	}

	/**
	 * 对身份证号进行校验
	 * 
	 * @param cardId
	 *            身份证号
	 * @return 验证结果,如果验证正确返回空串
	 */
	public static String checkCardId(String cardId) {

		// 判断身份证号码长度
		if (cardId.length() != 18 && cardId.length() != 15) {
			return "号码长度应该为15位或18位";
		}
		// ================ 数字 除最后以为都为数字 ================
		String sevenId = "";
		if (cardId.length() == 18) {
			sevenId = cardId.substring(0, 17);
		} else if (cardId.length() == 15) {
			sevenId = cardId.substring(0, 6) + "19" + cardId.substring(6, 15);
		}
		if (isNumeric(sevenId) == false) {
			return "15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
		}
		// 验证校验位
		if (cardId.length() == 18) {
			String verify = getVerify(sevenId);
			if (!cardId.substring(17, 18).toLowerCase().equals(verify)) {
				return "身份证校验位有误";
			}
		}
		int year = Integer.parseInt(sevenId.substring(6, 10));
		int month = Integer.parseInt(sevenId.substring(10, 12));
		int day = Integer.parseInt(sevenId.substring(12, 14));
		if (month < 1 || month > 12 || day < 1 || day > 31
				|| ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30)
				|| (month == 2 && (((year) % 4 > 0 && day > 28) || day > 29))) {
			return "身份证号中出生日期有误";
		}
		return null;
	}

	/**
	 * 根据身份证号获取性别
	 * 
	 * @param cardId
	 *            身份证号
	 * @return 性别
	 */
	public static String getGender(String cardId) {
		String gender = null;
		try {
			if (cardId.length() == 18) {
				gender = Integer.parseInt(cardId.substring(16, 17)) % 2 == 0 ? "女" : "男";
			} else if (cardId.length() == 15) {
				gender = Integer.parseInt(cardId.substring(14, 15)) % 2 == 0 ? "女" : "男";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
		return gender;
	}

	/**
	 * 15位身份证号转18位
	 * 
	 * @param cardId
	 * @return
	 * @create by soft at 2009-7-9
	 */
	public static String to18CardId(String cardId) {
		if (cardId.length() != 15)
			return cardId;
		cardId = cardId.substring(0, 6) + "19" + cardId.substring(6, 15);
		cardId = cardId + getVerify(cardId);
		return cardId;
	}

	/**
	 * 
	 * @param year 从1950年开始,第year年以内出生
	 * @return 18位身份证号
	 */
	public static String ranCardId(int yearLen){
		String ret = "";
			String propFile = "areacode.properties";
			// 定义一个properties对象
			Properties properties = new Properties();
			// 读取properties
			InputStream file = TreeInfoUtil.class.getClassLoader().getResourceAsStream(propFile);
			// 加载properties文件
			try {
				// properties.load(new InputStreamReader(file, "utf-8"));
				properties.load(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Object[] code = properties.keySet().toArray();
			int size = code.length;
			Random random = new Random();
			String areaCode = (String) code[random.nextInt(size)];
			int year = 1920 + random.nextInt(yearLen);
			int month = random.nextInt(11);
			if (month == 0)
				month = 12;
			int day = 0;
			while (true) {
				day = random.nextInt(31);
				if (!((day == 0 || (month == 4 || month == 6 || month == 9 || month == 11)
						&& day > 30) || (month == 2 && (((year) % 4 > 0 && day > 28) || day > 29)))) {
					break;
				}
			}
			String birthday = String.valueOf(year * 10000 + month * 100 + day);
			String randomCode = String.valueOf(1000 + random.nextInt(999)).substring(1);
			String verify = getVerify(areaCode + birthday + randomCode);
			ret = areaCode + birthday + randomCode + verify;

		return ret;
	}

	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据17位身份证号获取验证码
	 * 
	 * @param cardId
	 *            17位身份证号
	 * @return 验证码
	 */
	private static String getVerify(String cardId) {
		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5",
				"8", "4", "2" };
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(cardId.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];

		return strVerifyCode;
	}

	/**
	 * 将"yyyy-MM-dd"格式的日期字符串转为java.util.Date类型
	 * 
	 * @param strDate
	 *            日期字符串
	 * @return 时间类型
	 * @create by soft at 2009-7-9
	 */
	private static Date StringToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String[] array = strDate.split("-");
			if (array.length != 3)
				throw new Exception();
			int year = Integer.parseInt(array[0]);
			int month = Integer.parseInt(array[1]);
			int day = Integer.parseInt(array[2]);
			if (month < 1 || month > 12 || day < 1 || day > 31
					|| ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30)
					|| (month == 2 && (((year) % 4 > 0 && day > 28) || day > 29)))
				throw new Exception();
			return formatter.parse(strDate);

		} catch (Exception e) {
			throw new RuntimeException("日期错误");
		}
	}

	
	/**
	 * 生成随机身份证号
	 * 
	 * @param num
	 *            个数
	 * @param year
	 *            从1920年开始第几年出生
	 * @create by szq at 2009-8-19
	 */
	/*
	public static String genCardId(int num, int year) {
		return genCardId(num, "d:\\cardId.txt", year);
	}
	*/
	
	/**
	 * 生成随机身份证号
	 * 
	 * @param num
	 *            个数
	 * @param filePath
	 *            文件路径
	 * @param year
	 *            从1920年开始第几年出生
	 * @create by szq at 2009-8-19
	 */
	/*public static String genCardId(int num, String filePath, int yearLen) {
		String ret = "";
		FileWriter fw = null;
		try {
			// 定义一个properties文件的名字
			fw = new FileWriter(filePath);

			String propFile = "areacode.properties";
			// 定义一个properties对象
			Properties properties = new Properties();
			// 读取properties
			InputStream file = CardId.class.getClassLoader().getResourceAsStream(propFile);
			// 加载properties文件
			// properties.load(new InputStreamReader(file, "utf-8"));
			properties.load(file);
			Object[] code = properties.keySet().toArray();
			int size = code.length;
			Random random = new Random();
			for (int i = 0; i < num; i++) {
				String areaCode = (String) code[random.nextInt(size)];
				int year = 1920 + random.nextInt(yearLen);
				int month = random.nextInt(11);
				if (month == 0)
					month = 12;
				int day = 0;
				while (true) {
					day = random.nextInt(31);
					if (!((day == 0 || (month == 4 || month == 6 || month == 9 || month == 11)
							&& day > 30) || (month == 2 && (((year) % 4 > 0 && day > 28) || day > 29)))) {
						break;
					}
				}
				String birthday = String.valueOf(year * 10000 + month * 100 + day);
				String randomCode = String.valueOf(1000 + random.nextInt(999)).substring(1);
				String verify = getVerify(areaCode + birthday + randomCode);
				ret = areaCode + birthday + randomCode + verify;
				fw.write(ret);
				fw.write("\r\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	*/

	// 常用姓氏
	private static final String[] SURNAME = { "\u8D75", "\u94B1", "\u5B59", "\u674E", "\u5468",
			"\u5434", "\u90D1", "\u738B", "\u51AF", "\u9648", "\u891A", "\u536B", "\u848B",
			"\u6C88", "\u97E9", "\u6768", "\u6731", "\u79E6", "\u5C24", "\u8BB8", "\u4F55",
			"\u5415", "\u65BD", "\u5F20", "\u5B54", "\u66F9", "\u4E25", "\u534E", "\u91D1",
			"\u9B4F", "\u9676", "\u59DC", "\u621A", "\u8C22", "\u90B9", "\u55BB", "\u67CF",
			"\u6C34", "\u7AA6", "\u7AE0", "\u4E91", "\u82CF", "\u6F58", "\u845B", "\u595A",
			"\u8303", "\u5F6D", "\u90CE", "\u9C81", "\u97E6", "\u660C", "\u9A6C", "\u82D7",
			"\u51E4", "\u82B1", "\u65B9", "\u4FDE", "\u4EFB", "\u8881", "\u67F3", "\u9146",
			"\u9C8D", "\u53F2", "\u5510", "\u8D39", "\u5EC9", "\u5C91", "\u859B", "\u96F7",
			"\u8D3A", "\u502A", "\u6C64", "\u6ED5", "\u6BB7", "\u7F57", "\u6BD5", "\u90DD",
			"\u90AC", "\u5B89", "\u5E38", "\u4E50", "\u4E8E", "\u65F6", "\u5085", "\u76AE",
			"\u535E", "\u9F50", "\u5EB7", "\u4F0D", "\u4F59", "\u5143", "\u535C", "\u987E",
			"\u5B5F", "\u5E73", "\u9EC4", "\u548C", "\u7A46", "\u8427", "\u5C39", "\u59DA",
			"\u90B5", "\u6E5B", "\u6C6A", "\u7941", "\u6BDB", "\u79B9", "\u72C4", "\u7C73",
			"\u8D1D", "\u660E", "\u81E7", "\u8BA1", "\u4F0F", "\u6210", "\u6234", "\u8C08",
			"\u5B8B", "\u8305", "\u5E9E", "\u718A", "\u7EAA", "\u8212", "\u5C48", "\u9879",
			"\u795D", "\u8463", "\u6881", "\u675C", "\u962E", "\u84DD", "\u95F5", "\u5E2D",
			"\u5B63", "\u9EBB", "\u5F3A", "\u8D3E", "\u8DEF", "\u5A04", "\u5371", "\u6C5F",
			"\u7AE5", "\u989C", "\u90ED", "\u6885", "\u76DB", "\u6797", "\u5201", "\u953A",
			"\u5F90", "\u90B1", "\u9A86", "\u9AD8", "\u590F", "\u8521", "\u7530", "\u6A0A",
			"\u80E1", "\u51CC", "\u970D", "\u865E", "\u4E07", "\u652F", "\u67EF", "\u661D",
			"\u7BA1", "\u5362", "\u83AB", "\u7ECF", "\u623F", "\u88D8", "\u7F2A", "\u5E72",
			"\u89E3", "\u5E94", "\u5B97", "\u4E01", "\u5BA3", "\u8D32", "\u9093", "\u53F8\u9A6C",
			"\u4E0A\u5B98", "\u6B27\u9633", "\u590F\u4FAF", "\u8BF8\u845B", "\u95FB\u4EBA",
			"\u4E1C\u65B9", "\u8D6B\u8FDE", "\u7687\u752B", "\u5C09\u8FDF", "\u516C\u7F8A",
			"\u6FB9\u53F0", "\u516C\u51B6", "\u5B97\u653F", "\u6FEE\u9633", "\u6DF3\u4E8E",
			"\u5355\u4E8E", "\u592A\u53D4", "\u7533\u5C60", "\u516C\u5B59", "\u4EF2\u5B59",
			"\u8F69\u8F95", "\u4EE4\u72D0", "\u949F\u79BB", "\u5B87\u6587", "\u957F\u5B59",
			"\u6155\u5BB9", "\u7AE0\u4F73", "\u90A3\u62C9", "\u4E4C\u96C5", "\u8303\u59DC",
			"\u78A7\u9C81", "\u5F20\u5ED6", "\u5F20\u7B80", "\u56FE\u95E8", "\u516C\u53D4",
			"\u4E4C\u5B59", "\u5B8C\u989C", "\u9A6C\u4F73", "\u5BCC\u5BDF", "\u8D39\u83AB" };

	// 手机号码号段(去掉145号段)
	private static final String[] TELHEAD = { "139", "138", "137", "136", "135", "134", "147",
			"150", "151", "152", "157", "158", "159", "178", "182", "183", "184", "187", "188",
			"130", "131", "132", "155", "156", "185", "186", "176", "133", "153", "177", "180",
			"181", "189", };

}
