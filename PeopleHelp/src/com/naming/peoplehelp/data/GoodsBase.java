package com.naming.peoplehelp.data;

public abstract class GoodsBase {
    //@Id // �������û��������Ϊid��_id��ʱ����ҪΪ������Ӵ�ע��
    //@NoAutoIncrement // int,long���͵�idĬ������������ʹ������ʱ��Ӵ�ע��
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
