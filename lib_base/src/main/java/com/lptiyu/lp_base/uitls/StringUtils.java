package com.lptiyu.lp_base.uitls;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by map on 2017/4/24.
 */

public class StringUtils {
    /**
     * 判断是否是6到16位字母密码
     *
     * @param inputNumber
     * @return
     */
    public static boolean checkPwd(String inputNumber) {
        String regex = "[0-9a-zA-Z]{6,16}";
        return inputNumber.matches(regex);
    }

    /**
     * 去掉指定字符串前面指定的字符
     *
     * @param str
     * @param c
     * @return
     */
    public static String custom_ltrim(String str, char c) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        int st = 0;
        while ((st < len) && (chars[st] == c)) {
            st++;
        }
        return (st > 0) ? str.substring(st, len) : str;
    }

    /**
     * 判断是否是子账号 子账号不是以1开头的
     *
     * @param inputNumber
     * @return
     */
    public static String isChildAccount(String inputNumber) {
        if (!inputNumber.startsWith("1")) {
            inputNumber = inputNumber.substring(1, inputNumber.length());
            return "1".concat(inputNumber);
        } else {
            return inputNumber;
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param valuses
     * @return
     */
    public static boolean isNull(String... valuses) {
        boolean isNUll = false;
        for (String string : valuses) {
            if (TextUtils.isEmpty(string) || "null".equalsIgnoreCase(string)) {
                isNUll = true;
                return isNUll;
            }
        }
        return isNUll;
    }


    public static boolean isNotNull(String string) {
        return !isNull(string);
    }

    /**
     * 去掉空格
     *
     * @param oldStr
     * @return
     */
    public static String removeSpace(String oldStr) {
        return TextUtils.isEmpty(oldStr) ? oldStr : oldStr.replace(" ", "");
    }

    /**
     * 将空格替换为下划线
     *
     * @param oldStr
     * @return
     */
    public static String replaceSpaceWithDownLine(String oldStr) {
        return TextUtils.isEmpty(oldStr) ? oldStr : oldStr.replace(" ", "_");
    }


    /**
     * 过滤特殊字符
     *
     * @param paramString
     * @return
     */
    public static boolean StringFilter(String paramString) {
        return Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]").matcher
                (paramString).find();
    }

    /**
     * 获取字符串长度
     *
     * @param str
     * @return
     */
    public static int getStrLength(String str) {
        int length = 0;
        if (str == null || str.length() == 0) {
            return 0;
        } else {
            for (int i = 0; i < str.length(); i++) {
                char strChar = str.charAt(i);
                //单字节加1
                if ((strChar >= 0x0001 && strChar <= 0x007e) || (0xff60 <= strChar && strChar <= 0xff9f)) {
                    length++;
                } else {
                    length += 2;
                }
            }
            return length;
        }
    }


    /**
     * 按照规定长度截取字符串
     * <p/>
     * 需要对汉字和字符串进行处理
     *
     * @param length
     * @return
     */
    public static String cutOutStr(String str, int length) {
        int tempLength = 0;
        StringBuilder tempStr = new StringBuilder();
        if (str == null || str.length() == 0) {
            return "";
        } else {
            for (int i = 0; i < str.length(); i++) {
                char strChar = str.charAt(i);
                //单字节加1
                if ((strChar >= 0x0001 && strChar <= 0x007e) || (0xff60 <= strChar && strChar <= 0xff9f)) {
                    tempLength++;
                } else {
                    tempLength += 2;
                }
                if (tempLength <= length) {
                    tempStr.append(strChar);
                }
            }
            return tempStr.toString();
        }
    }

    /**
     * 是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (TextUtils.isEmpty(str)) return false;
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(str);
        boolean result = matcher.matches();
        return result;
    }

    /**
     * 是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isDoubleNumber(String str) {
        if (TextUtils.isEmpty(str)) return false;
        Pattern pattern = Pattern.compile("^[+-]?\\d*[.]?\\d*$");
        Matcher matcher = pattern.matcher(str);
        boolean result = matcher.matches();
        return result;
    }

    /**
     * 实现文本复制功能
     *
     * @param content
     */
    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
       ToastUtil.showTextToast("复制成功");
    }

    /**
     * 实现粘贴功能
     *
     * @param context
     * @return
     */
    public static String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
       ToastUtil.showTextToast("粘贴成功");
        return cmb.getText().toString().trim();
    }

    /***
     * 判断是否为合法中文名
     *
     * @param str
     * @return
     */
    public static boolean isChineseName(String str) {
        if (str.isEmpty() || str.length() > 4 || str.length() < 2) {
            return false;
        }
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        return m.find();
    }

    /***
     * 判断是否为合法中文名
     *
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        if (str.isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * @prama: str 要判断是否包含特殊字符的目标字符串
     */
    public static boolean compileExChar(String str) {
        String limitEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(limitEx);
        Matcher m = pattern.matcher(str);
        return m.find();
    }

    /**
     * 返回一个高亮spannable
     *
     * @param content 文本内容
     * @param color   高亮颜色
     * @param start   起始位置
     * @param end     结束位置
     * @return 高亮spannable
     */
    public static CharSequence getHighLightText(String content, int color, int start, int end) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        start = start >= 0 ? start : 0;
        end = end <= content.length() ? end : content.length();
        SpannableString spannable = new SpannableString(content);
        CharacterStyle span = new ForegroundColorSpan(color);
        spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    /**
     * 第n次出现某个字符的位置
     *
     * @param str
     * @param ch
     * @param n
     * @return
     */
    public static int getChPos(String str, String ch, int n) {
        //这里是获取"/"符号的位置
        Matcher slashMatcher = Pattern.compile(ch).matcher(str);
        int mIdx = 0;
        while (slashMatcher.find()) {
            mIdx++;
            //当"/"符号第n次出现的位置
            if (mIdx == n) {
                break;
            }
        }
        if (mIdx < n) {
            return -1;
        }
        try {
            return slashMatcher.start();
        } catch (IllegalStateException e) {
            return -1;
        }
    }

    public static String formatNum(String num, Boolean kBool) {
        return formatNum(num, kBool, "");
    }

    /**
     * <pre>
     * 数字格式化显示
     * 小于万默认显示 大于万以1.7万方式显示最大是9999.9万
     * 大于亿以1.1亿方式显示最大没有限制都是亿单位
     * </pre>
     *
     * @param num   格式化的数字
     * @param kBool 是否格式化千,为true,并且num大于999就显示999+,小于等于999就正常显示
     * @return
     */
    public static String formatNum(String num, Boolean kBool, String unitStr) {
        StringBuffer sb = new StringBuffer();
        if (!isNumber(num))
            return "0";
        if (kBool == null)
            kBool = false;

        BigDecimal b0 = new BigDecimal("1000");
        BigDecimal b1 = new BigDecimal("10000");
        BigDecimal b2 = new BigDecimal("100000000");
        BigDecimal b3 = new BigDecimal(num);

        String formatNumStr = "";
        String nuit = "";

        // 以千为单位处理
        if (kBool) {
            if (b3.compareTo(b0) == 0 || b3.compareTo(b0) == 1) {
                return "999+";
            }
            return num;
        }

        // 以万为单位处理
        if (b3.compareTo(b1) == -1) {
            sb.append(b3.toString());
        } else if ((b3.compareTo(b1) == 0 && b3.compareTo(b1) == 1)
                || b3.compareTo(b2) == -1) {
            formatNumStr = b3.divide(b1).toString();
            if (StringUtils.isNotNull(unitStr)) {
                nuit = unitStr;
            } else {
                nuit = "万";
            }
        } else if (b3.compareTo(b2) == 0 || b3.compareTo(b2) == 1) {
            formatNumStr = b3.divide(b2).toString();
            nuit = "亿";
        }
        if (!"".equals(formatNumStr)) {
            int i = formatNumStr.indexOf(".");
            if (i == -1) {
                sb.append(formatNumStr).append(nuit);
            } else {
                i = i + 1;
                String v = formatNumStr.substring(i, i + 1);
                if (!v.equals("0")) {
                    sb.append(formatNumStr.substring(0, i + 1)).append(nuit);
                } else {
                    sb.append(formatNumStr.substring(0, i - 1)).append(nuit);
                }
            }
        }
        if (sb.length() == 0)
            return "0";
        return sb.toString();
    }

    private static String nums[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    private static String pos_units[] = {"", "十", "百", "千"};

    private static String weight_units[] = {"", "万", "亿"};

    /**
     * 数字转汉字【新】
     *
     * @param num
     * @return
     */
    public static String numberToChinese(int num) {
        if (num == 0) {
            return "零";
        }

        int weigth = 0;//节权位
        String chinese = "";
        String chinese_section = "";
        boolean setZero = false;//下一小节是否需要零，第一次没有上一小节所以为false
        while (num > 0) {
            int section = num % 10000;//得到最后面的小节
            if (setZero) {//判断上一小节的千位是否为零，是就设置零
                chinese = nums[0] + chinese;
            }
            chinese_section = sectionTrans(section);
            if (section != 0) {//判断是都加节权位
                chinese_section = chinese_section + weight_units[weigth];
            }
            chinese = chinese_section + chinese;
            chinese_section = "";
            LogUtils.i(chinese_section);

            setZero = (section < 1000) && (section > 0);
            num = num / 10000;
            weigth++;
        }
        if ((chinese.length() == 2 || (chinese.length() == 3)) && chinese.contains("一十")) {
            chinese = chinese.substring(1, chinese.length());
        }
        if (chinese.indexOf("一十") == 0) {
            chinese = chinese.replaceFirst("一十", "十");
        }

        return chinese;
    }

    /**
     * @param context
     * @return
     */
    public static boolean isWifiProxy(Context context) {
        final boolean IS_ICS_OR_LATER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
        String proxyAddress;
        int proxyPort;
        if (IS_ICS_OR_LATER) {
            proxyAddress = System.getProperty("http.proxyHost");
            String portStr = System.getProperty("http.proxyPort");
            proxyPort = Integer.parseInt((portStr != null ? portStr : "-1"));
        } else {
            proxyAddress = android.net.Proxy.getHost(context);
            proxyPort = android.net.Proxy.getPort(context);
        }
        return (!TextUtils.isEmpty(proxyAddress)) && (proxyPort != -1);
    }

    public static int stringToInt(String string) {
        int i = 0;
        if (isNull(string)) {
            return i;
        }
        try {
            i = Integer.valueOf(string);
        } catch (NumberFormatException e) {

        }
        return i;
    }

    public static float stringToFloat(String string) {
        float i = 0f;
        if (isNull(string)) {
            return i;
        }
        try {
            i = Float.valueOf(string);
        } catch (NumberFormatException e) {

        }
        return i;
    }

    public static double stringToDouble(String string) {
        double i = 0d;
        if (isNull(string)) {
            return i;
        }
        try {
            i = Double.valueOf(string);
        } catch (NumberFormatException e) {

        }
        return i;
    }

    public static float sumFloat(List<Float> floats) {
        if (CollectionUtils.isEmpty(floats)) {
            return 0f;
        }
        float sum = 0f;
        if (CollectionUtils.isEmpty(floats)) {
            return sum;
        }
        for (Float f : floats) {
            sum += f;
        }
        return sum / floats.size();
    }

    /**
     * 将每段数字转汉字
     *
     * @param section
     * @return
     */
    public static String sectionTrans(int section) {
        StringBuilder section_chinese = new StringBuilder();
        int pos = 0;//小节内部权位的计数器
        boolean zero = true;//小节内部的置零判断，每一个小节只能有一个零。
        while (section > 0) {
            int v = section % 10;//得到最后一个数
            if (v == 0) {
                if (!zero) {
                    zero = true;//需要补零的操作，确保对连续多个零只是输出一个
                    section_chinese.insert(0, nums[0]);
                }
            } else {
                zero = false;//有非零数字就把置零打开
                section_chinese.insert(0, pos_units[pos]);
                section_chinese.insert(0, nums[v]);
            }
            pos++;
            section = section / 10;
        }

        return section_chinese.toString();
    }

    /**
     * 生成指定长度的随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomStringByLength(int length) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < length; j++) {
            //生成一个97-122之间的int类型整数--为了生成小写字母
            int intValL = (int) (Math.random() * 26 + 97);
            //生成一个65-90之间的int类型整数--为了生成大写字母
            int intValU = (int) (Math.random() * 26 + 65);
            //生成一个48-57之间的int类型整数--为了生成数字
            int intValN = (int) (Math.random() * 10 + 48);
            int intVal;
            int r = (int) (Math.random() * 3);
            if (r == 0) {
                if (intValL == 105 || intValL == 108 || intValL == 111) {//排除小写 i o l
                    intValL = intValL + 1;
                }
                intVal = intValL;
            } else if (r == 1) {
                if (intValU == 79) {//排除大写 O
                    intValU = intValU + 1;
                }
                intVal = intValU;
            } else {
                //如果是0,1 改成 2,3
                if (intValN == 48 || intValN == 49) {
                    intValN = intValN + 2;
                }
                intVal = intValN;
            }

            sb.append((char) intVal);
        }
        return sb.toString();
    }
}
