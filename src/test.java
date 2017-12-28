
public class test {

	public static void main(String[] args) {
		boolean x = false;
		int y = 0;
		if(changeX(x, y))
			x = true;
		System.out.println(x);
	}

	public static boolean changeX(boolean z, int y){
		if(y != 0){
			z = true;
			return z;
		}
		return z;
	}
}
