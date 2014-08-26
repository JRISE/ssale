package com.ssale.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TreeUtil {

	/**
	 * 把ids的数组转成String字符串,中间用逗号分隔
	 * 
	 * @param ids
	 *            数组
	 * @return
	 */
	public static String arrays2String(Serializable[] ids) {
		StringBuffer stringBuffer = new StringBuffer();
		if (ids instanceof String[]) {
			for (int i = 0; i < ids.length; i++) {
				if (i == ids.length - 1) {
					stringBuffer.append("'" + ids[i] + "'");

				} else {
					stringBuffer.append("'" + ids[i] + "'" + ",");
				}
			}
		} else {
			for (int i = 0; i < ids.length; i++) {
				if (i == ids.length - 1) {
					stringBuffer.append(ids[i]);

				} else {
					stringBuffer.append(ids[i] + ",");
				}
			}
		}
		return stringBuffer.toString();
	}

	/**
	 * 通过解析Tomcat的servlet.xml文件,找到虚拟路径对应的物理文件夹files路径
	 * 
	 * @return 获取资源存放路径
	 */
	public static String getWebSourcePath() {
		String path = getClassesPath();
		Element element = null;
		int index = path.indexOf("/webapps/");
		if (index > 0) {
			Document document = getDocument(path.substring(0, path.indexOf("/webapps/"))
					+ "/conf/server.xml");
			if (document != null) {
				element = (Element) document.getRootElement().elements("Service").get(0);
				element = (Element) element.elements("Engine").get(0);
				element = (Element) element.elements("Host").get(0);
				element = (Element) element.elements("Context").get(0);
			}
		}
		if (element != null) {
			path = element.attributeValue("docBase") + "/";
		} else {
			// 手动指定资源文件路径
			path = "D:/app/files/";
		}
		return path.replaceAll("files/", "");
	}

	/**
	 * 
	 * @return web应用中classes的物理路径地址
	 */
	public static String getClassesPath() {
		return TreeUtil.class.getClassLoader().getResource("").toString().replaceAll("file:/", "");
	}

	/**
	 * 把输入的数字转成固定长度的字符串
	 * 
	 * @param num
	 *            数字
	 * @param bit
	 *            输入的字符串长度
	 * @return
	 * @throws CastException
	 */
	public static String castFixedBit(Integer num, Integer bit) {
		if (bit < 1) {
			try {
				throw new Exception("错误:指定的位数必须大于或等于1");
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
		if (num < 0) {
			num = Math.abs(num);
			System.out.println("警告:输入数字为负,已转绝对值");
		}
		int j = bit - (num + "").length();
		if (j < 0) {
			System.out.println("警告:数字的位数已经大于指定的长度,返回值已经舍弃高位");
			return ("" + num).substring(Math.abs(j));
		}
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < j; i++) {
			sb.append(0);
		}
		return sb.toString() + num;
	}

	/**
	 * @param num
	 *            用户当前浏览页码
	 * @param pageNum
	 *            需要显示的所有数据条数
	 * @param count
	 *            所有数据的条数
	 * @param pageSize
	 *            每页显示的最大数据条数
	 * @return 当前需要显示的页码
	 */
	public static int pageNum(String num, String pageNum, int count, int pageSize) {
		int temp = 1;
		int pnum = 0;
		if (num != null && !"".equals(num)) {
			temp = Integer.parseInt(num);
		}
		// 对用户输入的pageNum进行校验
		if (pageNum != null && !"".equals(pageNum)) {
			try {
				pnum = Integer.parseInt(pageNum);
			} catch (Exception e) {
				System.out.println("跳转页面输入错误");
			}
		} else {
			pnum = temp;
		}
		// 分页查询该页显示多少数据
		if (pnum <= 0 || count <= (pnum - 1) * pageSize) {
			pnum = temp;
		}
		if (count > 0 && count == (pnum - 1) * pageSize) {
			pnum = pnum - 1;
		}

		return pnum;
	}

	/**
	 * 通过算法获取摘要信息。摘要信息是安全的单向哈希函数，固定长度的哈希值。 Nio版本,效率高
	 * 
	 * @param sourcePath
	 *            文件路径,例如d:/pom.xml
	 * @return 固定长度的哈希值
	 */
	public static String getMD5Hash(String sourcePath) {
		return _getHash(sourcePath, MD5);
	}

	/**
	 * 判断两个文件是否相同. 用MD5校验码进行验证,文件越大速度越慢.(200M文件需要1.5秒左右,具体看机器)
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @return
	 */
	public static boolean isSameFile(String sourceFile, String targetFile) {
		if (!TreeJudgement.isFileExists(sourceFile, targetFile)) {
			return false;
		}
		String source_md5 = getMD5Hash(sourceFile);
		String target_md5 = getMD5Hash(targetFile);
		if (source_md5 == target_md5) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断两个文件是否相同. 用最后修改时间比较.速度很快.
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @return
	 */
	public static boolean isSameTimeFile(String sourceFile, String targetFile) {
		if (!TreeJudgement.isFileExists(sourceFile, targetFile)) {
			return false;
		}
		long source = new File(sourceFile).lastModified();
		long target = new File(targetFile).lastModified();
		if (source == target) {
			return true;
		} else {
			return false;
		}
	}

	public static void copyFile(String sourcePath, String targetPath) {
		if (!TreeJudgement.isFileExists(sourcePath)) {
			return;
		} else if ((new File(sourcePath)).isFile()) {
			_copyFile(sourcePath, targetPath);
		} else {
			_copyFolder(sourcePath, targetPath);
		}

	}

	/**
	 * @param path
	 *            xml的路径
	 * 
	 * @return xml的Document.
	 */
	public static Document getDocument(String path) {
		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			saxReader.setEncoding("UTF-8");
			document = saxReader.read(path);
			return document;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static final String MD5 = "MD5";
	// private static final String SHA = "SHA";

	private static final char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
			'b', 'c', 'd', 'e', 'f' };

	/**
	 * 复制单个文件
	 * 
	 * @param sourcePath
	 *            源文件路径,例如d:/pom.xml
	 * @param targetPath
	 *            目标文件路径,例如d:/pom1.xml
	 */
	private static void _copyFile(String sourcePath, String targetPath) {
		InputStream in = null;
		FileOutputStream out = null;
		try {
			int byteread = 0;
			in = new FileInputStream(sourcePath);
			out = new FileOutputStream(targetPath);
			byte[] buffer = new byte[1024 * 4];
			while ((byteread = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
			out.flush();
		} catch (Exception e) {
			System.out.println("复制文件操作出错");
			e.printStackTrace();

		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 复制整个文件夹
	 * 
	 * @param sourcePath
	 *            源文件夹路径,例如d:/yun
	 * @param targetPath
	 *            源文件夹路径,例如d:/yun1
	 */
	private static void _copyFolder(String sourcePath, String targetPath) {
		// 如果文件夹不存在 则建立新文件夹
		(new File(targetPath)).mkdirs();
		File f = new File(sourcePath);
		String[] files = f.list();
		File temp = null;
		for (int i = 0; i < files.length; i++) {
			if (sourcePath.endsWith(File.separator)) {
				temp = new File(sourcePath + files[i]);
			} else {
				temp = new File(sourcePath + File.separator + files[i]);
			}

			// 如果是文件,则进行复制
			if (temp.isFile()) {
				_copyFile(temp.getPath(), targetPath + "/" + (temp.getName()).toString());
			}

			// 如果是子文件夹则递归
			if (temp.isDirectory()) {
				_copyFolder(sourcePath + "/" + files[i], targetPath + "/" + files[i]);
			}
		}
	}

	/**
	 * 通过算法获取摘要信息。摘要信息是安全的单向哈希函数，固定长度的哈希值。 Nio版本,效率高
	 * 
	 * @param sourcePath
	 *            文件路径,例如d:/pom.xml
	 * @param hashType
	 *            算法类型,如 MD5 或 SHA 算法
	 * @return 固定长度的哈希值
	 */
	private static String _getHash(String sourcePath, String hashType) {
		if (!TreeJudgement.isFileExists(sourcePath)) {
			return "";
		}
		FileInputStream fStream = null;
		String hash = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance(hashType);
			fStream = new FileInputStream(sourcePath);
			FileChannel fChannel = fStream.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(64 * 1024);
			for (int count = fChannel.read(buffer); count != -1; count = fChannel.read(buffer)) {
				buffer.flip();
				md5.update(buffer);
				if (!buffer.hasRemaining()) {
					// System.out.println("count:"+count);
					buffer.clear();
				}
			}
			hash = _toHexString(md5.digest());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fStream != null)
					fStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return hash;
	}

	/**
	 * 字节数组转成16位显示
	 * 
	 * @param b
	 *            字节数组
	 * @return
	 */
	private static String _toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return sb.toString();
	}

}
