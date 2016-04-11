package jetools.common;

import java.math.BigDecimal;

/**
 * 金额类
 * */
public class Money implements Comparable<Money> {
	
	/**存放数据的类*/
	private BigDecimal value;
	
	/**
	 * 构造函数
	 * 默认标度为4
	 * 默认使用四舍五入
	 * */
	public Money(BigDecimal value) {
		this.value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * value:数目数值
	 * 强制使用4个标度和四舍五入
	 * */
	public Money(String value) {
		this(new BigDecimal(value));
	}

	/**
	 * value:数目数值
	 * 强制使用4个标度和四舍五入
	 * */
	public Money(int value) {
		this(new BigDecimal(value));
	}

	/**
	 * value:数目数值
	 * 强制使用4个标度和四舍五入
	 * */
	public Money(long value) {
		this(new BigDecimal(value));
	}

	/**
	 * value:数目数值
	 * 强制使用4个标度和四舍五入
	 * */
	public Money(float value) {
		this(new BigDecimal(value));
	}

	/**
	 * value:数目数值
	 * 强制使用4个标度和四舍五入
	 * */
	public Money(double value) {
		this(new BigDecimal(value));
	}

	/**
	 * value:数目数值
	 * 强制使用4个标度和四舍五入
	 * */
	public Money() {
		this(0);
	}

	//------------------------------取值-----------------------------------
	/**
	 * 以BigDecimal的方式返回数目数值
	 * 此方法不会损失精度
	 * */
	public synchronized BigDecimal value() {
		return this.value;
	}

	/**
	 * 以String的方式返回数目数值
	 * 此方法不会损失精度
	 * */
	public synchronized String strValue() {
		return this.value.toString();
	}
	
	/**
	 * 以byte的方式返回数目数值
	 * 此方法有可能损失精度
	 * */
	public synchronized byte byteValue() {
		return this.value.byteValue();
	}
	
	/**
	 * 以int的方式返回数目数值
	 * 此方法有可能损失精度
	 * */
	public synchronized int intValue() {
		return this.value.intValue();
	}
	
	/**
	 * 以Long的方式返回数目数值
	 * 此方法有可能损失精度
	 * */
	public synchronized Long longValue() {
		return this.value.longValue();
	}
	
	/**
	 * 以float的方式返回数目数值
	 * 此方法有可能损失精度
	 * */
	public synchronized float floatValue() {
		return this.value.floatValue();
	}
	
	/**
	 * 以double的方式返回数目数值
	 * 此方法有可能损失精度
	 * */
	public synchronized double doubleValue() {
		return this.value.doubleValue();
	}
	
	//------------------------------符号值-----------------------------------
	/**
	 * 获得数目的符号值
	 * 0返回0
	 * 正数返回1
	 * 负数返回-1
	 * */
	public synchronized int signum() {
		return this.value.signum();
	}
	
	//------------------------------特殊运算-----------------------------------
	/**
	 * 返回数目的绝对值
	 * 注意：如果数目本身是正数则返回数目类本身，如果数目本身是负数则返回一个新的类
	 * */
	public synchronized Money abs() {
		BigDecimal newValue = this.value.abs();
		return new Money(newValue);
	}
	
	/**
	 * 返回数目的符号相反值
	 * */
	public synchronized Money negate() {
		BigDecimal newValue = this.value.negate();
		return new Money(newValue);
	}

	/**
	 * 数目的次方运算
	 * 参数表示几次方
	 * */
	public synchronized Money pow(int n) {
		BigDecimal newValue = this.value.pow(n);
		return new Money(newValue);
	}
	
	//------------------------------比较大小-----------------------------------
	/**
	 * 比较2个数目的大小
	 * 两个数一样返回0
	 * 当前数大于参数返回1
	 * 当前数小于参数返回-1
	 * */
	@Override
	public synchronized int compareTo(Money other) {
		return this.value.compareTo(other.value);
	}
	
	/**
	 * 判断2个值是否相等
	 * */
	@Override
	public synchronized boolean equals(Object obj) {
		//如果类指针相等，直接返回true
		if(obj == this) {
			return true;
		}
		//检查obj是否可以转为Number类型
		if(obj instanceof Money) {
			Money temp = (Money)obj;
			return this.compareTo(temp) == 0;
		}
		return false;
	}
	
	/**
	 * 判断2个值是否相等
	 * */
	public synchronized boolean equal(Money other) {
		return this.compareTo(other) == 0;
	}
	
	/**
	 * 判断当前数是否大于传入参数
	 * */
	public synchronized boolean more(Money other) {
		return this.compareTo(other) > 0;
	}
	
	/**
	 * 判断当前数是否大于等于传入参数
	 * */
	public synchronized boolean moreOrEqual(Money other) {
		return this.compareTo(other) >= 0;
	}
	
	/**
	 * 判断当前数是否小于传入参数
	 * */
	public synchronized boolean less(Money other) {
		return this.compareTo(other) < 0;
	}
	
	/**
	 * 判断当前数是否小于等于传入参数
	 * */
	public synchronized boolean lessOrEqual(Money other) {
		return this.compareTo(other) <= 0;
	}
	
	/**
	 * 判断是否为0
	 * */
	public synchronized boolean isZero() {
		return this.compareTo(new Money()) == 0;
	}
	
	/**
	 * 判断是否大于0
	 * */
	public synchronized boolean moreZero() {
		return this.compareTo(new Money()) > 0;
	}
	
	/**
	 * 判断是否大于等于0
	 * */
	public synchronized boolean moreOrEqualZero() {
		return this.compareTo(new Money()) >= 0;
	}
	
	/**
	 * 判断是否小于0
	 * */
	public synchronized boolean lessZero() {
		return this.compareTo(new Money()) < 0;
	}
	
	/**
	 * 判断是否小于等于0
	 * */
	public synchronized boolean lessOrEqualZero() {
		return this.compareTo(new Money()) <= 0;
	}
	
	/**
	 * 比较2个数目的大小
	 * 返回小的那个数目
	 * 此操作直接返回2个类中的一个，不再生成一个新的类来放
	 * */
	public synchronized Money min(Money other) {
		return this.value.min(other.value).equals(this.value) ? this : other;
	}
	
	/**
	 * 比较2个数目的大小
	 * 返回大的那个数目
	 * 此操作直接返回2个类中的一个，不再生成一个新的类来放
	 * */
	public synchronized Money max(Money other) {
		return this.value.max(other.value).equals(this.value) ? this : other;
	}

	//------------------------------toString重载-----------------------------------
	@Override
	public synchronized String toString() {
		return this.value.toString();
	}
	
	//------------------------------加法-----------------------------------
	/**
	 * 加法
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money add(BigDecimal augend) {
		BigDecimal newValue = this.value.add(augend);
		return new Money(newValue);
	}
	
	/**
	 * 加法
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money add(Money augend) {
		return this.add(augend.value);
	}
	
	/**
	 * 加法
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money add(String augend) {
		return this.add(new BigDecimal(augend));
	}
	
	/**
	 * 加法
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money add(int augend) {
		return this.add(new BigDecimal(augend));
	}
	
	/**
	 * 加法
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money add(long augend) {
		return this.add(new BigDecimal(augend));
	}
	
	/**
	 * 加法
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money add(float augend) {
		return this.add(new BigDecimal(augend));
	}
	
	/**
	 * 加法
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money add(double augend) {
		return this.add(new BigDecimal(augend));
	}

	//------------------------------减法-----------------------------------
	/**
	 * 减法
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money subtract(BigDecimal subtrahend) {
		BigDecimal newValue = this.value.subtract(subtrahend);
		return new Money(newValue);
	}
	
	/**
	 * 减法
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money subtract(Money subtrahend) {
		return this.subtract(subtrahend.value);
	}
	
	/**
	 * 减法
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money subtract(String subtrahend) {
		return this.subtract(new BigDecimal(subtrahend));
	}
	
	/**
	 * 减法
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money subtract(int subtrahend) {
		return this.subtract(new BigDecimal(subtrahend));
	}
	
	/**
	 * 减法
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money subtract(long subtrahend) {
		return this.subtract(new BigDecimal(subtrahend));
	}
	
	/**
	 * 减法
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money subtract(float subtrahend) {
		return this.subtract(new BigDecimal(subtrahend));
	}
	
	/**
	 * 减法
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money subtract(double subtrahend) {
		return this.subtract(new BigDecimal(subtrahend));
	}

	//------------------------------乘法-----------------------------------
	/**
	 * 乘法
	 * 返回值标度为2个值之间的标度相加
	 * */
	public synchronized Money multiply(BigDecimal multiplicand) {
		BigDecimal newValue = this.value.multiply(multiplicand);
		return new Money(newValue);
	}
	
	/**
	 * 乘法
	 * 返回值标度为2个值之间的标度相加
	 * */
	public synchronized Money multiply(Money multiplicand) {
		return this.multiply(multiplicand.value);
	}
	
	/**
	 * 乘法
	 * 返回值标度为2个值之间的标度相加
	 * */
	public synchronized Money multiply(String multiplicand) {
		return this.multiply(new BigDecimal(multiplicand));
	}
	
	/**
	 * 乘法
	 * 返回值标度为2个值之间的标度相加
	 * */
	public synchronized Money multiply(int multiplicand) {
		return this.multiply(new BigDecimal(multiplicand));
	}
	
	/**
	 * 乘法
	 * 返回值标度为2个值之间的标度相加
	 * */
	public synchronized Money multiply(long multiplicand) {
		return this.multiply(new BigDecimal(multiplicand));
	}
	
	/**
	 * 乘法
	 * 返回值标度为2个值之间的标度相加
	 * */
	public synchronized Money multiply(float multiplicand) {
		return this.multiply(new BigDecimal(multiplicand));
	}
	
	/**
	 * 乘法
	 * 返回值标度为2个值之间的标度相加
	 * */
	public synchronized Money multiply(double multiplicand) {
		return this.multiply(new BigDecimal(multiplicand));
	}

	//------------------------------除法-----------------------------------
	/**
	 * 除法
	 * 考虑到可能会有除不尽的情况出现，因此需要强制指定标度，和超过标度后的舍入模式
	 * */
	public synchronized Money divide(BigDecimal divisor) {
		BigDecimal newValue = this.value.divide(divisor, 4, BigDecimal.ROUND_HALF_UP);
		return new Money(newValue);
	}
	
	/**
	 * 除法
	 * 考虑到可能会有除不尽的情况出现，因此需要强制指定标度，和超过标度后的舍入模式
	 * */
	public synchronized Money divide(Money divisor) {
		return this.divide(divisor.value);
	}
	
	/**
	 * 除法
	 * 考虑到可能会有除不尽的情况出现，因此需要强制指定标度，和超过标度后的舍入模式
	 * */
	public synchronized Money divide(String divisor) {
		return this.divide(new BigDecimal(divisor));
	}
	
	/**
	 * 除法
	 * 考虑到可能会有除不尽的情况出现，因此需要强制指定标度，和超过标度后的舍入模式
	 * */
	public synchronized Money divide(int divisor) {
		return this.divide(new BigDecimal(divisor));
	}
	
	/**
	 * 除法
	 * 考虑到可能会有除不尽的情况出现，因此需要强制指定标度，和超过标度后的舍入模式
	 * */
	public synchronized Money divide(long divisor) {
		return this.divide(new BigDecimal(divisor));
	}
	
	/**
	 * 除法
	 * 考虑到可能会有除不尽的情况出现，因此需要强制指定标度，和超过标度后的舍入模式
	 * */
	public synchronized Money divide(float divisor) {
		return this.divide(new BigDecimal(divisor));
	}
	
	/**
	 * 除法
	 * 默认使用10标度和四舍五入
	 * */
	public synchronized Money divide(double divisor) {
		return this.divide(new BigDecimal(divisor));
	}

	//------------------------------整数商-----------------------------------
	/**
	 * 获得除后的整数部分
	 * */
	public synchronized Money divideToIntegralValue(BigDecimal divisor) {
		BigDecimal newValue = this.value.divideToIntegralValue(divisor);
		return new Money(newValue);
	}
	
	/**
	 * 获得除后的整数部分
	 * */
	public synchronized Money divideToIntegralValue(Money divisor) {
		return this.divideToIntegralValue(divisor.value);
	}
	
	/**
	 * 获得除后的整数部分
	 * */
	public synchronized Money divideToIntegralValue(String divisor) {
		return this.divideToIntegralValue(new BigDecimal(divisor));
	}
	
	/**
	 * 获得除后的整数部分
	 * */
	public synchronized Money divideToIntegralValue(int divisor) {
		return this.divideToIntegralValue(Long.toString(divisor));
	}
	
	/**
	 * 获得除后的整数部分
	 * */
	public synchronized Money divideToIntegralValue(long divisor) {
		return this.divideToIntegralValue(Long.toString(divisor));
	}
	
	/**
	 * 获得除后的整数部分
	 * */
	public synchronized Money divideToIntegralValue(float divisor) {
		return this.divideToIntegralValue(Float.toString(divisor));
	}
	
	/**
	 * 获得除后的整数部分
	 * */
	public synchronized Money divideToIntegralValue(double divisor) {
		return this.divideToIntegralValue(Double.toString(divisor));
	}

	//------------------------------求模-----------------------------------
	/**
	 * 求模
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money remainder(BigDecimal divisor) {
		BigDecimal newValue = this.value.remainder(divisor);
		return new Money(newValue);
	}
	
	/**
	 * 求模
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money remainder(Money divisor) {
		return this.remainder(divisor.value);
	}
	
	/**
	 * 求模
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money remainder(String divisor) {
		return this.remainder(new BigDecimal(divisor));
	}
	
	/**
	 * 求模
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money remainder(int divisor) {
		return this.remainder(new BigDecimal(divisor));
	}
	
	/**
	 * 求模
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money remainder(long divisor) {
		return this.remainder(new BigDecimal(divisor));
	}
	
	/**
	 * 求模
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money remainder(float divisor) {
		return this.remainder(new BigDecimal(divisor));
	}
	
	/**
	 * 求模
	 * 返回值标度为2个值之间最大的标度
	 * */
	public synchronized Money remainder(double divisor) {
		return this.remainder(new BigDecimal(divisor));
	}
}
