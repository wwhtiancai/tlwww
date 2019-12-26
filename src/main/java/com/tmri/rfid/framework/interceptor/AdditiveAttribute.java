package com.tmri.rfid.framework.interceptor;

import java.lang.annotation.*;

/**
 * 标识注解，标识数据库实体类，额外属性
 */

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AdditiveAttribute {

}
