package projekt3;


public class Iter {
	private static int n =10000;
	private static double precision = Math.pow(10, -6);
	private int size;
	private double[][] G;//przeksztalcone A
	private double[][] c;//przeksztalcone b
	private double[][] W;//wynikowa x stare x nowe
	private double[][] W1;//poprzedniawartoscW
	private double[][] x;
	
	public Iter(int size, double[][] g, double[][] c, double[][] x) {
		super();
		W = new double[size][1];
		W1 = new double[size][1];
		this.size = size;
		this.G = g;
		this.c = c;
		this.x = x;
		for(int i=0; i<size; i++) {
			W[i][0] = c[i][0];
		}
	}
	

	public void jacobiWOTransform() {
		double[][] mul;
		for(int i =0; i<n; i++) {
			mul = mul(G,W);
			for(int j=0; j<mul.length; j++) {
				W1[j][0] = W[j][0];
				W[j][0] = mul[j][0];
				//System.out.println(mul[j][0]);
			}
			if (getDifference()<=precision) {
				//System.out.println("Koniec J dla i="+i);
				System.out.print("\t"+i+"\t");
				break;
			}
		}
	}

	
	public double[][] mul(double[][] A, double[][] B) {
		double[][] wynik = new double[A.length][B[0].length];
		for(int i=0; i<A.length; i++) {
			for(int j=0; j<B[0].length; j++) {
				for(int k=0; k<A[0].length;k++) {
					wynik[i][j]=wynik[i][j] + A[i][k]*B[k][j];

				}
			}
		}
		return wynik;
	}
	public double[][] mulGS(double[][] A, double[][] B) {
		double[][] wynik = new double[A.length][B[0].length];
		for (int i = 0; i < wynik.length; i++) {
			for (int j = 0; j < wynik[0].length; j++) {
				wynik[i][j]=B[i][j];
			}
		}
		for(int i=0; i<A.length; i++) {
			for(int j=0; j<B[0].length; j++) {
				double temp =0;
				for(int k=0; k<A[0].length;k++) {
					temp += A[i][k]*wynik[k][j];
				}
				wynik[i][j]=temp;
			}
		}
		return wynik;
	}
	
	public String wynik() {
		String s="wynik z jacobiego: ";
		for(int i=0; i<W.length;i++)
			s += W[i][0]+" vs x:"+x[i][0]+"\t";
		return s;
	}
	
	public double getDifference() {
		double ret = 0.0;
		for(int i = 0; i < W.length; i++) {
			ret+= (W[i][0]-W1[i][0])*(W[i][0]-W1[i][0]);
		}
		return Math.sqrt(ret);
	}

	public void gaussWTransform() {
		double[][] mul;
		for(int i =0; i<n; i++) {
			mul = mulGS(G,W);
			for(int j=0; j<mul.length; j++) {
				W1[j][0] = W[j][0];
				W[j][0] = mul[j][0];
				//System.out.println(mul[j][0]);
			}
			if (getDifference()<=precision) {
				//System.out.println("Koniec GS dla i="+i);
				System.out.print("\t"+i);
				break;
			}
		}
		
	}
	public double[][] getW() {
		return W;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public double[][] getG() {
		return G;
	}
	public void setG(double[][] g) {
		G = g;
	}
	public double[][] getC() {
		return c;
	}
	public void setC(double[][] c) {
		this.c = c;
	}
	public double[][] getX() {
		return x;
	}
	public void setX(double[][] x) {
		this.x = x;
	}

	@Override
	public String toString() {
		String s = "Jacobi [size=" + size + ", G=\n";
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				s+=G[i][j]+"\t";
			}
			s+="\n";
		}
		return s;
	}
	
	
}
