package com.mrz.secKill.util;

import com.mrz.secKill.common.Constant;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.util.DigestUtils;

/**
 * Author : MrZ
 *
 * @Description Created in 16:37 on 2017/5/19.
 * Modified By :
 */
public class SecKillUtil {

    public static String goodSecKill(Integer id) {
        return DigestUtils.md5DigestAsHex((Constant.SystemCode.SALT + id).getBytes());
    }

}
