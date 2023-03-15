public class BinaryToDecimal {
public static int binaryToDecimal(int n)
{
	int dec_num = 0;
	int power = 0;
	while (n > 0) {
	if (n % 10 == 1) {
		dec_num += (1 << power);
	}
	power++;
	n = n / 10;
	}
	return dec_num;
}

public static void main(String[] args)
{
	int num = 10101001;
	System.out.println(binaryToDecimal(num));
}
}

// This code is contributed by aadityamaharshi21.
