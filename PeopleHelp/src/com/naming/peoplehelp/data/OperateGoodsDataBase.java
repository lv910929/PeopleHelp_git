package com.naming.peoplehelp.data;
import android.content.Context;


public class OperateGoodsDataBase implements GoodsDataBaseInterface{

    private static OperateGoodsDataBase instance  = new OperateGoodsDataBase();

    public static OperateGoodsDataBase getInstance(){
        return  instance;
    }

    private OperateGoodsDataBase(){

    }

    /**
     *��Ӻ�ɾ����Ʒ����,���õ���Ʒ����
     */
    @Override
    public int saveGoodsNumber(Context context, int menupos, int goodsid, String goodsnum , String goodsprice) {
        return OperateGoodsDataBaseStatic.saveGoodsNumber(context , menupos , goodsid , goodsnum ,goodsprice);
    }
    /**
     *�����±�õ� �ڶ�����Ӧ���������
     */
    @Override
    public int getSecondGoodsNumber(Context context, int menupos, int goodsid) {
        return OperateGoodsDataBaseStatic.getSecondGoodsNumber(context, menupos, goodsid);
    }
    /**
     * ���ݵ�һ�����±� �õ��ڶ��������й�������
     */
    @Override
    public int getSecondGoodsNumberAll(Context context, int menupos) {
        return OperateGoodsDataBaseStatic.getSecondGoodsNumberAll(context, menupos);
    }

    /**
     *�ݵ�һ�����±� �õ��ڶ��������й���ļ۸�
     */
    @Override
    public int getSecondGoodsPriceAll(Context context, int menupos) {
        return OperateGoodsDataBaseStatic.getSecondGoodsPriceAll(context, menupos);
    }
    /**
     *ɾ�����еĹ�������
     */
    @Override
    public void deleteAll(Context context) {
        OperateGoodsDataBaseStatic.deleteAll(context);
    }
}
