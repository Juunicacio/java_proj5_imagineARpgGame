package imagineARpgGame;

import java.util.Random;

public class ReturnRandom {
	public static int returnRandomN(int x) {
		Random rd = new Random();
		int result = rd.nextInt(x);
		return result;
	}

}
