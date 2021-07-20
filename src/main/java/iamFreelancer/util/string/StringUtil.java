package iamFreelancer.util.string;

import java.util.Random;

public class StringUtil {
	/**
	 * @description : <pre>숫자 난수 발생</pre>
	 * @param len : 출력하려는 난수의 자릿수
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
	 * @description : <pre>특수문자, 영문대소문자, 숫자 포함 난수 발생</pre>
	 * @param len : 출력하려는 난수의 자릿수
	 * @return
	 */
	public static String getRandomSpecialEnglishNumberStr(int len){
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < len; i++) {
		    int rIndex = rnd.nextInt(4);
		    switch (rIndex) {
		    case 0:
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97)); // 소문자
		        break;
		    case 1:
		        temp.append((char) ((int) (rnd.nextInt(26)) + 65)); // 대문자
		        break;
		    case 2:
		        temp.append((char) ((int) (rnd.nextInt(15)) + 33)); // 특수문자
		        break;
		    case 3:
		        temp.append((rnd.nextInt(10))); // 숫자
		        break;
		    }
		}
		return temp.toString();
	}
}
