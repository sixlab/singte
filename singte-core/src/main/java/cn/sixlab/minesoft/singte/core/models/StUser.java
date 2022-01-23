package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StUser extends BaseModel {

    private String username;

    private String showName;

    private String password;

    private String mobile;

    private String email;

    private String role;

    private String token;

    private Date tokenValid;

}