package projekt3;

import java.util.concurrent.ThreadLocalRandom;

public class MonteCarlo {
	
	public static int simulate(int N, int nY, int nN) {
		int nU = N - nY - nN;
		
		int NP = (N-1)*N/2;
		int ran = 0;
		
		while (nY!=0 && nN !=0) {
			//System.out.println(String.format("Y:%d N:%d, U:%d", nY, nN, nU));
			ran = ThreadLocalRandom.current().nextInt(NP);
			
			if(ran < nN*nU) {
				nN++;
				nU--;
				continue;
			}
			ran -= nN*nU;
			
			if(ran < nY*nU) {
				nY++;
				nU--;
				continue;
			}
			ran -= nY*nU;
			
			if(ran < nN*nY) {
				nN--;
				nY--;
				nU+=2;
				continue;
			}
		}
		//System.out.println(String.format("Y:%d N:%d, U:%d", nY, nN, nU));
		if(nY==0) {
			//System.out.println("NO");
			return -1;
		}
		else if(nN==0) {
			//System.out.println("YES");
			return 1;
		}
		else {
			//System.out.println("UNSURE");
			return 0;
		}
	}
	
	
	public static double simulationNTimes(int N, int nY, int nN, int n) {
		int win = 0;
		int lose = 0;
		int draw = 0;
		
		for(int i = 0; i < n; i++) {
			int result = simulate(N,nY,nN);
			switch(result) {
			case 1:
				win++;
				break;
			case 0:
				draw++;
				break;
			default:
				lose++;
				break;
			}
		}
		
		/*System.out.println("Py="+(double)win/(win+lose+draw));
		System.out.println("Pn="+(double)lose/(win+lose+draw));
		System.out.println("Pu="+(double)draw/(win+lose+draw));*/
		//System.out.println("P_"+nY+","+nN+"= "+(double)win/(win+lose+draw));
		return (double)win/(win+lose+draw);
	}
}