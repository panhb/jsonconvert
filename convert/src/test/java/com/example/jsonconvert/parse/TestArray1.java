package com.example.jsonconvert.parse;

import com.example.jsonconvert.JsonConvert;
import com.example.jsonconvert.JsonParse;
import com.example.jsonconvert.model.RootElement;
import com.example.jsonconvert.utils.CommonUtil;

/**
 * @author hongbo.pan
 * @date 2020/11/10
 */
public class TestArray1 {
    public static void main(String[] args) {
        String json = "[[{\"id\":\"1\",\"name\":\"Json技术\"},{\"id\":\"2\",\"name\":\"java技术\"}],[{\"id\":\"1\",\"name\":\"Json技术\"},{\"id\":\"2\",\"name\":\"java技术\"}]]";
        String json2 = "{\"request\":{\"locale\":null,\"industryCode\":\"GM\",\"tenantCode\":\"JXGM\",\"code\":\"XSJS210425000004\"},\"response\":{\"message\":{\"code\":0,\"text\":\"操作成功\"},\"dto\":{\"pkid\":214,\"ttCode\":\"XSJS\",\"ttName\":\"标准销售结算单\",\"otCode\":\"XSJS\",\"otName\":\"标准销售结算单\",\"code\":\"XSJS210425000004\",\"externalNo\":null,\"status\":\"A\",\"auditStatus\":\"PASS\",\"date\":\"2021-04-25 00:00:00\",\"periodDateStart\":null,\"periodDateEnd\":null,\"leCode\":\"1809\",\"leName\":\"找钢网\",\"leFullname\":\"上海找钢网信息科技股份有限公司\",\"siteCode\":\"ZG_SH_JC\",\"siteName\":\"上海建材（QT）\",\"siteFullname\":\"上海建材（QT）\",\"tcTrCode\":\"C2100987622\",\"tcTrName\":\"武汉胖猫加工有限公司-bcc\",\"tcTrFullname\":\"武汉胖猫加工有限公司-bcc\",\"currCode\":\"CNY\",\"currCodeText\":\"人民币（CNY）\",\"remark\":null,\"interestAmt\":0E-8,\"otherAmt\":0E-8,\"amt\":-16.76000000,\"draftedDatetime\":\"2021-04-25 11:43:32\",\"draftedUserCode\":\"baicongcong\",\"draftedUserName\":\"白聪聪\",\"lastDraftedDatetime\":\"2021-04-25 11:50:50\",\"lastDraftedUserCode\":\"baicongcong\",\"lastDraftedUserName\":\"白聪聪\",\"approvedDatetime\":\"2021-04-25 11:50:50\",\"approvedUserCode\":\"baicongcong\",\"approvedUserName\":\"白聪聪\",\"lockTime\":\"2021-04-25 11:50:47\",\"addedTime\":\"2021-04-25 11:43:32\",\"addedByCode\":\"baicongcong\",\"addedByName\":\"白聪聪\",\"addedByIp\":\"172.16.10.88\",\"lastModifiedTime\":\"2021-04-25 11:50:50\",\"lastModifiedByCode\":\"baicongcong\",\"lastModifiedByName\":\"白聪聪\",\"lastModifiedByIp\":\"10.0.76.244\",\"isDelete\":\"N\",\"tenantCode\":\"JXGM\",\"industryCode\":\"GM\",\"firstSaveDatetime\":\"2021-04-25 11:49:04\",\"amtDecimals\":2,\"priceDecimals\":2,\"detailList\":[{\"pkid\":970,\"arId\":6370,\"arpId\":10866,\"arStatus\":\"S\",\"arStatusText\":\"结算关闭\",\"arSettlementCode\":\"XSJS210425000004\",\"sn\":1,\"type\":\"GOODS\",\"soCode\":\"RSO210423000001\",\"itlCode\":\"RSOR2104230000000004\",\"itlSn\":\"1\",\"itType\":\"GDS\",\"itCode\":\"00607500004\",\"itName\":\"三级螺纹钢\",\"itDesc\":\"HRB400/12*78/西王\",\"itMatCode\":\"17\",\"itMatName\":\"HRB400\",\"itFtyCode\":\"47\",\"itFtyName\":\"西王\",\"itSpec\":\"12*78\",\"itIcCode\":\"006075\",\"itIcName\":\"三级螺纹钢\",\"price\":4.19000000,\"oriPrice\":4.19000000,\"costAveragePrice\":0E-8,\"amt\":12.57000000,\"qty\":3.00000000,\"remark\":null,\"tenantCode\":\"JXGM\",\"industryCode\":\"GM\",\"qualityStatus\":null,\"qualityStatusText\":null,\"direction\":-1.00000000,\"directionText\":\"退货\",\"um\":\"T\",\"qtyDecimals\":5,\"sosgSnType\":null,\"itlQty\":3.00000000},{\"pkid\":968,\"arId\":6381,\"arpId\":10881,\"arStatus\":\"S\",\"arStatusText\":\"结算关闭\",\"arSettlementCode\":\"XSJS210425000004\",\"sn\":2,\"type\":\"GOODS\",\"soCode\":\"RSO210423000001\",\"itlCode\":\"RSOR2104230000000006\",\"itlSn\":\"1\",\"itType\":\"GDS\",\"itCode\":\"00607500004\",\"itName\":\"三级螺纹钢\",\"itDesc\":\"HRB400/12*78/西王\",\"itMatCode\":\"17\",\"itMatName\":\"HRB400\",\"itFtyCode\":\"47\",\"itFtyName\":\"西王\",\"itSpec\":\"12*78\",\"itIcCode\":\"006075\",\"itIcName\":\"三级螺纹钢\",\"price\":4.19000000,\"oriPrice\":4.19000000,\"costAveragePrice\":0E-8,\"amt\":20.95000000,\"qty\":5.00000000,\"remark\":null,\"tenantCode\":\"JXGM\",\"industryCode\":\"GM\",\"qualityStatus\":null,\"qualityStatusText\":null,\"direction\":-1.00000000,\"directionText\":\"退货\",\"um\":\"T\",\"qtyDecimals\":5,\"sosgSnType\":null,\"itlQty\":5.00000000},{\"pkid\":969,\"arId\":6382,\"arpId\":10882,\"arStatus\":\"S\",\"arStatusText\":\"结算关闭\",\"arSettlementCode\":\"XSJS210425000004\",\"sn\":3,\"type\":\"GOODS\",\"soCode\":\"RSO210423000001\",\"itlCode\":\"RSOR2104230000000006\",\"itlSn\":\"2\",\"itType\":\"GDS\",\"itCode\":\"00607500004\",\"itName\":\"三级螺纹钢\",\"itDesc\":\"HRB400/12*78/西王\",\"itMatCode\":\"17\",\"itMatName\":\"HRB400\",\"itFtyCode\":\"47\",\"itFtyName\":\"西王\",\"itSpec\":\"12*78\",\"itIcCode\":\"006075\",\"itIcName\":\"三级螺纹钢\",\"price\":4.19000000,\"oriPrice\":4.19000000,\"costAveragePrice\":0E-8,\"amt\":-16.76000000,\"qty\":-4.00000000,\"remark\":null,\"tenantCode\":\"JXGM\",\"industryCode\":\"GM\",\"qualityStatus\":null,\"qualityStatusText\":null,\"direction\":-1.00000000,\"directionText\":\"退货\",\"um\":\"T\",\"qtyDecimals\":5,\"sosgSnType\":null,\"itlQty\":-3.00000000}],\"oaProcessname\":null,\"oaFormid\":null}}}";
        try {
            System.out.println(JsonParse.parse(json2));
            System.out.println(CommonUtil.toJson(JsonParse.parse(json2)));
            System.out.println(JsonConvert.convertToString(json2, JsonParse.parse(json2)));
            System.out.println(json2.equals(JsonConvert.convertToString(json2, JsonParse.parse(json2))));

            RootElement rootElement = JsonParse.parse(json2);
            rootElement.getObjectElement().getElements().get(0).setTargetPropName("target_request");
            rootElement.getObjectElement().getElements().get(0).getObjectElement().getElements().get(0).setTargetPropName("target_locale");
            System.out.println(JsonConvert.convertToString(json2, rootElement));

            System.out.println(JsonConvert.convertToString(json2, rootElement, "response.dto.detailList"));

            System.out.println(JsonParse.parse(json2, "response.dto.detailList"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
