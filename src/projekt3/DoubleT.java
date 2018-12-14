package projekt3;

public class DoubleT extends RealNumber<DoubleT>{
	public double val;
	public DoubleT() {
		val = 0;
	}
	public DoubleT(double a) {
		val = a;
	}
	public DoubleT(DoubleT a) {
		this.val = a.val;
	}
	@Override
	public DoubleT add(DoubleT a) {
		DoubleT ret = new DoubleT(this);
		ret.val+=a.val;
		return ret;
	}
	@Override
	public DoubleT add(long a) {
		DoubleT ret = new DoubleT(this);
		ret.val+=a;
		return ret;
	}
	@Override
	public DoubleT sub(DoubleT a) {
		DoubleT ret = new DoubleT(this);
		ret.val-=a.val;
		return ret;
	}
	@Override
	public DoubleT sub(long a) {
		DoubleT ret = new DoubleT(this);
		ret.val-=a;
		return ret;
	}
	@Override
	public DoubleT mul(DoubleT a) {
		DoubleT ret = new DoubleT(this);
		ret.val*=a.val;
		return ret;
	}
	@Override
	public DoubleT mul(long a) {
		DoubleT ret = new DoubleT(this);
		ret.val*=a;
		return ret;
	}
	@Override
	public DoubleT div(DoubleT a) {
		DoubleT ret = new DoubleT(this);
		ret.val/=a.val;
		return ret;
	}
	@Override
	public DoubleT div(long a) {
		DoubleT ret = new DoubleT(this);
		ret.val/=a;
		return ret;
	}
	@Override
	public DoubleT inv() {
		return new DoubleT(1/this.val);
	}

	@Override
	public String toString() {
		return ""+val;
	}
	public DoubleT[][] returnNew(int pion, int poziom) {
		return new DoubleT[pion][poziom];
	}
	public int eq(DoubleT a) {
		if(this.val<a.val)
			return -1;
		if(this.val>a.val)
			return 1;
		return 0;
	}
	@Override
	public DoubleT abs() {
		return new DoubleT((this.val<0)?-this.val:this.val);
	}

}