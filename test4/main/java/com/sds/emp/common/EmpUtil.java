package com.sds.emp.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This EmpUtil class contains various methods
 * for different functionality on this project.
 * @author Taeho Kim
 */
public class EmpUtil {

    private EmpUtil() {
        super();
    }

    public static String null2str(String org, String converted) {
        if (org == null || org.trim().length() == 0) {
            return converted;
        } else {
            return org.trim();
        }
    }

    public static String null2str(String org) {
        return EmpUtil.null2str(org, "");
    }

    public static String null2str(Object org) {
        if (org != null)
            return org.toString().trim();
        else
            return "";

    }

    public static String null2str(java.math.BigDecimal org, String converted) {
        if (org == null) {
            return converted;
        } else {
            return org.toString().trim();
        }
    }

    public static String null2str(java.math.BigDecimal org) {
        return EmpUtil.null2str(org, "");
    }

    public static String toDateStr(Object date) {

        if (date == null) {
            return "";
        } else if (date.toString().length() != 8) {
            return date.toString();
        } else {
            return date.toString().substring(0, 4) + "/"
                + date.toString().substring(4, 6) + "/"
                + date.toString().substring(6, 8);
        }
    }

    public static String toDateStr(Timestamp date) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return sdf.format(new Date(date.getTime()));
        }
    }

    public static String toSsnStr(String ssnStr) {
        if (ssnStr == null) {
            return "";
        } else if (ssnStr.length() != 13) {
            return ssnStr;
        } else {
            return ssnStr.substring(0, 6) + "-" + ssnStr.substring(6, 13);
        }
    }

    public static Object toAmountStr(Object amount) {

        String returnValue = "";
        if (amount == null) {
            return returnValue;
        } else {
            String amountStr = amount.toString();
            int strLength = amountStr.length();

            if (strLength <= 3) {
                return amountStr;
            } else {
                String s1 = "";
                String s2 = "";
                for (int i = strLength - 1; i >= 0; i--) {
                    s1 += amountStr.charAt(i);
                }
                for (int i = strLength - 1; i >= 0; i--) {
                    s2 += s1.charAt(i);
                    if (i % 3 == 0 && i != 0) {
                        s2 += ",";
                    }
                }

                return s2;
            }
        }
    }

    public static String toAmountStr(java.math.BigDecimal amount) {
        if (amount == null) {
            return "";
        } else {
            return toAmountStr(amount);
        }
    }
}
