package com.example.jieyue.user.service;

import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.common.entity.SysOrder;
import com.example.jieyue.common.mapper.SysCartMapper;
import com.example.jieyue.common.mapper.SysGoodsMapper;
import com.example.jieyue.common.mapper.SysMtMapper;
import com.example.jieyue.common.mapper.SysOrderMapper;
import com.example.jieyue.common.utils.IsEmptyUtil;
import com.example.jieyue.common.utils.QRCodeUtil;
import com.example.jieyue.common.utils.StringUtil;
import com.example.jieyue.wxpay.config.MyWXPayConfig;
import com.example.jieyue.wxpay.sdk.WXPay;
import com.example.jieyue.wxpay.sdk.WXPayConstants;
import com.example.jieyue.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>微信支付服务类</p>
 * @author Bosen
 * 2020/12/4 20:53
 */
@Service
public class WxPayService {
    @Autowired
    StringUtil stringUtil;
    @Autowired
    SysOrderMapper orderMapper;
    @Autowired
    SysGoodsMapper goodsMapper;
    @Autowired
    SysMtMapper merchantMapper;
    @Autowired
    SysCartMapper cartMapper;
    @Value("${site-url}")
    String siteUrl;

    MyWXPayConfig config = new MyWXPayConfig();
    WXPay wxPay = new WXPay(config);

    String staticPath;
    String classPath;

    IsEmptyUtil isEmptyUtil = new IsEmptyUtil();

    public WxPayService() throws Exception {
        this.classPath = ResourceUtils.getURL("classpath:static/").getPath();
    }

    /**
     * <p>添加订单</p>
     * @return
     *-1 sql语句执行失败
     * 0 必填信息不能为空
     * 1 添加成功
     */
    @Transactional
    public String addOrder(String nums,String mark, String users, String merchants, String prices, String notes,
                           String goods,String address,String name,String phone,String code,int way,String carts) {
        if (isEmptyUtil.strings(address,name,phone)){
            return "0";
        }
        // 获取当前时间戳
        long createTime = System.currentTimeMillis();
        DecimalFormat dfPrice = new DecimalFormat("#.00");
        String[] numArr = getStringArray(nums);
        String[] userArr = getStringArray(users);
        String[] cartArr = getStringArray(carts);
        String[] goodsArr = getStringArray(goods);
        String[] pricesArr = getStringArray(prices);
        String[] merchantsArr = getStringArray(merchants);
        for (int i = 0; i < numArr.length;i++){
            // 生成订单号
            String orderId = getOrderId();
            // 获取商户信息
            SysMt merchant = merchantMapper.findById(Integer.valueOf(merchantsArr[i]));
            // 执行sql语句
            int sql = -1;
            if (cartArr[i].equals("0")){
                sql = orderMapper.insert1(orderId,createTime,Integer.valueOf(numArr[i]),mark,Integer.valueOf(userArr[i]),
                        Integer.valueOf(merchantsArr[i]),new BigDecimal(dfPrice.format(Double.valueOf(pricesArr[i]))),
                        Integer.valueOf(goodsArr[i]),notes,address,name,phone,code,way,merchant.getRatio());
            }else{
                sql = orderMapper.insert2(orderId,createTime,Integer.valueOf(numArr[i]),mark,Integer.valueOf(userArr[i]),
                        Integer.valueOf(merchantsArr[i]),new BigDecimal(dfPrice.format(Double.valueOf(pricesArr[i]))),
                        Integer.valueOf(goodsArr[i]),notes,address,name,phone,code,way,cartArr[i],merchant.getRatio());
            }

            // 将商品库存做相应的减少
            int delStock = goodsMapper.delStock(Integer.valueOf(goodsArr[i]),Integer.valueOf(numArr[i]));

            // 库存检查，库存少于零时回滚
            if (sql != 1 || delStock != 1 || goodsMapper.findById(Integer.valueOf(goodsArr[i])).getStock() < 0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        String allPrice = getAllPrice(pricesArr,numArr);
        return wxCodeUrl(mark,allPrice);
    }

    /**
     * <p>分割js传递的数组</p>
     */
    public String[] getStringArray(String array){
        List<String> list = new ArrayList<>();
        return array.split(",");
    }
    
    /**
     * <p>总金额运算</p>
     */
    public String getAllPrice(String[] prices,String[] nums){
        DecimalFormat dfPrice = new DecimalFormat("#.00");
        DecimalFormat dfNum = new DecimalFormat("#");
        BigDecimal allPrice = new BigDecimal(0.00);

        for (int i = 0;i < prices.length;i++){
            BigDecimal price = new BigDecimal(dfPrice.format(Double.valueOf(prices[i])));
            BigDecimal num = new BigDecimal(dfNum.format(Double.valueOf(nums[i])));
            allPrice = allPrice.add(price.multiply(num));
        }
        return allPrice.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString();
    }

    /**
     * <p>生成二维码</p>
     */
    public String wxCodeUrl(String orderMark,String price){
        Map<String, String> data = new HashMap<>();
        data.put("body", "捷阅网商品");
        data.put("out_trade_no", orderMark);
        data.put("device_info", orderMark);
        BigDecimal temp = new BigDecimal(100);
        data.put("total_fee", price);
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", siteUrl+"/user/wxpay/notify");
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        data.put("product_id", orderMark);
        try {
            Map<String, String> resp = wxPay.unifiedOrder(data);
            System.out.println(resp);
            QRCodeUtil.zxingCodeCreate(resp.get("code_url"),classPath+"/data/pay/",orderMark,500,"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String codeUrl = "/data/pay/"+orderMark+".jpg";
        orderMapper.updateCodeUrl(codeUrl,orderMark);
        return codeUrl;
    }

    /**
     * <p>生成订单号</p>
     */
    public String getOrderId(){
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] c = s.toCharArray();
        Random random = new Random();
        String word = "";
        for( int i = 0; i < 3; i ++) {
            word = word + c[random.nextInt(c.length)];
        }
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHmmss");
        String time = sdf.format(System.currentTimeMillis());
        String number = ""+Math.round(Math.random()*(999999-100000)+100000);
        String result = word + time + number;

        return result;
    }
    
    /**
     * <p>回调处理</p>
     */
    public String wxNotify(HttpServletRequest request) throws Exception {
        return payAsyncNotifyVerificationSign(request);
    }

    /**
     * <p>微信支付异步通知验证签名</p>
     * @param request
     * @return
     */
    public String payAsyncNotifyVerificationSign(HttpServletRequest request) throws Exception {
        String returnXmlMessage = null;
        String notifyXmlData = null;
        try {
            notifyXmlData = readXmlFromStream(request);

            Map<String, String> notifyMapData = WXPayUtil.xmlToMap(notifyXmlData);

            // 验证签名
            boolean signatureValid = wxPay.isPayResultNotifySignatureValid(notifyMapData);
            if (signatureValid) {
                // 订单支付成功之后相关业务逻辑...
                orderMapper.updateState(System.currentTimeMillis(),notifyMapData.get("device_info"));
                List<SysOrder> orderList = orderMapper.findByOrderMark(notifyMapData.get("device_info"));
                for (SysOrder order : orderList) {
                    cartMapper.deleteById(order.getCartId());
                }
                // 一切正常返回的xml数据
                returnXmlMessage = setReturnXml(WXPayConstants.SUCCESS, "OK");

            } else {
                returnXmlMessage = setReturnXml(WXPayConstants.FAIL, "Verification sign failed2");
            }

        } catch (IOException e) {
            returnXmlMessage = setReturnXml(WXPayConstants.FAIL, "An exception occurred while reading the WeChat server returning xml data in the stream.");
        } catch (Exception e) {
            returnXmlMessage = setReturnXml(WXPayConstants.FAIL, "Payment successful, exception occurred during asynchronous notification processing.");
        }

        return returnXmlMessage;
    }

    /**
     * <p>从流中读取微信返回的xml数据</p>
     * @param httpServletRequest
     * @return
     * @throws IOException
     */
    private String readXmlFromStream(HttpServletRequest httpServletRequest) throws IOException {
        InputStream inputStream = httpServletRequest.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        final StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } finally {
            bufferedReader.close();
            inputStream.close();
        }

        return sb.toString();
    }

    /**
     * <p>设置返回给微信服务器的xml信息</p>
     * @param returnCode
     * @param returnMsg
     * @return
     */
    private String setReturnXml(String returnCode, String returnMsg) {
        return "<xml><return_code><![CDATA[" + returnCode + "]]></return_code><return_msg><![CDATA[" + returnMsg + "]]></return_msg></xml>";
    }
}
