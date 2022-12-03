package com.example.ms.tweet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckPermissionDto {
    private String userId;
    private String permissionName;
    private String productName;
}
