package com.naming.peoplehelp.data;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

public class OperateGoodsDataBaseStatic{
    /**
     *添加和删除商品数量
     */
    public static int saveGoodsNumber(Context context, int menupos, int goodsid, String goodsnum , String goodsprice) {
        DbUtils utils = DbUtils.create(context);
        GoodsBean goodsBean = null;
        goodsBean =new GoodsBean();
        goodsBean.setMenupos(menupos);
        goodsBean.setGoodsid(goodsid);
        goodsBean.setGoodsnum(goodsnum);
        goodsBean.setGoodsprice(goodsprice);
        try {
            GoodsBean bean = utils.findFirst(Selector.from(GoodsBean.class).where("menupos" , "=" , menupos).and("goodsid", "=", goodsid));
            //如果有这条数据，数量直接加1；否则就插入表里面
            if(bean == null){
                utils.save(goodsBean);
                return getSecondGoodsNumber(context , menupos , goodsid);
            }else{
                //返回添加商品之后的商品总数
                return updateNum(context, menupos, goodsid, goodsnum);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        utils.close();
        return 0;
    }
    /**修改数量，直接传入数量**/
    public static int updateNum(Context context , int menupos , int goodsid , String goodsnum){
        DbUtils	utils = DbUtils.create(context);
        try {
            GoodsBean bean = utils.findFirst(Selector.from(GoodsBean.class).where("menupos", "=", menupos).and("goodsid", "=", goodsid));
            bean.setGoodsnum(goodsnum);
            utils.update(bean);
            return getSecondGoodsNumber(context , menupos , goodsid);
        } catch (DbException e) {
            e.printStackTrace();
        }
        utils.close();
        return 0;
    }
    /**
     *根据下标得到 第二级对应购物的数量
     */
    public static int getSecondGoodsNumber(Context context , int menupos , int goodsid) {
        DbUtils	utils = DbUtils.create(context);
        if(utils == null){
            return 0;
        }
        try {
            GoodsBean bean = utils.findFirst(Selector.from(GoodsBean.class).where("menupos", "=", menupos).and("goodsid", "=", goodsid));
            if(bean == null){
                return 0;
            }else{
                return Integer.parseInt(bean.getGoodsnum());
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        utils.close();
        return 0;
    }
    /**
     * 根据第一级的下标 得到第二级的所有购物数量
     */
    public static int getSecondGoodsNumberAll(Context context, int menupos) {
        DbUtils	utils = DbUtils.create(context);
        int mSecondGoodsNum = 0;
        ArrayList<GoodsBean> mGoodsBeanList = null;
        mGoodsBeanList = getSecondGoodsTypeList(context);
        if(mGoodsBeanList == null){
            return 0;
        }
        for(int i = 0 ; i < mGoodsBeanList.size() ; i++){
            if(mGoodsBeanList.get(i).getMenupos() == menupos){
                mSecondGoodsNum += Integer.parseInt(mGoodsBeanList.get(i).getGoodsnum());
            }
        }
        utils.close();
        return mSecondGoodsNum;
    }
    /**
     *根据第一级的下标 得到第二级商品所有商品类型集合
     */
    public static ArrayList<GoodsBean> getSecondGoodsTypeList(Context context){
        DbUtils	utils = DbUtils.create(context);
        ArrayList<GoodsBean> list = null;
        try {
            list = (ArrayList<GoodsBean>) DbUtils.create(context).findAll(GoodsBean.class);
            if(list == null){
                return null;
            }else{
                return list;
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        utils.close();
        return null;
    }

    /**
     *据第一级的下标 得到第二级的所有购物的价格
     */
    public static int getSecondGoodsPriceAll(Context context, int menupos) {
        DbUtils	utils = DbUtils.create(context);
        int mSecondGoodsPrice = 0;
        ArrayList<GoodsBean> mGoodsBeanList = null;
        mGoodsBeanList = getSecondGoodsTypeList(context);
        if(mGoodsBeanList == null){
            return 0;
        }
        for(int i = 0 ; i < mGoodsBeanList.size(); i++){
            if(mGoodsBeanList.get(i).getMenupos() == menupos){
                mSecondGoodsPrice += Integer.parseInt(mGoodsBeanList.get(i).getGoodsnum()) * Integer.parseInt(mGoodsBeanList.get(i).getGoodsprice());
            }
        }
        utils.close();
        return mSecondGoodsPrice;
    }
    
    //得到所有购物的价格
    public static int getAllGoodsPrice(Context context){
    	DbUtils	utils = DbUtils.create(context);
        int allGoodsPrice = 0;
        ArrayList<GoodsBean> mGoodsBeanList = null;
        mGoodsBeanList = getSecondGoodsTypeList(context);
        if(mGoodsBeanList == null){
            return 0;
        }
        for(int i = 0 ; i < mGoodsBeanList.size(); i++){
        	allGoodsPrice += Integer.parseInt(mGoodsBeanList.get(i).getGoodsnum()) * Integer.parseInt(mGoodsBeanList.get(i).getGoodsprice());
        }
        utils.close();
		return allGoodsPrice;
    }
    
    //得到所有购物的数量
    public static int getAllGoodsNumber(Context context){
    	DbUtils	utils = DbUtils.create(context);
        int allGoodsNum = 0;
        ArrayList<GoodsBean> mGoodsBeanList = null;
        mGoodsBeanList = getSecondGoodsTypeList(context);
        if(mGoodsBeanList == null){
            return 0;
        }
        for(int i = 0 ; i < mGoodsBeanList.size() ; i++){
            allGoodsNum += Integer.parseInt(mGoodsBeanList.get(i).getGoodsnum());
        }
        utils.close();
        return allGoodsNum;
    }
    
    /**
     *删除所有的购物数据
     */
    public static void deleteAll(Context context) {
        DbUtils	utils = DbUtils.create(context);
        try {
            List<GoodsBean> records = utils.findAll(GoodsBean.class);
            utils.deleteAll(records);
        } catch (DbException e) {
            e.printStackTrace();
        }
        utils.close();
    }
}
