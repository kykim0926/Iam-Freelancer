package iamFreelancer.util.string;

import java.util.Random;

public class StringUtil {
	/**
	 * @description : <pre>���� ���� �߻�</pre>
	 * @param len : ����Ϸ��� ������ �ڸ���
	 * @return
	 */
	public static String getRandomNumberStr(int len){
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < len; i++) {
			temp.append((rnd.nextInt(10)));
		}
		return temp.toString();
	}
	
	/**
	 * @description : <pre>Ư������, ������ҹ���, ���� ���� ���� �߻�</pre>
	 * @param len : ����Ϸ��� ������ �ڸ���
	 * @return
	 */
	public static String getRandomSpecialEnglishNumberStr(int len){
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < len; i++) {
		    int rIndex = rnd.nextInt(4);
		    switch (rIndex) {
		    case 0:
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97)); // �ҹ���
		        break;
		    case 1:
		        temp.append((char) ((int) (rnd.nextInt(26)) + 65)); // �빮��
		        break;
		    case 2:
		        temp.append((char) ((int) (rnd.nextInt(15)) + 33)); // Ư������
		        break;
		    case 3:
		        temp.append((rnd.nextInt(10))); // ����
		        break;
		    }
		}
		return temp.toString();
	}
}
