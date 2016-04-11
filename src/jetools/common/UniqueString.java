package jetools.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UniqueString {

	//最后一次操作的时间
	private long lastTime;
	//时间内的次数
	private int count;

	public UniqueString() {
		this.lastTime = System.currentTimeMillis();
		this.count = 0;
	}
	
	public synchronized String get() {
		//判断是否需要重置索引
		long curTime = System.currentTimeMillis();
		if(curTime > this.lastTime) {
			this.lastTime = curTime;
			this.count = 0;
		}
		//生成日期
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateStr = format.format(new Date(this.lastTime));
		//将日期和最后时间以及次数组合成字串
		this.count++;
		long timeCount = this.lastTime + this.count;
		return dateStr + timeCount;
	}
}
