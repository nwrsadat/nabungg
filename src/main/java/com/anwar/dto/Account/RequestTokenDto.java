package com.anwar.dto.Account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RequestTokenDto {
    private String username;
    private String password;
    private String subject;
    private String secretKey;
    private String audience;
}
