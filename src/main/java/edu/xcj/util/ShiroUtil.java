package edu.xcj.util;

import edu.xcj.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/4/27 23:24
 */
public class ShiroUtil {
    public  static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
