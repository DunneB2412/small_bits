package nl.ru.ai.exercise6;

public class Test {
	private static int rgbAdjust(int tag, int dir, int Key)
	{
		assert (tag>=0&&tag<=255):"out of bound arguments";
		for(int i=0;i<Key;i++)
		{
			if (tag<=0||tag>=255) 
			{
				dir= -dir;
			}
			tag+=dir;
		} 
		return tag;
	}

	public static void main(String[] args) {
		System.out.print(rgbAdjust(0, -1,300000));
		// TODO Auto-generated method stub

	}

}
