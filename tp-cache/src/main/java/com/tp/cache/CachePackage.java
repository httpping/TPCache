package com.tp.cache;
/*

                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG

*/

/**
 * 项目名称: YOSHOP
 * 类描述：
 * 创建人：Created by tanping
 * 创建时间:2018/7/31 15:47
 */
public class CachePackage {
    public String v;
    /**
     * 什么时候过期 秒
     */
    public int exp;
    /**
     * 当前时间是
     */
    public long timestamp;

    public String cls;

    public boolean isExp(){
        if (exp > 0 ){
            long time = System.currentTimeMillis() - timestamp;
            if (exp < time/1000){
                return true;
            }
        }
        return false;
    }

    public Object getValue(){

     /*
     if (cls !=null){
            int index = isBaseType();
            if (index>=0) {
                try {
                    switch (index) {
                        case 0:
                            return ((Number) v).intValue();
                        case 1:
                            return ((Number) v).floatValue();
                        case 2:
                            return ((Number) v).doubleValue();
                        case 3:
                            return ((Number) v).byteValue();
                        case 4:
                            return ((Number) v).longValue();
                        case 5:
                            return ((Number) v).shortValue();
                        case 6:
                            return Boolean.valueOf(v.toString());
                        default:
                            return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
*/
        return null;
    }

    private static String[] types = {"java.lang.Integer","java.lang.Float","java.lang.Double","java.lang.Byte","java.lang.Long","java.lang.Short","java.lang.Boolean"};
    private int isBaseType(){

        int pos =0;
        for (String type : types){
            if (cls.indexOf(type)>=0){
               return  pos;
            }
            pos++;
        }

        return  -1;
    }
}
