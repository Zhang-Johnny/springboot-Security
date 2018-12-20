package org.sang.common;

import org.sang.common.tool.GsonUtils;
import com.google.common.base.CaseFormat;
import org.apache.commons.collections.map.LinkedMap;
import org.sang.common.tool.Strings;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by woniu on 2015/4/27 0027.
 */
public class Utils {
    private Utils(){}

    public final static Logger logger = LoggerFactory.getLogger(Utils.class);
    //全是数字
    private static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");
    //长度18位并且字母和数字都有
    private static Pattern LETTER_NUMBER_18_PATTERN = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{18}$");
    //全是数字，只支持正负数，不支持“，”号格式的千位分割数字和小数
    private static Pattern ZF_NUMBER_PATTERN = Pattern.compile("[-]?[0-9]*");
    //是否数字，支持正负数，“，”号格式的千位分割数字
    private static Pattern ZC_NUMBER_PATTERN = Pattern.compile("[-]?[0-9]*[.]?[0-9]*");
    //是否数字，支持正负数，“，”号格式的千位分割数字,和末尾%号
    private static Pattern ZC_NUMBER_PATTERN_S = Pattern.compile("[-]?[0-9]*[.]?[0-9]*[%]?");
    //手机号码
    private static Pattern IS_IPHONE = Pattern.compile("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\\d{8})$");
    //是否邮箱
    private static Pattern IS_EMAILE = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
    //是否中文
    private static Pattern IS_ZHONGWEN = Pattern.compile("[\u4e00-\u9fa5]");
    public static void main(String[] args) {
            String abc = "中国石化销售有限公司上海奉贤石油分公司第四加油站";
//            String abc = "中国纺织科学研究院纤维新材料分院";
//        abc = abc.replaceAll("(\\s)+", "");
//        System.out.println(abc.replaceAll("(\\s)+", ""));
        System.out.println(extractStr(abc, ".*(公司|研究院)"));//0.00
//            System.out.println(extractStr(abc, "([\\u4e00-\\u9fa5]+)"));//0.00


//        String regex = "^(—|-|\\s)*$";
//        System.out.println(isRegex("— ----—    ", regex));
    }
    /**
     * 根据正则提取字符串 （第一次出现的字符串）
     * @param str
     * @param regex
     * @return
     */
    public static String extractStr(String str, String regex){
        try {
            Pattern compile = Pattern.compile(regex);
            Matcher matcher = compile.matcher(str);
            matcher.find();
            return matcher.group();//提取匹配到的结果

//            matcher.find(); // 提取第二次出现
//            String string2 = matcher.group();//提取匹配到的结果
//            System.out.println(string2);//0.00
        }catch (Exception e){
        }
        return "";
    }

    /**
     * 纯汉字
     * @param str
     * @return
     */
    public static boolean isCN(String str) {
        return isRegex(str, "[\\u4e00-\\u9fa5]*");
    }

    /**
     * 符合指定正则
     * @param str
     * @return
     */
    public static boolean isRegex(String str, String regex) {
        try{
            Matcher ma;
            Pattern pa = Pattern.compile(regex);
            ma = pa.matcher(str);
            return ma.matches();
        }catch (Exception e){}
        return false;
    }

    /**
     * 对字符串md5加密(大写+ 数字)
     *
     * @param source 传入要加密的字符串
     * @return  MD5加密后的字符串
     */
    public static String MD5(String source) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        try {
            byte[] btInput = source.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 首字母大写
     * @param str aa_bb
     * @return Aa_bb
     */
    public static String firstCharOnlyToUpper(String str){
        if(isBlankEmpty(str)){
            return str;
        }
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, str);
    }

    /**
     * 下划线转成驼峰命名
     * @param str aa_bb
     * @return aaBb
     */
    public static String lineToHump(String str){
        if(isBlankEmpty(str)){
            return str;
        }
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str);
    }

    /**
     * 驼峰转成下划线命名
     * @param str aaBb
     * @return aa_bb
     */
    public static String humpToLine(String str){
        if(isBlankEmpty(str)){
            return str;
        }
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
    }

    /**
     * 使用Velocity进行字符串占位符替换
     * @param content 带占位符的字符串（占位符关键字${} 如：哈哈哈${a},jfdkjfd${b}）
     * @param contextParams 值
     * @return
     */
    public static String replaceStr(String content, Map<String, Object> contextParams){
        if(isBlankEmpty(content) || contextParams == null || contextParams.isEmpty()){
            return content;
        }
        VelocityContext context = new VelocityContext(contextParams);
        StringWriter writer = new StringWriter();
        Velocity.evaluate(context, writer, "", content);
        return writer.toString();
    }

    /**
     * 生成随机字符串
     * @param base 随机的字符范围，默认是英文小写和数字
     * @param length 字符串长度
     * @return
     */
    public static String getRandomString(String base, int length) { //length表示生成字符串的长度
        if(isBlankEmpty(base)){
            base = "abcdefghijklmnopqrstuvwxyz0123456789";
        }
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成token  暂时直接用UUID生成的
     * @param login
     * @return
     */
    public static String generateToken(String login){
        String uid = UUID.randomUUID().toString();
        uid = uid.toUpperCase();
        uid = uid.replaceAll("-", "");
        return uid;
    }

    public static String generateCode(Integer length){
        int le = 6;
        if(length != null && length > 0){
            le = length;
        }
        String uid = UUID.randomUUID().toString();
        uid = uid.toUpperCase();
        uid = uid.replaceAll("-", "");
        if(le > uid.length()){
            le = uid.length();
        }
        uid = uid.substring(0, le);
        return uid;
    }

    /**
     * 生成唯一编号， yyyyMMdd + 8位毫秒数
     * @param length 长度，默认32位, 暂时没使用
     * @return
     */
    public static synchronized String generateNumber(Integer length){
        int le = 32;
        if(length != null && length > 0){
            le = length;
        }
        try{
            Thread.sleep(new Long(1));
        }catch (Exception e){
        }
        // 生成编号
        Date date = new Date();
        String number = date.getTime() + "";
        number = number.substring(5);
        String dateStr = formatDate(date, "yyyyMMdd");
        return dateStr + number;
    }

    /**
     * 生成唯一订单编号， 前缀+yyyyMMdd + 8位毫秒数
     * @param prefix 前缀（S 线上标准报告订单,M 线上免费报告订单,X 线下报告订单,J 旧报告订单,W WEBAPI报告订单）
     * @return
     */
    public static String generateOrderNo(String prefix){
        if(isBlankEmpty(prefix)){
            prefix = "";
        }
        return prefix + generateNumber(30);
    }

    /**
     * 获取String类型，如果是null返回""
     * @param obj
     * @return
     */
    public static String toString(Object obj){
        if(obj != null){
            return obj.toString();
        }
        return "";
    }

    /**
     * 移除前后空格，支持html的&nbsp;
     * @param obj
     * @return
     */
    public static String trimHtml(Object obj){
        if(obj == null)
            return null;
       String str = toString(obj).replaceAll("&nbsp;"," ").replaceAll(" ", " ");
        str = replaceEmptyAll(str, " ");
       return str.trim();
    }

    /**
     * 替换字符串中的空白为指定字符
     * @param str
     * @param newStr
     * @return
     */
    public static String replaceEmptyAll(String str, String newStr){
        if(isBlankEmpty(str)){
            return str;
        }
        StringBuffer sb = new StringBuffer();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(c <= ' '){
                sb.append(newStr);
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * null或空字符或全是空格
     */
    public static boolean isBlankEmpty(Object obj){
        return Strings.isBlank(toString(obj).trim());
    }
    /*
    *null或空字符串或者全是空格统一处理返回空字符串
    * **/
    public static Object reStringEmpty(Object obj){
         if(toString(obj).trim().equalsIgnoreCase("null") || Strings.isBlank(toString(obj).trim())){
             return "";
         }
        return obj;
    }
    /**
     * 根据指定字符串的结束索引开始，截取原字符串
     * 如：sourceStr=abcde ; str=bc ;返回：de
     * @param sourceStr
     * @param str
     * @return
     */
    public static String indexOfSubString(String sourceStr, String str){
        if(!isBlankEmpty(sourceStr) && !isBlankEmpty(str) && sourceStr.indexOf(str) >= 0){
            int startIndex = sourceStr.indexOf(str) + str.length();
            return sourceStr.substring(startIndex);
        }
        return null;
    }

    /**
     * 修改字符串到指定的长度，如果不够，以fill进行填充
     * @param str 字符串
     * @param length  返回的长度
     * @param fill  填充字符，，必须一个长度 ，默认填充字符"0"
     * @param isBegin 是否填充在开头，，否则就填充在结尾
     * @return 填充后的字符串
     */
    public static String convertStrLength(String str, int length, String fill, boolean isBegin){
        String result = str;
        if(result == null){
            result = "";
        }
        if(fill == null){
            fill = "0";
        }
        if(result.length() < length){
            StringBuffer sb = new StringBuffer();
            for (int i = result.length(); i < length; i++) {
                sb.append(fill);
            }
            if(isBegin){
                result = sb.toString() + result;
            }else{
                result = result + sb.toString();
            }
        }
        return result;
    }

    /**
     * 以指定的字符，拼接数组，排除数组中null和""
     * @param noDuplicate 是否排除重复
     * @param separator 字符
     * @param args 数组
     * @return
     */
    public static String joinExcludeBlank(boolean noDuplicate, String separator, String... args){
        if(separator == null) {
            separator = "";
        }
        if(args == null) {
            return null;
        }
        int bufSize = args.length;
        if(bufSize <= 0) {
            return "";
        }
        if(noDuplicate){
            Set<String> set = new LinkedHashSet<>(Arrays.asList(args));
            args = set.toArray(new String[0]);
        }
        bufSize *= (args[0] == null?16:args[0].toString().length()) + separator.length();
        StringBuffer buf = new StringBuffer(bufSize);
        for(int i=0; i < args.length; i++){
            if(!isBlankEmpty(args[i])) {
                if(!isBlankEmpty(buf.toString())) {
                    buf.append(separator);
                }
                buf.append(args[i]);
            }
        }
        return buf.toString();
    }
    /**
     * 以指定的字符，拼接数组，排除数组中null和""
     * @param noDuplicate 是否排除重复
     * @param separator 字符
     * @param collection 数组
     * @return
     */
    public static String joinExcludeBlank(boolean noDuplicate, String separator, Collection<String> collection){
        if(separator == null) {
            separator = "";
        }
        if(collection == null) {
            return null;
        }
        int bufSize = collection.size();
        if(bufSize <= 0) {
            return "";
        }
        String[] args = collection.toArray(new String[0]);
        return  joinExcludeBlank(noDuplicate, separator, args);
    }
    /**
     * 全角转半角:
     * @param fullStr
     * @return
     */
    public static String full2Half(String fullStr) {
        if(isBlankEmpty(fullStr)){
            return fullStr;
        }
        char[] c = fullStr.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= 65281 && c[i] <= 65374) {
                c[i] = (char) (c[i] - 65248);
            } else if (c[i] == 12288) { // 空格
                c[i] = (char) 32;
            }
        }
        return new String(c);
    }

    /**
     * 半角转全角
     * @param halfStr
     * @return
     */
    public static String half2Full(String halfStr) {
        if(isBlankEmpty(halfStr)){
            return halfStr;
        }
        char[] c = halfStr.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 32) {
                c[i] = (char) 12288;
            } else if (c[i] < 127) {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }


    /**
     * 获取布尔类型
     * @param obj
     * @return
     */
    public static Boolean toBoolean(Object obj){
        if(isBlankEmpty(obj)){
            return false;
        }
        return Boolean.valueOf(obj.toString());
    }

    /**
     * 获取Integer类型，如果是null或转换失败，返回0
     * @param obj
     * @return
     */
    public static Integer toInteger(Object obj){
        Integer result = 0;
        if(obj != null){
            try{
                BigDecimal bigDecimal = toBigDecimalRigor(obj);
                if(!isZeroOrEmpty(bigDecimal)){
                    return bigDecimal.intValue();
                }
//                return Integer.valueOf(obj.toString());
            }catch (NumberFormatException e){}
        }
        return result;
    }
    /**
     * 获取Integer类型，如果是null或转换失败，返回null
     * @param obj
     * @return
     */
    public static Integer toIntegerRigor(Object obj){
        Integer result = null;
        if(obj != null){
            try{
                BigDecimal bigDecimal = toBigDecimalRigor(obj);
                if(bigDecimal != null){
                    return bigDecimal.intValue();
                }
            }catch (NumberFormatException e){}
        }
        return result;
    }
    /**
     * 获取Long类型，如果是null或转换失败，返回0
     * @param obj
     * @return
     */
    public static Long toLong(Object obj){
        Long result = new Long(0);
        if(obj != null){
            try{
                BigDecimal bigDecimal = toBigDecimalRigor(obj);
                if(!isZeroOrEmpty(bigDecimal)){
                    return bigDecimal.longValue();
                }
//                return Long.valueOf(obj.toString());
            }catch (NumberFormatException e){}
        }
        return result;
    }
    /**
     * 获取Long类型，如果是null或转换失败，返回null
     * @param obj
     * @return
     */
    public static Long toLongRigor(Object obj){
        Long result = null;
        if(obj != null){
            try{
                BigDecimal bigDecimal = toBigDecimalRigor(obj);
                if(bigDecimal != null){
                    return bigDecimal.longValue();
                }
//                return Long.valueOf(obj.toString());
            }catch (NumberFormatException e){}
        }
        return result;
    }

    public static Double toDouble(Object obj){
        BigDecimal bigDecimal = toBigDecimalRigor(obj);
        if(bigDecimal != null){
            return bigDecimal.doubleValue();
        }
        return null;
    }

    /**
     * 获取BigDecimal类型，如果存在%,直接去掉，传再,千位分隔符，直接去掉
     * 如果是null或转换失败，返回0
     * @param obj
     * @return
     */
    public static BigDecimal toBigDecimal(Object obj){
        BigDecimal result = BigDecimal.ZERO;
        if(obj != null){
            try{
                String str = obj.toString();
                if(str.contains("%")){
                    str = str.replaceAll("%", "");
//                    str = divide(str, 100, 2);
                }
                if(str.contains(",")){
                    str = str.replaceAll(",","");
                }
                return new BigDecimal(str);
            }catch (NumberFormatException e){}
        }
        return result;
    }
    /**
     * 获取BigDecimal类型，精确转换，如果格式错误，返回null
     * @param obj
     * @return
     */
    public static BigDecimal toBigDecimalRigor(Object obj){
        BigDecimal result = null;
        if(obj != null){
            try{
                String str = obj.toString();
                return new BigDecimal(str);
            }catch (NumberFormatException e){}
        }
        return result;
    }

    /**
     * 是否是数字并且值等于0或者空字符，
     * @param obj
     * @return
     */
    public static boolean isZeroOrEmpty(Object obj){
        if(isBlankEmpty(obj)){
            return true;
        }
        BigDecimal result;
        try{
            result = toBigDecimal(obj);
            if(result.compareTo(BigDecimal.ZERO) == 0){
                return true;
            }
        }catch (NumberFormatException e){ }
        return false;
    }

    /**
     * 比较两个数字的大小，必须是数字字符串，否则返回0
     * @param num1
     * @param num2
     * @return 0相等，1 num1>num2， -1 num1 < num2
     */
    public static int compareBigDecimal(Object num1, Object num2){
        BigDecimal bd1 = toBigDecimal(num1);
        BigDecimal bd2 = toBigDecimal(num2);
        return bd1.compareTo(bd2);
    }

    /**
     * 是否注册号(全是数字，或者 长度18位并且字母和数字都有)
     * @param str
     * @return
     */
    @Deprecated
    public static boolean isRegNo(String str)
    {
        boolean result = false;
        try{
            Matcher ma;
            ma = NUMBER_PATTERN.matcher(str);
            result = ma.matches();
            if(!result){
                ma = LETTER_NUMBER_18_PATTERN.matcher(str);
                result = ma.matches();
            }
        }catch (Exception e){}
        return result;
    }
    /**
     * 全是纯数字，不支持“，”号格式的千位分割数字和小数
     * @param str
     * @return
     */
    public static boolean isNumeric(String str)
    {
        try{
            Matcher ma;
            ma = NUMBER_PATTERN.matcher(str);
            return ma.matches();
        }catch (Exception e){}
        return false;
    }

    /**
     * 全是数字，只支持正负数，不支持“，”号格式的千位分割数字和小数
     * @param str
     * @return
     */
    public static boolean isNumeric2(String str)
    {
        try{
            Matcher ma;
            ma = ZF_NUMBER_PATTERN.matcher(str);
            return ma.matches();
        }catch (Exception e){}
        return false;
    }

    /**
     * 是否数字，支持正负数，“，”号格式的千位分割数字
     * @param str
     * @return
     */
    public static boolean isNumeric1(String str)
    {
        try{
            if(!isBlankEmpty(str)){
                str = str.replaceAll(",","");
            }
            Matcher ma;
            ma = ZC_NUMBER_PATTERN.matcher(str);
            return ma.matches();
        }catch (Exception e){}
        return false;
    }
    /**
     * 是否数字，支持正负数，“，”号格式的千位分割数字,和末尾%号
     * @param str
     * @return
     */
    public static boolean isNumeric3(String str)
    {
        try{
            if(!isBlankEmpty(str)){
                str = str.replaceAll(",","");
            }
            Matcher ma;
            ma = ZC_NUMBER_PATTERN_S.matcher(str);
            return ma.matches();
        }catch (Exception e){}
        return false;
    }

    /**
     * 是否手机号码
     * @param str
     * @return
     */
    public static boolean isMobile(String str)
    {
        if(isBlankEmpty(str)){
            return false;
        }
        try{
            if(!isBlankEmpty(str)){
                str = str.replaceAll(" ","");
            }
            Matcher ma;
            ma = IS_IPHONE.matcher(str);
            boolean tmp = ma.matches();
            if(tmp && str.length() == 11){
                return true;
            }
        }catch (Exception e){}
        return false;
    }

    /**
     * 是否邮箱
     * @param str
     * @return
     */
    public static boolean isEmail(String str)
    {
        if(isBlankEmpty(str)){
            return false;
        }
        try{
            Matcher ma;
            ma = IS_EMAILE.matcher(str);
            return ma.matches();
        }catch (Exception e){}
        return false;
    }

    /**
     * 是否含中文
     * @param str
     * @return
     */
    public static boolean isChineseChar(String str){
        boolean temp = false;
        Matcher m=IS_ZHONGWEN.matcher(str);
        if(m.find()){
            temp =  true;
        }
        return temp;
    }

    /**
     * 小数点后为零的自动忽略
     * @param number
     * @return
     */
    public static String formatNumbersToString0(Object number){
        if(isBlankEmpty(number)){
            return "";
        }
        String retStr = number.toString();
        retStr = retStr.replaceAll(",", "");
        DecimalFormat format = (DecimalFormat) NumberFormat.getPercentInstance();
        format.applyPattern("##################.#############");
        try{
            retStr = format.format(Double.parseDouble(number.toString()));
        }catch (Exception e){}
        return retStr;
    }
    /**
     * 四舍五入，强制忽略小数， -0返回0
     * @param number
     * @return
     */
    public static String formatNumbers00(Object number){
        if(isBlankEmpty(number)){
            return "";
        }
        DecimalFormat format = (DecimalFormat) NumberFormat.getPercentInstance();
        format.applyPattern("###############0");
        format.setRoundingMode(RoundingMode.HALF_UP);
        String retStr = number.toString();
        try{
            retStr = format.format(Double.parseDouble(number.toString()));
            if("-0".equalsIgnoreCase(retStr)){
                retStr = "0";
            }
        }catch (Exception e){}
        return retStr;
    }
    /**
     * 四舍五入，如果小数点后都是零，最少保留两位小数0.00
     * @param number
     * @return
     */
    public static String formatNumbersToString(Object number){
        if(isBlankEmpty(number)){
            return "";
        }
        String retStr = number.toString();
        retStr = retStr.replaceAll(",", "");
        DecimalFormat format = (DecimalFormat) NumberFormat.getPercentInstance();
        format.setRoundingMode(RoundingMode.HALF_UP);
        format.applyPattern("###############0.00######");
        try{
            retStr = format.format(Double.parseDouble(number.toString()));
        }catch (Exception e){}
        return retStr;
    }
    /**
     * 四舍五入，强制保留两位小数0.00
     * @param number
     * @return
     */
    public static String formatNumbersToString02(Object number){
        if(isBlankEmpty(number)){
            return "";
        }
        String retStr = number.toString();
        retStr = retStr.replaceAll(",", "");
        DecimalFormat format = (DecimalFormat) NumberFormat.getPercentInstance();
        format.setRoundingMode(RoundingMode.HALF_UP);
        format.applyPattern("###############0.00");
        try{
            retStr = format.format(Double.parseDouble(number.toString()));
        }catch (Exception e){}
        return retStr;
    }
    /**
     * 四舍五入，强制保留两位小数，千位，好分隔0.00
     * @param number
     * @return
     */
    public static String formatNumbers(Object number){
        if(isBlankEmpty(number)){
            return "";
        }
        DecimalFormat format = (DecimalFormat) NumberFormat.getPercentInstance();
        format.applyPattern("####,###,###,###,##0.00");
        format.setRoundingMode(RoundingMode.HALF_UP);
        String retStr = number.toString();
        try{
            retStr = format.format(Double.parseDouble(number.toString()));
            if("-0.00".equalsIgnoreCase(retStr)){
                retStr = "0.00";
            }
        }catch (Exception e){}
        return retStr;
    }
    /**
     * 四舍五入，强制忽略小数，千位，好分隔
     * @param number
     * @return
     */
    public static String formatNumbers0(Object number){
        if(isBlankEmpty(number)){
            return "";
        }
        DecimalFormat format = (DecimalFormat) NumberFormat.getPercentInstance();
        format.applyPattern("####,###,###,###,##0");
        format.setRoundingMode(RoundingMode.HALF_UP);
        String retStr = number.toString();
        try{
            retStr = format.format(Double.parseDouble(number.toString()));
            if("-0".equalsIgnoreCase(retStr)){
                retStr = "0";
            }
        }catch (Exception e){}
        return retStr;
    }

    /**
     * num1 加 num2
     * @param num1
     * @param num2
     * @return
     */
    public static String add(Object num1, Object num2){
        String result = "";
        if(!isBlankEmpty(num1) || !isBlankEmpty(num2)){
            return toBigDecimal(num1).add(toBigDecimal(num2)).toPlainString();
        }
        return result;
    }
    /**
     * num1 减去 num2
     * @param num1
     * @param num2
     * @return
     */
    public static String subtract(Object num1, Object num2){
        String result = "";
        if(!isBlankEmpty(num1) || !isBlankEmpty(num2)){
            return toBigDecimal(num1).subtract(toBigDecimal(num2)).toPlainString();
        }
        return result;
    }
    /**
     * num1 乘以 num2
     * @param num1
     * @param num2
     * @return
     */
    public static Long multiply(Long num1, Long num2){
        Long result = new Long(0);
        if(num1 != null && num2 != null){
            return num1 * num2;
        }
        return result;
    }
    /**
     * num1 乘以 num2
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(Object num1, Object num2){
        String result = "";
        if(!isBlankEmpty(num1) || !isBlankEmpty(num2)){
            return toBigDecimal(num1).multiply(toBigDecimal(num2)).toPlainString();
        }
        return result;
    }
    /**
     * num1 除以 num2 结果四色五入取整
     * @param num1
     * @param num2
     * @return
     */
    public static Long divide(Long num1, Long num2){
        Long result = new Long(0);
        if(num1 != null && num2 != null){
            if(num1 != 0){
                try {
                    BigDecimal arg1 = new BigDecimal(num1);
                    BigDecimal arg2 = new BigDecimal(num2);
                    BigDecimal arg3 = arg1.divide(arg2, 0, BigDecimal.ROUND_CEILING);
                    return arg3.longValue();
                }catch (ArithmeticException e){}
            }
        }
        return result;
    }
    /**
     * num1 除以 num2 结果四色五入取整
     * @param num1
     * @param num2
     * @return
     */
    public static String divide(Object num1, Object num2){
        String result = "";
        if(!isBlankEmpty(num1) && !isBlankEmpty(num2)){
            BigDecimal arg2 = toBigDecimal(num2);
            if(arg2.compareTo(BigDecimal.ZERO) != 0){
                try {
                    BigDecimal arg1 = toBigDecimal(num1);
                    BigDecimal arg3 = arg1.divide(arg2, 0, BigDecimal.ROUND_CEILING);
                    return arg3.toPlainString();
                }catch (ArithmeticException e){}
            }else{
                return "0";
            }
        }
        return result;
    }
    /**
     * num1 除以 num2 结果四舍五入,保留指定小数位
     * @param num1
     * @param num2
     * @param scale 保留小数位数，默认0只保留整数
     * @return 计算出错返回空字符
     */
    public static String divide(Object num1, Object num2, int scale){
        String result = "";
        if(!isBlankEmpty(num1) && !isBlankEmpty(num2)){
            BigDecimal arg2 = toBigDecimal(num2);
            if(arg2.compareTo(BigDecimal.ZERO) != 0){
                try {
                    if(scale < 0){
                        scale = 0;
                    }
                    BigDecimal arg1 = toBigDecimal(num1);
                    BigDecimal arg3 = arg1.divide(arg2, scale, BigDecimal.ROUND_HALF_UP);
                    return arg3.toPlainString();
                }catch (ArithmeticException e){}
            }else{
                return result;
            }
        }
        return result;
    }

    /**
     * 文件路径重命名,如果存在，文件名末尾加1
     * "d://xxx/xxxx/xx.xxxx" to "d://xxx/xxxx/xx_1.xxxx"
     * 或 "d://xxx/xxxx/xx_1.xxxx" to "d://xxx/xxxx/xx_2.xxxx"
     * @param filePath
     * @param newFileName 不包含后缀 xxx
     * @return
     */
    public static String reFilePathName(String filePath, String newFileName){

        if(isBlankEmpty(filePath)){
            return "";
        }
        String path = ""; //  "d://xxx/xxxx/"
        String name = filePath; // "xx"
        String suffix = "";  // ".xxx"
        if(filePath.indexOf("//") > -1){
            path = filePath.substring(0, filePath.indexOf("//") + 1);
            name = filePath.substring(filePath.indexOf("//") + 1);
        }
        if(filePath.indexOf(".") > -1){
            suffix = filePath.substring(filePath.indexOf("."));
            name = name.substring(0, name.indexOf("."));
        }
        String newName = newFileName;
        if(isBlankEmpty(newFileName)){
            newName = name;
        }
        String newFilePath = path + newName + suffix;
        File file = new File(newFilePath);
        if(file.exists()){
            int count = 1;
            if(newName.indexOf("_") > -1){
                String countTmp = newName.substring(newName.indexOf("_") + 1);
                if(isNumeric(countTmp)){
                    newName = newName.substring(0, name.indexOf("_"));
                    count = toInteger(countTmp);
                    count++;
                }
            }
            newName = newName + "_" + count;
            return reFilePathName(filePath, newName);
        }
        return newFilePath;
    }

    /**
     * 文件重命名,根据当前毫秒数
     * @param fileName
     * @return
     */
    public static String reFileName(String fileName){

        if(isBlankEmpty(fileName)){
            return "";
        }
        if(fileName.indexOf(".")> -1){
            return System.currentTimeMillis()+fileName.substring(fileName.indexOf("."),fileName.length());
        }
        return "";
    }
    /**
     * 删除指定地址下的文件和文件夹
     * @param path
     */
    public static void delDirAndFile(String path){
        try {
            File file = new File(path);
            if(file.exists()){
                deleteDir(file);
            }
        } catch (Exception e) {}
    }
    /**
     * 递归删除文件夹中的文件
     * @param file
     * @return
     */
    public static boolean deleteDir(File file){
        if (file.isDirectory()) {
            String[] children = file.list();
            if(children != null){
                // 递归删除目录中的子目录下
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(file, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        // 目录此时为空，可以删除
        return file.delete();
    }
    /**
     * byte(字节)转成kb(千字节)
     *
     * @param bytes
     * @return
     */
    public static  float bytesTokb(long bytes) {
        BigDecimal filesize = new BigDecimal(bytes);
//		BigDecimal megabyte = new BigDecimal(1024 * 1024);
//		float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP)
//				.floatValue();
//		if (returnValue > 1)
//			return returnValue;
//			return (returnValue + "MB");
        BigDecimal kilobyte = new BigDecimal(1024);
        float returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
//		return (returnValue + "KB");
        return returnValue;
    }
    /**
     * 获取文件夹下所有文件,递归获取
     *
     * @param baseDir
     *            文件夹路径
     * @return 文件集合
     */
    public static List<File> getSubFiles(File baseDir) {
        List<File> ret = new ArrayList<File>();
        File[] tmp = baseDir.listFiles();
        if(tmp != null){
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i].isFile()){
                    ret.add(tmp[i]);
                }
                if (tmp[i].isDirectory()){
                    ret.addAll(getSubFiles(tmp[i]));
                }
        }
        }
        return ret;
    }
    /**
     * 指定目录下，根据文件名获取文件的集合
     * @param baseDir 文件目录
     * @param fileNames 目录下文件名的集合
     * @return
     */
    public static List<File> getSubFiles(String baseDir, List<String> fileNames) {
        List<File> ret = new ArrayList<File>();
        if(fileNames == null){
            return ret;
        }
        for (int f = 0; f < fileNames.size(); f++) {
            String fileName = fileNames.get(f);
            String filePath = Paths.get(baseDir, fileName).toString();
            File file = new File(filePath);
            if(file.exists()){
                ret.add(file);
            }
        }
        return ret;
    }
    /**
     * 初始化目录结构，只初始化文件夹和父文件夹，不会创建文件
     * @param path 文件地址
     */
    public static void initAllDir(String path){
        try {
            File file = new File(path);
            if(!file.exists()){
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
            }
        } catch (Exception e) {}
    }
    /**
     * 初始化目录结构，初始化文件夹和父文件夹
     * @param path 文件夹地址
     */
    public static void initAllDir2(String path){
        try {
            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
        } catch (Exception e) {}
    }



    /**
     * 获取指定日期字符串的明天
     * @param dateStr 日期字符串
     * @param pattern 指定日期字符串格式，和要返回的日期格式, 如果格式错误，默认[yyyy-MM-dd]
     * @return
     */
    public static String getNextDay(String dateStr, String pattern) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(parseDate(dateStr, pattern));
        rightNow.add(Calendar.DATE, 1);
        Date dt = rightNow.getTime();
        String reStr;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            reStr = sdf.format(dt);
        }catch (Exception e){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            reStr = sdf.format(dt);
        }
        return reStr;
    }
    /**
     * 获取昨天
     * @param pattern 要返回的日期格式, 如果格式错误，默认[yyyy-MM-dd]
     * @return
     */
    public static String getCurrentLastDay(String pattern) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.add(Calendar.DATE, -1);
        Date dt = rightNow.getTime();
        String reStr;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            reStr = sdf.format(dt);
        }catch (Exception e){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            reStr = sdf.format(dt);
        }
        return reStr;
    }
    /**
     * 根据当前日期，获取指定相隔天数的日期
     * @param pattern 要返回的日期格式, 如果格式错误，默认[yyyy-MM-dd]
     * @param amount 要增加或减少的天数
     * @return
     */
    public static String getCurrentToDay(int amount, String pattern) {
        return getCurrentTo(Calendar.DATE, amount, pattern);
    }
    /**
     * 根据当前日期，获取指定相隔的日期
     * @param pattern 要返回的日期格式, 如果格式错误，默认[yyyy-MM-dd]
     * @param amount 要增加或减少的数
     * @param type 要增加或减少的类型
     *
     * @see java.util.Calendar
     * @return
     */
    public static String getCurrentTo(int type, int amount, String pattern) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.add(type, amount);
        Date dt = rightNow.getTime();
        return formatDate(dt, pattern);
    }
    /**
     * 根据指定日期字符串的，获取指定相隔月份的日期
     * @param returnPattern 要返回的日期格式, 如果格式错误，默认[yyyy-MM-dd]
     * @param amount 要增加或减少的月份
     * @return
     */
    public static String getToMoth(String dateStr, String pattern, int amount, String returnPattern) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(parseDate(dateStr, pattern));
        rightNow.add(Calendar.MONTH, amount);
        Date dt = rightNow.getTime();
        return formatDate(dt, returnPattern);
    }
    /**
     * 获取上一个月今天
     * @param pattern 要返回的日期格式, 如果格式错误，默认[yyyy-MM-dd]
     * @return
     */
    public static String lastMonth(String pattern) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.add(Calendar.MONTH, -1);
        Date dt = rightNow.getTime();
        String reStr;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            reStr = sdf.format(dt);
        }catch (Exception e){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            reStr = sdf.format(dt);
        }
        return reStr;
    }
    /**
     * 获取下一个月今天
     * @param pattern 要返回的日期格式, 如果格式错误，默认[yyyy-MM-dd]
     * @return
     */
    public static String nextMonth(String pattern) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.add(Calendar.MONTH, 1);
        Date dt = rightNow.getTime();
        String reStr;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            reStr = sdf.format(dt);
        }catch (Exception e){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            reStr = sdf.format(dt);
        }
        return reStr;
    }
    /**
     * 比较两个日期字符串的大小
     * @param dateStr1
     * @param date1Pattern 日期字符串的格式，默认[yyyy-MM-dd]
     * @param dateStr2
     * @param date2Pattern 日期字符串的格式，默认[yyyy-MM-dd]
     * @return 0相等，1=dateStr1 > dateStr2， -1=dateStr1 < dateStr2
     */
    public static int compareDate(String dateStr1, String date1Pattern, String dateStr2, String date2Pattern){
        Date date1 = parseDate(dateStr1, date1Pattern);
        Date date2 = parseDate(dateStr2, date2Pattern);
        if(date1 == null && date2 == null){
           return 0;
        }else if(date1 == null){
            return -1;
        }else if(date2 == null){
            return 1;
        }else{
            return date1.compareTo(date2);
        }
    }
    /**
     * 比较两个日期字符串的相隔天数
     * @param dateStr1
     * @param date1Pattern 日期字符串的格式，默认[yyyy-MM-dd]
     * @param dateStr2
     * @param date2Pattern 日期字符串的格式，默认[yyyy-MM-dd]
     * @return null 计算错误， 0=相等， 整数=dateStr1>dateStr2， 负数=dateStr1<dateStr2
     */
    public static Integer differentDays(String dateStr1, String date1Pattern, String dateStr2, String date2Pattern){
        Date date1 = parseDate(dateStr1, date1Pattern);
        Date date2 = parseDate(dateStr2, date2Pattern);
        if(date1 != null && date2 != null){
            int days = (int) ((date1.getTime() - date2.getTime()) / (1000*3600*24));
            return days;
        }
        return null;
    }
    /**
     * 获取传入日期字符串的年份
     * @param dateStr 日期字符串
     * @param pattern 日期字符串的格式，默认[yyyy-MM-dd]
     * @return
     */
    public static String getYear(String dateStr, String pattern){
        try {
            if(isBlankEmpty(dateStr)){
               return null;
            }
            return formatDate(parseDate(dateStr, pattern), "yyyy");
        }catch (Exception e){}
        return null;
    }
    /**
     * 获取传入日期字符串的上一年
     * @param dateStr 日期字符串
     * @param pattern 日期字符串的格式，默认[yyyy-MM-dd]
     * @param resultPattern 返回日期字符串的格式，默认[yyyy-MM-dd]
     * @return
     */
    public static String getLastYear(String dateStr, String pattern, String resultPattern){
        try {
            if(isBlankEmpty(dateStr)){
                return null;
            }
            Date date = parseDate(dateStr, pattern);
            if(date != null){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.YEAR, -1);
                Date dt = calendar.getTime();
                return formatDate(dt, resultPattern);
            }
        }catch (Exception e){}
        return null;
    }
    /**
     * 得到最新十年的年份
     * @return
     */
    public static List getLastTenYear(){
        List years = new ArrayList();
        Calendar c = Calendar.getInstance();
        int year=  c.get(Calendar.YEAR);
        for(int i = 0;i<10;i++){
            years.add(year);
            year--;
        }
        return years;
    }
    /**
     * 获取指定日期是周几（周一到周日==1到7）
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Integer getDayWeekByDate(String dateStr, String pattern){
        try {
            if(isBlankEmpty(dateStr)){
                return null;
            }
            Date date = parseDate(dateStr, pattern);
            if(date != null){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int t = calendar.get(Calendar.DAY_OF_WEEK)-1;
                if(t==0){
                    t=7;
                }
                return t;
            }
        }catch (Exception e){}
        return null;
    }
    /**
     * 获取日期对象， 失败返回null
     * @param dateStr 日期字符串
     * @param pattern 字符串格式，默认[yyyy-MM-dd]
     * @return
     */
    public static Date parseDate(String dateStr, String pattern){
        return parseDateByLocale(dateStr, pattern, null);
    }
    public static Date parseDateByLocale(String dateStr, String pattern, Locale locale){
        try {
            if(isBlankEmpty(dateStr)){
                return null;
            }
            if(isBlankEmpty(pattern)){
                pattern = "yyyy-MM-dd";
            }
            if(locale == null){
                locale = Locale.CHINESE;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
            return sdf.parse(dateStr);
        }catch (Exception e){}
        return null;
    }
    /**
     * 将日期格式字符串，转换成新的格式
     * @param dateStr 日期字符串
     * @param pattern 原字符串格式，默认[yyyy-MM-dd]
     * @param newPattern 新字符串格式，默认[yyyy-MM-dd]
     * @return
     */
    public static String formatDate(String dateStr, String pattern, String newPattern){
        try {
            Date date = parseDate(dateStr, pattern);
            if(date != null){
                return formatDate(date, newPattern);
            }
        }catch (Exception e){}
        return null;
    }
    /**
     * 获取当前日期字符串， 失败返回null
     * @param resultPattern 返回日期字符串格式，默认[yyyy-MM-dd]
     * @return
     */
    public static String getCurrentFormatDate(String resultPattern){
        return formatDate(new Date(), resultPattern);
    }
    /**
     * 将日期格式字符串，转换成新的格式
     * @param dateStr 日期字符串
     *                支持的格式 yyyy-MM-dd、yyyy-MM
     *                 yyyy/MM/dd、yyyy/MM
     *                 yyyyMMdd、yyyyMM
     *                 yyyy
     * @param resultPattern 新字符串格式，默认[yyyy-MM-dd]
     * @return
     */
    public static String formatDate(String dateStr, String resultPattern){
        return formatDateByLocale(dateStr, resultPattern, null, null);
    }
    public static String formatDateByLocale(String dateStr, String resultPattern, Locale fromLocale, Locale toLocale){
        try {
            String pattern;
            if(isBlankEmpty(dateStr)){
                return null;
            }
            if(dateStr.contains("-")){
                if(dateStr.length() == 10){
                    pattern = "yyyy-MM-dd";
                }else if(dateStr.length() >= 8){
                    pattern = "yyyy-M-d";
                }else{
                    pattern = "yyyy-MM";
                }
            }else if(dateStr.contains("/")){
                if(dateStr.length() == 10){
                    pattern = "yyyy/MM/dd";
                }else if(dateStr.length() >= 8){
                    pattern = "yyyy/M/dd";
                }else{
                    pattern = "yyyy/MM";
                }
            }else{
                if(dateStr.length() == 8){
                    pattern = "yyyyMMdd";
                }else if(dateStr.length() == 6){
                    pattern = "yyyyMM";
                }else{
                    pattern = "yyyy";
                }
            }
            if(isBlankEmpty(resultPattern)){
                resultPattern = "yyyy-MM-dd";
            }
            if(fromLocale == null && toLocale == null && pattern.equals(resultPattern)){
                return dateStr;
            }
            Date date = parseDateByLocale(dateStr, pattern, fromLocale);
            if(date != null){
                return formatDateByLocale(date, resultPattern, toLocale);
            }
        }catch (Exception e){}
        return null;
    }
    /**
     * 获取日期字符串， 失败返回null
     * @param date 日期
     * @param pattern 字符串格式，默认[yyyy-MM-dd]
     * @return
     */
    public static String formatDate(Date date, String pattern){
        return formatDateByLocale(date, pattern, null);
    }
    public static String formatDateByLocale(Date date, String pattern, Locale locale){
        try {
            if(date==null){
                return null;
            }
            if(isBlankEmpty(pattern)){
                pattern = "yyyy-MM-dd";
            }
            if(locale == null){
                locale = Locale.CHINESE;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
            return sdf.format(date);
        }catch (Exception e){}
        return null;
    }
    /**
     * 毫秒转换为日期格式字符串， 失败返回null
     * @param timeObj 毫秒对象
     * @param pattern 字符串格式，默认[yyyy-MM-dd]
     * @return
     */
    public static String formatTime(Object timeObj, String pattern){
        try {
            if(isBlankEmpty(pattern)){
                pattern = "yyyy-MM-dd";
            }
            Long time = toLongRigor(timeObj);
            if(time == null){
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(time);
        }catch (Exception e){}
        return null;
    }

    /**
     * @author johnny
     * 通用字符串转换提取年份
     * @param s$
     * **/
    public static String isConversionYear(String s$){
        try {
            if(s$.length()>4){
                if(s$.indexOf("-")>0 || s$.indexOf("/")>0 || s$.length()<=8){
                    s$=s$.substring(0,4);
                }else if(s$.length()>8){
                    SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
                    if(s$.matches("[0-9]*")){
                        Date date=new Date();
                        date.setTime(Long.valueOf(s$));
                        s$=simple.format(date).substring(0,4);
                    }else{
                        String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
                        SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
                        s$=simple.format(df.parse(s$)).substring(0,4);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return s$;
    }
    /**
     * @author johnny
     * 通用字符串转换提取年月日
     * @param s$
     * **/
    public static String isConversionDate(String s$, String end, String too){
        try {
            String pattern=null;
            SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
            if(isBlankEmpty(s$)){
                return null;
            }
            if(s$.contains("-") && s$.indexOf("-")>0){
                if(s$.length() == 10){
                    return s$;
                }else if(s$.length() >= 8 && s$.length()<10){
                    pattern = "yyyy-M-d";
                }else{
                    pattern = "yyyy-MM-dd";
                    s$=s$+"-"+too;
                }
            }else if(s$.contains("/")){
                if(s$.length() == 10){
                    pattern = "yyyy/MM/dd";
                }else if(s$.length() >= 8){
                    pattern = "yyyy/M/dd";
                }else{
                    pattern = "yyyy/MM/dd";
                    s$=s$+"/"+too;
                }
            }else{
                    if(s$.matches("[0-9]*")){
                        if(s$.length()>8) {
                            Date date = new Date();
                            date.setTime(Long.valueOf(s$));
                            s$ = simple.format(date);
                           return s$;
                        }else {
                            if(s$.length() == 8){
                                pattern = "yyyyMMdd";
                            }else if(s$.length() == 6){
                                pattern = "yyyyMMdd";
                                s$+=too;
                            }else if(s$.length()==4){
                                s$+="-"+end+"-"+too;
                                return s$;
                            }
                        }
                    }else {
                        if(s$.indexOf("-")==0){
                            Date date = new Date();
                            date.setTime(Long.valueOf(s$));
                            s$ = simple.format(date);
                            return s$;
                        }
                        String patternT = "EEE MMM dd HH:mm:ss zzz yyyy";
                        SimpleDateFormat df = new SimpleDateFormat(patternT, Locale.US);
                        s$=simple.format(df.parse(s$));
                        return s$;
                    }
            }
            if(pattern!=null){
                SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
                s$=simple.format(df.parse(s$));
            }
            }catch (ParseException e1) {
            e1.printStackTrace();
        }
        return s$;
    }
    /**
     * Map 的value是否都为空字符
     * @param map
     * @return true 是，false 不是
     */
    public static boolean isMapBlankValue(Map map){
        if(map == null){
            return true;
        }
        for (Object value : map.values()){
            if(value != null){
                if(!isBlankEmpty(value.toString())){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 移除Map中 value是空字符的键值对
     * @param map
     * return 移除的Key的集合
     */
    public static List removeMapBlankValue(Map map){
        List result = new ArrayList<>();
        if(map != null && map.size() > 0){
            Iterator<Map.Entry> it = map.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry entry=it.next();
                Object value = entry.getValue();
                if(value == null){
                    it.remove();
                    result.add(entry.getKey());
                }else{
                    if(isBlankEmpty(value.toString())){
                        it.remove();
                        result.add(entry.getKey());
                    }
                }
            }
        }
        return result;
    }
    /**
     * 移除Map中 value是空字符的键值对
     * @param list
     * return 移除的Key的集合
     */
    public static List removeListMapBlankValue(List<Map<String, String>> list){
        List result = new ArrayList<>();
        if(list != null && list.size() > 0){
            Map<Object, Integer> blankKey = new HashMap();
            // 获取为空的key的数量
            for (int i = 0; i < list.size(); i++) {
                Map map = list.get(i);
                Iterator<Map.Entry> it = map.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry entry=it.next();
                    Object value = entry.getValue();
                    if(isBlankEmpty(value)){
                        Object key = entry.getKey();
                        Integer count = 0;
                        if(blankKey.containsKey(key)){
                            count = blankKey.get(key);
                        }
                        count = count + 1;
                        blankKey.put(key, count);
                    }
                }
            }
            // 获取数量等于集合数量的空字符key
            for (Object key : blankKey.keySet()){
                Integer count = blankKey.get(key);
                if(count == list.size()){
                    result.add(key);
                }
            }
            removeListMapByKeys(list, result.iterator());
        }
        return result;
    }
    /**
     * 根据key，移除Map中的键值对
     * @param map
     * @param keys
     * @return 移除的数量
     */
    public static int removeMapByKeys(Map map, Iterator keys){
        int count = 0;
        if(map != null && map.size() > 0 && keys != null){
            while (keys.hasNext()){
                Object key = keys.next();
                if(map.containsKey(key)){
                    map.remove(key);
                    count++;
                }
            }
        }
        return count;
    }
    /**
     * 根据key，移除Map中的键值对
     * @param list
     * @param keys
     * @return 移除的数量
     */
    public static int removeListMapByKeys(List<Map<String, String>> list, Iterator keys){
        int count = 0;
        if(list != null && list.size() > 0 && keys != null){
            List keyList = com.google.common.collect.Lists.newArrayList(keys);
            for (int i = 0; i < list.size(); i++) {
                Map map = list.get(i);
                if(map != null && map.size() > 0){
                    for (int j = 0; j < keyList.size(); j++) {
                        if(map.containsKey(keyList.get(j))){
                            map.remove(keyList.get(j));
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
    /**
     * 根据key获取map集合value的值
     * @param list
     * @param key
     * @return
     */
    public static <K, V> List<V> getValueListByKey(List<Map<K, V>> list, String key){
        List<V> result = new ArrayList<>();
        if(list != null && !list.isEmpty()){
            List<Map<K, V>> tmpList = new ArrayList<>();
            tmpList.addAll(list);
            Iterator<Map<K, V>> iter = tmpList.iterator();
            while (iter.hasNext()){
                Map<K, V> map = iter.next();
                result.add(map.get(key));
            }
        }
        return result;
    }
    /**
     * 根据key获取map集合value的值
     * @param list
     * @param key
     * @return
     */
    public static <K, V> Set<V> getValueSetByKey(List<Map<K, V>> list, String key){
        Set<V> result = new LinkedHashSet<>();
        if(list != null && !list.isEmpty()){
            List<Map<K, V>> tmpList = new ArrayList<>();
            tmpList.addAll(list);
            Iterator<Map<K, V>> iter = tmpList.iterator();
            while (iter.hasNext()){
                Map<K, V> map = iter.next();
                result.add(map.get(key));
            }
        }
        return result;
    }
    /**
     * 根据key对应的value值，将list转成map，注意 key对应的value值有重复的只留最后一个，如果key没有，会有个null替代
     * @param list
     * @param key  key名
     * @param isExist  是否只取key存在的，false：如果key没有，用null替代
     * @param <K>  key类型
     * @param <V>  value类型
     * @return
     */
    public static <K, V> Map<V, Map<K, V>> toMapFromListMap(List<Map<K, V>> list, String key, boolean isExist){
        Map<V, Map<K, V>> result = new LinkedMap();
        if(list != null && !list.isEmpty()){
            List<Map<K, V>> tmpList = new ArrayList<>();
            tmpList.addAll(list);
            Iterator<Map<K, V>> iter = tmpList.iterator();
            while (iter.hasNext()){
                Map<K, V> map = iter.next();
                V value = null;
                if(map.containsKey(key)){
                    value = map.get(key);
                }else if(isExist){
                    continue;
                }
                result.put(value, map);
            }
        }
        return result;
    }
    /**
     * 根据key对应的value值，将list分组
     * @param list
     * @param key  key名
     * @param isExist  是否只取key存在的，false：如果key没有，用null替代
     * @param <K>  key类型
     * @param <V>  value类型
     * @return
     */
    public static <K, V> Map<V, List<Map<K, V>>> groupFromListMap(List<Map<K, V>> list, String key, boolean isExist){
        Map<V, List<Map<K, V>>> result = new LinkedMap();
        if(list != null && !list.isEmpty()){
            List<Map<K, V>> tmpList = new ArrayList<>();
            tmpList.addAll(list);
            Iterator<Map<K, V>> iter = tmpList.iterator();
            while (iter.hasNext()){
                Map<K, V> map = iter.next();
                V value = null;
                if(map.containsKey(key)){
                    value = map.get(key);
                }else if(isExist){
                    continue;
                }
                if(result.containsKey(value)){
                    result.get(value).add(map);
                }else{
                    List<Map<K, V>> ll = new ArrayList<>();
                    ll.add(map);
                    result.put(value, ll);
                }

            }
        }
        return result;
    }

    public static <K, V> void copyMap(Map<K, V> from, Map<K, V> to){
        if(from != null && !from.isEmpty() && to != null){
            for (Map.Entry<K, V> entry : from.entrySet()){
                V value = null;
                if(entry.getValue() instanceof List){
                    List old = (List)entry.getValue();
                    List newvalue = (List)org.springframework.beans.BeanUtils.instantiate(entry.getValue().getClass());
                    copyList(old, newvalue);
                    value = (V)newvalue;
                }else if(entry.getValue() instanceof Map){
                    Map old = (Map)entry.getValue();
                    Map newvalue = (Map)org.springframework.beans.BeanUtils.instantiate(entry.getValue().getClass());
                    copyMap(old, newvalue);
                    value = (V)newvalue;
                }else{
                    value = entry.getValue();
                }
                to.put(entry.getKey(), value);
            }
        }
    }
    public static <T> void copyList(List<T> from, List<T> to){
        if(from != null && !from.isEmpty() && to != null){
            for (T f : from){
                T value;
                if(f instanceof List){
                    List old = (List)f;
                    List newvalue = (List)org.springframework.beans.BeanUtils.instantiate(f.getClass());
                    copyList(old, newvalue);
                    value = (T)newvalue;
                }else if(f instanceof Map){
                    Map old = (Map)f;
                    Map newvalue = (Map)org.springframework.beans.BeanUtils.instantiate(f.getClass());
                    copyMap(old, newvalue);
                    value = (T)newvalue;
                }else{
                    value = f;
                }
                to.add(value);
            }
        }
    }

    /**
     * 字符串根据指定的分隔符转换成set
     * @param str
     * @param separator
     * @param includeNull 是否包含空字符串
     * @return
     */
    public static Set<String> convertToSet(String str, String separator, boolean includeNull){
        Set<String> result = new HashSet<>();
        if(separator == null){
            separator = "";
        }
        if(!isBlankEmpty(str)){
            String[] a = str.split(separator);
            if(a != null && a.length > 0){
                for (String name : a){
                    if(includeNull || !isBlankEmpty(name)){
                        result.add(name);
                    }
                }
            }
        }
        return result;
    }

    /**
     * xml格式字符串转成json格式字符串
     * @param xml
     * @return
     */
    public static String xmlToJson(String xml){
        if(isBlankEmpty(xml)){
            return null;
        }
        try{
           JSONObject jsonObject =  XML.toJSONObject(xml);
            if(jsonObject != null){
                return jsonObject.toString();
            }
        }catch (Exception e){}
       return null;
    }

    /**
     * 对象转成Xml格式字符串
     * @param obj
     * @param rootNodeName xml跟节点名
     * @return
     */
    public static String objToXml(Object obj, String rootNodeName){
        if(obj == null)
            return null;
        rootNodeName = Utils.isBlankEmpty(rootNodeName) ? null : rootNodeName;
        String json = GsonUtils.toJsonStrFromObj(obj);
        if(GsonUtils.isJsonListType(obj)){
            JSONArray jobj = new JSONArray(json);
            String xml =  XML.toString(jobj, rootNodeName);
            return xml;
        }else{
            JSONObject jobj = new JSONObject(json);
            String xml =  XML.toString(jobj, rootNodeName);
            return xml;
        }
    }

    /**
     * json格式字符串转成Xml格式字符串
     * @param json
     * @param rootNodeName xml跟节点名
     * @return
     */
    public static String jsonToXml(String json, String rootNodeName){
        if(isBlankEmpty(json)){
            return null;
        }
        try{
            rootNodeName = Utils.isBlankEmpty(rootNodeName) ? null : rootNodeName;
            JSONObject jobj = new JSONObject(json);
            String xml =  XML.toString(jobj, rootNodeName);
            return xml;
        }catch (Exception e){}
       return null;
    }

    /**
     * 格式化xml字符串
     * @param xmlStr
     * @return
     */
    public static String formatXmlStr(String xmlStr){
        if(isBlankEmpty(xmlStr)){
            return "";
        }
        String result = xmlStr;
        XMLWriter xmlWriter = null;
        StringWriter writer = null;
        try {
            Document document = DocumentHelper.parseText(xmlStr);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            writer = new StringWriter();
            // 格式化输出流
            xmlWriter = new XMLWriter(writer, format);
            // 将document写入到输出流
            xmlWriter.write(document);
            result = writer.toString();
        }catch (Exception e){
            logger.error("格式化xml字符串[{}]异常：", xmlStr, e);
            System.out.println(e);
        }finally {
            if(xmlWriter != null){
                try {
                    xmlWriter.close();
                }catch (IOException e){
                }
            }
            if(writer != null){
                try {
                    writer.close();
                }catch (IOException e){
                }
            }
        }
        return result;
    }

    /**
     * 格式行业代码，不够四位的后面用0补齐
     * @param code
     * @return
     */
    public static String fomatSicCode(String code){
        if(Utils.isBlankEmpty(code)){
            return code;
        }
        String result = code.trim();
        if(result.length() < 4){
            for (int i = result.length(); i < 4; i++) {
                result = result + "0";
            }
        }
        return result;
    }
  /***
   * 字符串转换Map
   * @param str
   * @return Map
   * 数据格式"color:red,name:zhanshan"
   * */
  public static Map<String,Object> convertStringOrMap(String str){
      Map<String,Object> map=new HashMap();
      String[] strc=str.split(",");
      String[] arrt=null;
      int size=strc.length;
      for (int i=0;i<size;i++){
          arrt=strc[i].split(":");
          if(arrt!=null){
              map.put(arrt[0],arrt[1]);
          }
      }
      return map;
  }
    /**
     * 获取本机名称和ip
     * @return
     */
    public static String getLocalNameAndIp(){
        StringBuffer sb = new StringBuffer();
        try {
            InetAddress addr = InetAddress.getLocalHost();
            sb.append(addr.getHostName());
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            if(enumeration != null){
                while (enumeration.hasMoreElements()) {
                    NetworkInterface netinterface = enumeration.nextElement();
                    Enumeration<InetAddress> inets = netinterface.getInetAddresses();
                    while (inets.hasMoreElements()) {
                        InetAddress inet = inets.nextElement();
                        if (inet instanceof Inet4Address) {
                            if(sb.length() > 0){
                                sb.append(";");
                            }
                            sb.append(netinterface.getName());
                            sb.append("@");
                            sb.append(netinterface.getDisplayName());
                            sb.append("@");
                            sb.append(inet.getHostAddress());
                        }
                    }
                }
            }
//            InetAddress addr = InetAddress.getLocalHost();
//            return addr.getHostAddress();
        }catch (Exception e){}
        return sb.toString();
    }
}
