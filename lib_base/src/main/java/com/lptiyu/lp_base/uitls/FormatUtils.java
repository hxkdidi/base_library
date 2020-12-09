package com.lptiyu.lp_base.uitls;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

/**
 * Created by Jason on 2016/8/18.
 */
public class FormatUtils {
    private static final DecimalFormat DF_TWO_DECIMAL_PLACES = new DecimalFormat("0.00");
    private static final DecimalFormat DF_SIX_DECIMAL_PLACES = new DecimalFormat("0.000000");
    private static final DecimalFormat DF_ONE_DECIMAL_PLACE = new DecimalFormat("0.0");
    private static final DecimalFormatSymbols symbols = new DecimalFormatSymbols();

    /**
     * 指定保留位数
     *
     * @param number
     * @param num    保留位数
     * @return
     */
    public static String formatDouble(float number, int num) {
        synchronized (DF_TWO_DECIMAL_PLACES) {
            BigDecimal bd = new BigDecimal(number);
            bd = bd.setScale(num, BigDecimal.ROUND_HALF_UP);
            return bd.toString();
        }
    }

    /**
     * 取固定长度字符串
     *
     * @param num
     * @param length
     * @return
     */
    public static String cutStr(float num, int length) {
        String str = String.valueOf(num);
        if (str.length() > length) {
            return str.substring(0, length);
        } else {
            return str;
        }
    }

    /**
     * 指定保留位数，满五进一
     *
     * @param number
     * @param num    保留位数
     * @return
     */
    public static String formatFloatOther(float number, int num) {
        return String.format("%.2f", number);
    }


    /**
     * 指定保留位数，满五进一
     *
     * @param number
     * @param num    保留位数
     * @return
     */
    public static float formatFloat(float number, int num) {
        synchronized (DF_TWO_DECIMAL_PLACES) {
            BigDecimal bd = new BigDecimal(number);
            bd = bd.setScale(num, BigDecimal.ROUND_HALF_UP);
            return bd.floatValue();
        }
    }

    /**
     * 将米转化为千米（不能四舍五入）
     *
     * @param meter
     * @return
     */
    public static float formatMeterToKiloMeter(double meter) {
        synchronized (DF_TWO_DECIMAL_PLACES) {
            double div = FloatCalculateUtils.div(meter, 1000d);
            float round = (float) FloatCalculateUtils.round(div, BigDecimal.ROUND_DOWN, 2);
            return round;
        }
//        try {
//            String s = DF_TWO_DECIMAL_PLACES.format(meter);
//            double f = Double.parseDouble(s);
//            String comment_content = big(f / 1000);
//            String substring = comment_content.substring(0, comment_content.indexOf('.') + 3);
//            return substring;
//        } catch (Exception e) {
//            return DF_TWO_DECIMAL_PLACES.format(meter / 1000f);
//        }
    }

    /**
     * 让double类型不用科学计数法显示
     *
     * @param d
     * @return
     */
    private static String big(double d) {
        synchronized (DF_TWO_DECIMAL_PLACES) {
            NumberFormat nf = NumberFormat.getInstance();
            // 是否以逗号隔开, 默认true以逗号隔开,如[123,456,789.128]
            nf.setGroupingUsed(false);
            // 结果未做任何处理
            return nf.format(d);
        }
    }

    /**
     * 将米格式化为英文单位的km
     *
     * @param meter
     * @return
     */
    public static String formatMeterWithEgUnit(double meter) {
        synchronized (DF_TWO_DECIMAL_PLACES) {
            setFormatSeparator();
            if (meter < 1000) {
                return DF_TWO_DECIMAL_PLACES.format(meter) + "m";
            } else {
                return DF_TWO_DECIMAL_PLACES.format(meter / 1000f) + "km";
            }
        }
    }

    /**
     * 将米格式化为中文的单位的千米
     *
     * @param meter
     * @return
     */
    public static String formatMeterWithCNUnit(double meter) {
        synchronized (DF_TWO_DECIMAL_PLACES) {
            setFormatSeparator();
            if (meter < 1000) {
                return DF_TWO_DECIMAL_PLACES.format(meter) + "米";
            } else {
                return DF_TWO_DECIMAL_PLACES.format(meter / 1000f) + "千米";
            }
        }
    }

    public static String formatFenToYuan(String fen) {
        synchronized (DF_TWO_DECIMAL_PLACES) {
            setFormatSeparator();
            return DF_TWO_DECIMAL_PLACES.format(Long.parseLong(fen) / 100f);
        }
    }

    /**
     * 保留一位小数
     *
     * @param f
     * @return
     */
    public static String reserveOneDecimalPlace(double f) {
        synchronized (DF_TWO_DECIMAL_PLACES) {
            setFormatSeparator();
            return DF_ONE_DECIMAL_PLACE.format(f);
        }
    }

    /**
     * 保留两位小数
     *
     * @param f
     * @return
     */
    public static String reserveTwoDecimalPlace(double f) {
        synchronized (DF_TWO_DECIMAL_PLACES) {
            setFormatSeparator();
            return DF_TWO_DECIMAL_PLACES.format(f);
        }
    }

    /**
     * 保留两位小数
     *
     * @param f
     * @return
     */
    public static String reserveSixDecimalPlace(double f) {
        synchronized (DF_SIX_DECIMAL_PLACES) {
            setFormatSeparator();
            return DF_SIX_DECIMAL_PLACES.format(f);
        }
    }

    private static void setFormatSeparator() {
        symbols.setDecimalSeparator('.');
        DF_TWO_DECIMAL_PLACES.setDecimalFormatSymbols(symbols);
        DF_ONE_DECIMAL_PLACE.setDecimalFormatSymbols(symbols);
    }

}
