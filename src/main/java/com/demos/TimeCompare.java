package com.demos;
import java.text.SimpleDateFormat;
        import java.util.Date;


public class TimeCompare {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //例如比较当前时间和早上6：00
        String nowTime = new SimpleDateFormat("HH:MM").format(new Date());
        System.out.println("当前时间为：" + nowTime);
        System.out.println("与当日06：00相比");
        int i = DateCompare(nowTime, "06:00", "HH:MM");
        switch (i) {
            case 0:
                System.out.println("两个时间相等");
                break;
            case 1:
                System.out.println("当前时间晚于06:00");
                break;
            case -1:
                System.out.println("当前时间早于06:00");
                break;
            default:
                break;
        }

    }

    /**
     * 根据时间类型比较时间大小
     *
     * @param source
     * @param traget
     * @param type        "YYYY-MM-DD" "yyyyMMdd HH:mm:ss"  类型可自定义
     * @param //传递时间的对比格式
     * @return 0 ：source和traget时间相同
     * 1 ：source比traget时间大
     * -1：source比traget时间小
     * @throws Exception
     */
    public static int DateCompare(String source, String traget, String type) throws Exception {
        int ret = 2;
        SimpleDateFormat format = new SimpleDateFormat(type);
        Date sourcedate = format.parse(source);
        Date tragetdate = format.parse(traget);
        ret = sourcedate.compareTo(tragetdate);
        return ret;
    }
}