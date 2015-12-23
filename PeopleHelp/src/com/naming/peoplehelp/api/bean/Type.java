package com.naming.peoplehelp.api.bean;

public class Type {
	
	// 1 ���ţ�2 ��ͼ��3 ���ӣ�4 ��Ƶ��
	// 7 ���8 ͶƱ��9 ���飬10 ר��
	// news(1), images(2), link(3), video(4), activity(7), vote(8), survey(9),special(10);
	
	private int index;

	public Type(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		String name = null;
		switch (index) {
		case 1:
			name = "����";
			break;
		case 2:
			name = "��ͼ";
			break;
		case 3:
			name = "����";
			break;
		case 4:
			name = "��Ƶ";
			break;
		case 7:
			name = "�";
			break;
		case 8:
			name = "ͶƱ";
			break;
		case 9:
			name = "����";
			break;
		case 10:
			name = "ר��";
			break;
		}
		return name;
	}
}