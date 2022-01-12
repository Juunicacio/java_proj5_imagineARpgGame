package imagineARpgGame;

import java.util.Random;

public class ReturnRandom {
	public int returnRandomN(int x) {
		Random rd = new Random();
		int y = rd.nextInt(x);
		return y;
	}

}
