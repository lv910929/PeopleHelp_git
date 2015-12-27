package com.naming.peoplehelp.data;

import android.content.Context;


public interface GoodsDataBaseInterface {
    /** 添加和删除购物的数量 */
    int saveGoodsNumber(Context context, int menupos, int goodsid, String goodsnum, String goodsprice);

    /** 根据下标得到 第二级对应购物的数量 */
    int getSecondGoodsNumber(Context context, int menupos, int goodsid);

    /** 根据第一级的下标 得到第二级的所有购物数量 */
    int getSecondGoodsNumberAll(Context context, int menupos);

    /** 根据第一级的下标 得到第二级的所有购物的价格 */
    int getSecondGoodsPriceAll(Context context, int menupos);
    
    //得到所有购物的价格
    int getAllGoodsPrice(Context context);
    
    //得到所有购物的数量
    int getAllGoodsNumber(Context context);
    
    /** 删除所有的购物数据 */
    void deleteAll(Context context);

}
