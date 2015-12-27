package com.naming.peoplehelp.data;

import android.content.Context;


public interface GoodsDataBaseInterface {
    /** ��Ӻ�ɾ����������� */
    int saveGoodsNumber(Context context, int menupos, int goodsid, String goodsnum, String goodsprice);

    /** �����±�õ� �ڶ�����Ӧ��������� */
    int getSecondGoodsNumber(Context context, int menupos, int goodsid);

    /** ���ݵ�һ�����±� �õ��ڶ��������й������� */
    int getSecondGoodsNumberAll(Context context, int menupos);

    /** ���ݵ�һ�����±� �õ��ڶ��������й���ļ۸� */
    int getSecondGoodsPriceAll(Context context, int menupos);
    
    //�õ����й���ļ۸�
    int getAllGoodsPrice(Context context);
    
    //�õ����й��������
    int getAllGoodsNumber(Context context);
    
    /** ɾ�����еĹ������� */
    void deleteAll(Context context);

}
