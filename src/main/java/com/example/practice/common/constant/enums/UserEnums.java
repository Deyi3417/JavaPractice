package com.example.practice.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**

 * 用户枚举类
 * @author : liudy23
 * @data : 2023/5/23
 */
public class UserEnums {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public enum UserGenderEnum {
        MAN(0,"男"),
        WOMAN(1,"女");
        private Integer code;
        private String name;
        public static UserGenderEnum byCode(Integer code) {
            return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findFirst().orElse(MAN);
        }
    }

}
