package Position_physics_calculation;
/* File Example00.java */
 public class Example00 {
	public static void main (String[] args) {

		// plain variable:
		int i = 555;

		// array:
		int[] t;
		t = new int[10];

		// new instance of 'MyNumber' (defined below):
		MyNumber x = new MyNumber();

		// call to a method of 'MyNumber':
		x.setN(7);
		i = x.getN();

		// library methods:
		for(int k=0; k < t.length; k++) {
			t[k] = k*k/2;
			System.out.println("t[" + k + "]=" + t[k]);
		}
		
		System.out.println("\ni = " + i);
		System.out.print("x = ");
		System.out.print(x);
		System.out.println();

		// etc...
		x.setN(99);
		System.out.println("and now x.n =  " + x.getN() + ";");
		System.out.println(" ** THE END **\n");
	}
 }

class MyNumber {

// fields:
	private int n;
	private String name;

// constructors:
	public MyNumber(){
		n = 0;
		name = "noname";
	}

	public MyNumber(String name){
		this.name = name ;
	}

// methods:
	public void setN(int k){
		n = k;
	}

	public int getN(){
		return n;
	}
}