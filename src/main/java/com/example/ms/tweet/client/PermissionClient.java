package com.example.ms.tweet.client;

import com.example.ms.tweet.model.CheckPermissionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-permission", url = "${client.ms-permission.endpoint}")
public interface PermissionClient {
    @PostMapping("/v1/permission/check")
    boolean checkPermission(@RequestBody CheckPermissionDto dto);
}
