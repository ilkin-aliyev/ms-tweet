package com.example.ms.tweet.service;

import com.example.ms.tweet.client.PermissionClient;
import com.example.ms.tweet.model.CheckPermissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(value = "permissionService")
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionClient permissionClient;

    public boolean checkPermission(String userId, String permissionName, String productName) {
        boolean result;
        try {
            result = permissionClient.checkPermission(
                    new CheckPermissionDto(userId, permissionName, productName));
        } catch (Exception e) {
            return false;
        }
        return result;
    }
}
