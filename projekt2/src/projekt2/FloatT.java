package projekt2;

public class FloatT extends RealNumber<FloatT>{
	public float val;
	public FloatT() {
		val=0;
	}
	public FloatT(float a) {
		val = a;
	}
	public FloatT(FloatT a) {
		this.val = a.val;
	}
	@Override
	public FloatT add(FloatT a) {
		FloatT ret = new FloatT(this);
		ret.val+=a.val;
		return ret;
	}
	@Override
	public FloatT add(long a) {
		FloatT ret = new FloatT(this);
		ret.val+=a;
		return ret;
	}
	@Override
	public FloatT sub(FloatT a) {
		FloatT ret = new FloatT(this);
		ret.val-=a.val;
		return ret;
	}
	@Override
	public FloatT sub(long a) {
		FloatT ret = new FloatT(this);
		ret.val-=a;
		return ret;
	}
	@Override
	public FloatT mul(FloatT a) {
		FloatT ret = new FloatT(this);
		ret.val*=a.val;
		return ret;
	}
	@Override
	public FloatT mul(long a) {
		FloatT ret = new FloatT(this);
		ret.val*=a;
		return ret;
	}
	@Override
	public FloatT div(FloatT a) {
		FloatT ret = new FloatT(this);
		ret.val/=a.val;
		return ret;
	}
	@Override
	public FloatT div(long a) {
		FloatT ret = new FloatT(this);
		ret.val/=a;
		return ret;
	}
	@Override
	public FloatT inv() {
		return new FloatT(1/this.val);
	}

	@Override
	public String toString() {
		return ""+val;
	}
	public FloatT[][] returnNew(int pion, int poziom) {
		return new FloatT[pion][poziom];
	}
	@Override
	public int eq(FloatT a) {
		if(this.val<a.val)
			return -1;
		if(this.val>a.val)
			return 1;
		return 0;
	}
	@Override
	public FloatT abs() {
		return new FloatT((this.val<0)?-this.val:this.val);
	}
}