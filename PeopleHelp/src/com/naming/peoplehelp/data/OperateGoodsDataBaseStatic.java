package com.naming.peoplehelp.data;
import android.content.Context;
import android.util.Log;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import java.util.ArrayList;
import java.util.List;

public class OperateGoodsDataBaseStatic{
    /**
     *��Ӻ�ɾ����Ʒ����
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
            //������������ݣ�����ֱ�Ӽ�1������Ͳ��������
            if(bean == null){
                Log.e("TAG", "��û�и���Ʒ");
                utils.save(goodsBean);
                Log.e("TAG" , "����Ʒ�Ѿ��洢");
                return getSecondGoodsNumber(context , menupos , goodsid);
            }else{
                Log.e("TAG" , "�Ѿ��и���Ʒ");
                //���������Ʒ֮�����Ʒ����
                return updateNum(context, menupos, goodsid, goodsnum);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        Log.e("TAG" , "�����Ʒʧ��");
        utils.close();
        return 0;
    }
    /**�޸�������ֱ�Ӵ�������**/
    public static int updateNum(Context context , int menupos , int goodsid , String goodsnum){
        DbUtils	utils = DbUtils.create(context);
        try {
            GoodsBean bean = utils.findFirst(Selector.from(GoodsBean.class).where("menupos", "=", menupos).and("goodsid", "=", goodsid));
            bean.setGoodsnum(goodsnum);
            utils.update(bean);
            Log.e("TAG", "����Ʒ�����ı�Ϊ��" + getSecondGoodsNumber(context, menupos, goodsid));
            return getSecondGoodsNumber(context , menupos , goodsid);
        } catch (DbException e) {
            e.printStackTrace();
        }
        utils.close();
        return 0;
    }
    /**
     *�����±�õ� �ڶ�����Ӧ���������
     */
    public static int getSecondGoodsNumber(Context context , int menupos , int goodsid) {
        DbUtils	utils = DbUtils.create(context);
        if(utils == null){
            Log.e("TAG" , "��û�и����ݿ�");
            return 0;
        }
        try {
            GoodsBean bean = utils.findFirst(Selector.from(GoodsBean.class).where("menupos", "=", menupos).and("goodsid", "=", goodsid));
            if(bean == null){
                Log.e("TAG" , "��û�иô洢��Ʒ");
                return 0;
            }else{
                Log.e("TAG" , "��ȡ��Ʒ�����ɹ�:" + Integer.parseInt(bean.getGoodsnum()));
                return Integer.parseInt(bean.getGoodsnum());
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        utils.close();
        Log.e("TAG", "��ȡ��Ʒ����ʧ��");
        return 0;
    }
    /**
     * ���ݵ�һ�����±� �õ��ڶ��������й�������
     */
    public static int getSecondGoodsNumberAll(Context context, int menupos) {
        DbUtils	utils = DbUtils.create(context);
        int mSecondGoodsNum = 0;
        ArrayList<GoodsBean> mGoodsBeanList = null;
        mGoodsBeanList = getSecondGoodsTypeList(context);
        if(mGoodsBeanList == null){
            Log.e("TAG" , "��ȡ��Ʒ��������ʧ��");
            return 0;
        }
        for(int i = 0 ; i < mGoodsBeanList.size() ; i++){
            if(mGoodsBeanList.get(i).getMenupos() == menupos){
                mSecondGoodsNum += Integer.parseInt(mGoodsBeanList.get(i).getGoodsnum());
            }
        }
        Log.e("TAG", "���ݵ�һ�����±� �õ��ڶ��������й��������ɹ���" + mSecondGoodsNum);
        utils.close();
        return mSecondGoodsNum;
    }
    /**
     *���ݵ�һ�����±� �õ��ڶ�����Ʒ������Ʒ���ͼ���
     */
    public static ArrayList<GoodsBean> getSecondGoodsTypeList(Context context){
        DbUtils	utils = DbUtils.create(context);
        ArrayList<GoodsBean> list = null;
        try {
            list = (ArrayList<GoodsBean>) DbUtils.create(context).findAll(GoodsBean.class);
            if(list == null){
                Log.e("TAG" , "�ö�����Ʒ��û�д洢����");
                return null;
            }else{
                Log.e("TAG" , "���ݵ�һ�����±� �õ��ڶ�����Ʒ���������ɹ���" + list.size());
                return list;
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        Log.e("TAG" , "���ݵ�һ�����±� �õ��ڶ�����Ʒ��������ʧ��");
        return null;
    }

    /**
     *�ݵ�һ�����±� �õ��ڶ��������й���ļ۸�
     */
    public static int getSecondGoodsPriceAll(Context context, int menupos) {
        DbUtils	utils = DbUtils.create(context);
        int mSecondGoodsPrice = 0;
        ArrayList<GoodsBean> mGoodsBeanList = null;
        mGoodsBeanList = getSecondGoodsTypeList(context);
        if(mGoodsBeanList == null){
            Log.e("TAG" , "��ȡ��Ʒ��������ʧ��");
            return 0;
        }
        for(int i = 0 ; i < mGoodsBeanList.size(); i++){
            if(mGoodsBeanList.get(i).getMenupos() == menupos){
                mSecondGoodsPrice += Integer.parseInt(mGoodsBeanList.get(i).getGoodsnum()) * Integer.parseInt(mGoodsBeanList.get(i).getGoodsprice());
            }
        }
        Log.e("TAG" , "���ݵ�һ�����±� �õ��ڶ��������й���ļ۸�ɹ���" + mSecondGoodsPrice);
        utils.close();
        Log.e("TAG" , "���ݵ�һ�����±� �õ��ڶ��������й���ļ۸�ʧ��");
        return mSecondGoodsPrice;
    }
    
    //�õ����й���ļ۸�
    public static int getAllGoodsPrice(Context context){
    	DbUtils	utils = DbUtils.create(context);
        int allGoodsPrice = 0;
        ArrayList<GoodsBean> mGoodsBeanList = null;
        mGoodsBeanList = getSecondGoodsTypeList(context);
        if(mGoodsBeanList == null){
            Log.e("TAG" , "��ȡ��Ʒ��������ʧ��");
            return 0;
        }
        for(int i = 0 ; i < mGoodsBeanList.size(); i++){
        	allGoodsPrice += Integer.parseInt(mGoodsBeanList.get(i).getGoodsnum()) * Integer.parseInt(mGoodsBeanList.get(i).getGoodsprice());
        }
        Log.e("TAG" , "���ݵ�һ�����±� �õ��ڶ��������й���ļ۸�ɹ���" + allGoodsPrice);
        utils.close();
        Log.e("TAG" , "���ݵ�һ�����±� �õ��ڶ��������й���ļ۸�ʧ��");
		return allGoodsPrice;
    }
    
    //�õ����й��������
    public static int getAllGoodsNumber(Context context){
    	DbUtils	utils = DbUtils.create(context);
        int allGoodsNum = 0;
        ArrayList<GoodsBean> mGoodsBeanList = null;
        mGoodsBeanList = getSecondGoodsTypeList(context);
        if(mGoodsBeanList == null){
            Log.e("TAG" , "��ȡ��Ʒ��������ʧ��");
            return 0;
        }
        for(int i = 0 ; i < mGoodsBeanList.size() ; i++){
            allGoodsNum += Integer.parseInt(mGoodsBeanList.get(i).getGoodsnum());
        }
        Log.e("TAG", "���ݵ�һ�����±� �õ��ڶ��������й��������ɹ���" + allGoodsNum);
        utils.close();
        return allGoodsNum;
    }
    
    /**
     *ɾ�����еĹ�������
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
