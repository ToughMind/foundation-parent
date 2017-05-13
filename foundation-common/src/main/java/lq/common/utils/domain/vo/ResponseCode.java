package lq.common.utils.domain.vo;

/**
 * 公用返回码：响应码枚举。
 * 
 * @author 刘泉
 * @date 2016年10月13日 下午9:05:22
 */
public enum ResponseCode {
	OK("200"), // 移动端成功响应
	FAIL("400"), // 移动端请求失败
	EXPIRE("600"), // 移动端需要登录
	ORDER_PAYED("601"), // 订单已支付
	ORDER_SUBMIT_SUFFI("602"), // 订单提交时商品库存不足
	CART_SKU_FULL("603"), // 添加购物车时，购物车sku数量已满
	DATA_WRONG("604"), // 数据过期
	PAY_ORDER_OVERDUE("605"), // 订单支付时已经过期
	COUPON_INVALID("607"), // 订单提交，优惠券出现错误-过期/已失效/已使用
	CART_SKU_VOLUME_SUFFI("608"), // 购物车更新时提示sku库存不足
	COMPOSE_ITEM_NOT_MATCH("609"), // 组单时,购物车数量和所选数量不一致了
	CATEGORY_ID_WRONG("610"), // 类目ID出错
	ITEM_NOT_SOLD_OUT("612"), // 商品没有售罄
	MOBILE_INVALID("613"), // 手机号格式不正确
	GIFTCARD_LOCK("620"), //  被禁止添加卡片
	GIFTCARD_LACK("621"), // 下单时礼品卡余额不足
	PAYPASSWORD_LACK("622"), // 使用礼品卡必须设置支付密码
	PAYPSSWORD_WRONG("623"), // 支付密码验证不通过
	MOBILE_BINDED("631"), // 手机已被绑定到其他帐号
	ORDER_SUBMIT_PROM_NOTMATCH("641"), // 订单提交时促销活动不满足
	ORDER_SUBMIT_PRESELL_CHANGE("642"), // 订单提交时预售商品状态变化
	ORDER_DELETE("650"); // 订单已被删除

	private String code;

	private ResponseCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code;
	}
}
