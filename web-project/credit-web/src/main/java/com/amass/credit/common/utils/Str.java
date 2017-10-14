package com.amass.credit.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Str {

	private static long timeMillis = System.currentTimeMillis();
	/**
	 * 按指定格式对日期进行格式化
	 * @param date 
	 * @param format "yyMMdd"...
	 * @return
	 */
	public static String format(Date date,String format){
		SimpleDateFormat f = new SimpleDateFormat(format);
		return f.format(date);
	}
	
	//ID获取当前日期
	public synchronized static String getDate() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = f.format(new Date(timeMillis));
		return d;
	}
	
	//判断是否为空
	public static boolean IsEmpty(String str) {
		if (str == null) {
			return true;
		}
		str = str.trim();
		if ("null".equals(str.toLowerCase()) || "".equals(str)
				|| "0000-00-00".equals(str)) {
			return true;
		}
		return false;
	}
	
	//过滤指定标签
	public static String replaceImg(String str,String tag){
		
		String regEx = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
		
		Pattern p_script = Pattern.compile(regEx);
		Matcher m_script = p_script.matcher(str); 
		str = m_script.replaceAll("");

		return str;
	}
	
	//过滤所有HTML标签
	public static String replaceHtml(String str){
		
		//定义IMG标签的正则表达式
		String regEx_html = "<[^>]+>";
		
		//过滤开始标签
		Pattern p_script = Pattern.compile(regEx_html);
		Matcher m_script = p_script.matcher(str); 
		str = m_script.replaceAll("");
		
		return str;
	}

	//过滤所有JS标签
	public static String replaceJs(String str){
		
		//定义IMG标签的正则表达式
		String regEx_html = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
		
		//过滤开始标签
		Pattern p_script = Pattern.compile(regEx_html);
		Matcher m_script = p_script.matcher(str); 
		str = m_script.replaceAll("");
		
		return str;
	}
	
	//过滤所有CSS标签
	public static String replaceCss(String str){
		
		//定义CSS标签的正则表达式
		String regEx_html = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
		
		//过滤开始标签
		Pattern p_script = Pattern.compile(regEx_html);
		Matcher m_script = p_script.matcher(str); 
		str = m_script.replaceAll("");
		
		return str;
	}
	
	public static void main(String[] args)throws Exception {
	    double[] roundlatlon = getAround(22.99597f,113.827043f,1000);
	    System.out.println("min:"+roundlatlon[0]+":"+roundlatlon[1]+"  ; max:"+roundlatlon[2]+":"+roundlatlon[3]);
	}
	
	public static double[] getAround(double lat,double lon,int raidus){
	    final double PI = 3.14159265;
	    final double EARTH_RADIUS = 6378137;
	    final double RAD = Math.PI / 180.0;
        Double latitude = lat;
        Double longitude = lon;
        
        Double degree = (24901*1609)/360.0;
        double raidusMile = raidus;
        
        Double dpmLat = 1/degree;
        Double radiusLat = dpmLat*raidusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;
        
        Double mpdLng = degree*Math.cos(latitude * (PI/180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng*raidusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;
        //System.out.println("["+minLat+","+minLng+","+maxLat+","+maxLng+"]");
        return new double[]{minLat,minLng,maxLat,maxLng};
    }
	public static Date formatDate(String str,String formatSymbols){
	   SimpleDateFormat format = new SimpleDateFormat(formatSymbols);  
	   Date date = null;  
	   try {  
	     date = format.parse(str);  
	   } catch (ParseException e) {  
	     e.printStackTrace();  
	   }  
	   return date;
	}
	
	public static String getDateStr(Date date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	 /**
	 * 十六进制字符串转换成十进制数据
	 * 
	 * @param strIn
	 * @param isLow 是否低位在前
	 * @return
	 */
	public static long hex2Long(String strIn, boolean isLow) {
		long digit = 0;
		strIn = strIn.trim().toLowerCase();
		for (int i = 0; i < strIn.length()/2; i++) 
		{
			char c1 = strIn.charAt(i*2);
			char c2 = strIn.charAt(i*2+1);
			int num = 0;
			if (c1 <= '9' && c1 >= '0') 
				num = (c1 - 48)*0x10;
			else if (c1 <= 'f' && c1 >= 'a') 
				num = (c1 - 87)*0x10;
			
			if (c2 <= '9' && c2 >= '0') 
				num += c2 - 48;
			else if (c2 <= 'f' && c2 >= 'a') 
				num += c2 - 87;

			if(isLow)
				digit += num * (long)(Math.pow(0x100, i));
			else
				digit += num * (long)(Math.pow(0x100, strIn.length()/2 - i - 1));
		}
		return digit;
	}
    /**
     * 十六进制转二进制，16进制的长度必须为2的整数倍
     * @param hexString
     * @return
     */
    public static String hexString2binaryString(String hexString)  
    {  
        if (hexString == null || hexString.length() % 2 != 0)  
            return null;  
        String bString = "", tmp;  
        for (int i = 0; i < hexString.length(); i++)  
        {  
            tmp = "0000"  
                    + Integer.toBinaryString(Integer.parseInt(hexString  
                            .substring(i, i + 1), 16));  
            bString += tmp.substring(tmp.length() - 4);  
        }  
        return bString;  
    }  
    
    /**
     * 二进制转16进制,字符串长度必须为8的整数倍
     * @param bString
     * @return
     */
    public static String binaryString2hexString(String bString)  
    {  
        if (bString == null || bString.equals("") || bString.length() % 8 != 0)  
            return null;  
        StringBuffer tmp = new StringBuffer();  
        int iTmp = 0;  
        for (int i = 0; i < bString.length(); i += 4)  
        {  
            iTmp = 0;  
            for (int j = 0; j < 4; j++)  
            {  
                iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);  
            }  
            tmp.append(Integer.toHexString(iTmp));  
        }  
        return tmp.toString();  
    } 

	
	public static String[] ListToArray(List<String> list)
	{
		String[] reStrings = new String[list.size()];
		int index = 0;
		while(index < reStrings.length)
		{
			reStrings[index] = list.get(index);
			index++;
		}
		return reStrings;
	}
	
	public static String parseTime(long time){
		long h = time / 3600;
		time = time % 3600;
		long m = time / 60;
		time = time % 60;
		long s = time;
		return h + "时" + m + "分" + s + "秒";
	}
	
	/**
	 * 空字符串转为空字符
	 * @param str
	 * @return
	 */
	public static String parseNull(String str){
		if(str == null)
		{
			str = "";
		}
		return str;
	}
	public static String decodefromUrltoUTF8(String str){
        try
        {
            String result = URLDecoder.decode(str, "UTF-8");
            return result;
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
}
