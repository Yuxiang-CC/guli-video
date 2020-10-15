package com.yuxiang.guli.common.base.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Yuxiang
 * @create: 2020-06-28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtInfo {

    private String id;
    private String nickName;
    private String avatar;
}
