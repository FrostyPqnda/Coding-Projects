public class TranslateBinary extends BinaryTranslator
{
	public static void main(String[] args) 
	{
		// Ouputs my name - Brian
		String text = binaryToText("010000100111001001101001011000010110111000001010");
		System.out.println(text);
		// Outputs the number - 55991
		int decimal = binaryToDecimal("1101101010110111");
		System.out.println(decimal);
	}
}