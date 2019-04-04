package com.iwhalecloud.zsmart.bss.payment.test.entity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetTime {
    String expireDate;
    String reserveDate;
    String BatchLeftMatch;
    String Date;
    String DateyyyyMMdd;
    String dateBcFormat1;
    String dateBcFormat2;
    //时间获取
    public   GetTime() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyMM");
        SimpleDateFormat sdf3=new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf4=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        BatchLeftMatch=sdf2.format(new Date());
        Date=sdf.format(new Date());
        DateyyyyMMdd=sdf3.format(new Date());
        dateBcFormat1=sdf4.format(new Date());

        Date dt = sdf.parse(Date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, 1);
        Date dt1 = rightNow.getTime();
        expireDate = sdf.format(dt1);
        rightNow.add(Calendar.MONTH, 1);
        Date dt2 = rightNow.getTime();
        reserveDate = sdf.format(dt2);
        dateBcFormat2=sdf4.format(dt2);
    }

}
