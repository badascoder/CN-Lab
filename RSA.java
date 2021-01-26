import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
public class RSA
{
private BigInteger p;
private BigInteger q;
private BigInteger N;
private BigInteger phi;
private BigInteger e;
private BigInteger d;
private int bitlength = 1024;
private Random r;
public RSA()
{
r = new Random();
p = BigInteger.probablePrime(bitlength, r);
q = BigInteger.probablePrime(bitlength, r);
N = p.multiply(q);
phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
e = BigInteger.probablePrime(bitlength / 2, r);
while (phi.gcd(e).compareTo(BigInteger.ONE) &gt; 0 &amp;&amp; e.compareTo(phi) &lt; 0)
{
e.add(BigInteger.ONE);
}
d = e.modInverse(phi);
}
public RSA(BigInteger e, BigInteger d, BigInteger N)
{
this.e = e;
this.d = d;
this.N = N;
}
public static void main(String[] args) throws IOException
{
RSA rsa = new RSA();
DataInputStream in = new DataInputStream(System.in);
String teststring;
System.out.println(&quot;Enter the plain text:&quot;);
teststring = in.readLine();
System.out.println(&quot;Encrypting String: &quot; + teststring);
System.out.println(&quot;String in Bytes: &quot;
+ bytesToString(teststring.getBytes()));
// encrypt
byte[] encrypted = rsa.encrypt(teststring.getBytes());
// decrypt
byte[] decrypted = rsa.decrypt(encrypted);
System.out.println(&quot;Decrypting Bytes: &quot; + bytesToString(decrypted));
System.out.println(&quot;Decrypted String: &quot; + new String(decrypted));

}
private static String bytesToString(byte[] encrypted)
{
String test = &quot;&quot;;
for (byte b : encrypted)
{
test += Byte.toString(b);
}
return test;
}
// Encrypt message
public byte[] encrypt(byte[] message)
{
return (new BigInteger(message)).modPow(e, N).toByteArray();
}
// Decrypt message
public byte[] decrypt(byte[] message)
{
return (new BigInteger(message)).modPow(d, N).toByteArray();
}
}
