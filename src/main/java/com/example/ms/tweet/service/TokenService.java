package com.example.ms.tweet.service;

import com.example.ms.tweet.exception.AuthException;
import com.example.ms.tweet.model.dto.AuthPayloadDto;
import com.example.ms.tweet.model.dto.TokenDto;
import com.example.ms.tweet.model.jwt.AuthCacheData;
import com.example.ms.tweet.util.CacheUtil;
import com.example.ms.tweet.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

import static com.example.ms.tweet.mapper.factory.TokenFactory.buildAccessTokenClaimsSet;
import static com.example.ms.tweet.mapper.factory.TokenFactory.buildRefreshTokenClaimsSet;
import static com.example.ms.tweet.model.constant.AuthConstants.AUTH_CACHE_DATA_PREFIX;
import static com.example.ms.tweet.model.constant.AuthConstants.RSA;
import static com.example.ms.tweet.model.constant.AuthConstants.TOKEN_EXPIRE_DAY_COUNT;
import static com.example.ms.tweet.model.constant.ExceptionConstants.REFRESH_TOKEN_COUNT_EXPIRED_MESSAGE;
import static com.example.ms.tweet.model.constant.ExceptionConstants.REFRESH_TOKEN_EXPIRED_MESSAGE;
import static com.example.ms.tweet.model.constant.ExceptionConstants.TOKEN_EXPIRED_CODE;
import static com.example.ms.tweet.model.constant.ExceptionConstants.TOKEN_EXPIRED_MESSAGE;
import static com.example.ms.tweet.model.constant.ExceptionConstants.USER_UNAUTHORIZED_CODE;
import static com.example.ms.tweet.model.constant.ExceptionConstants.USER_UNAUTHORIZED_MESSAGE;
import static java.time.temporal.ChronoUnit.DAYS;
import static jodd.util.Base64.encodeToString;
import static org.springframework.util.Base64Utils.decodeFromString;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    private final CacheUtil cacheUtil;
    private final JwtUtil jwtUtil;

    @Value("${jwt.accessToken.expiration.time}")
    private int accessTokenExpirationTime;

    @Value("${jwt.refreshToken.expiration.time}")
    private int refreshTokenExpirationTime;

    public TokenDto generateToken(String userId, int refreshTokenExpirationCount) {
        if (refreshTokenExpirationCount == 0) refreshTokenExpirationCount = 50;

        var accessTokenClaimsSet = buildAccessTokenClaimsSet(
                userId,
                jwtUtil.generateSessionExpirationTime(accessTokenExpirationTime)
        );

        var refreshTokenClaimsSet = buildRefreshTokenClaimsSet(
                userId,
                refreshTokenExpirationCount,
                jwtUtil.generateSessionExpirationTime(refreshTokenExpirationTime)
        );

        var keyPair = jwtUtil.generateKeyPair();

        var authCacheData = AuthCacheData.of(
                accessTokenClaimsSet,
                encodeToString(keyPair.getPublic().getEncoded())
        );

        cacheUtil.saveToCache(AUTH_CACHE_DATA_PREFIX + userId, authCacheData, TOKEN_EXPIRE_DAY_COUNT, DAYS);

        var accessToken = jwtUtil.generateToken(accessTokenClaimsSet, keyPair.getPrivate());
        var refreshToken = jwtUtil.generateToken(refreshTokenClaimsSet, keyPair.getPrivate());

        return TokenDto.of(accessToken, refreshToken);
    }

    public TokenDto refreshTokens(String refreshToken) {

        var refreshTokenClaimsSet = jwtUtil.getClaimsFromRefreshToken(refreshToken);
        var userId = refreshTokenClaimsSet.getUserId();

        try {
            AuthCacheData authCacheData = cacheUtil.getBucket(AUTH_CACHE_DATA_PREFIX + userId);

            if (authCacheData == null) throw new AuthException(USER_UNAUTHORIZED_MESSAGE, USER_UNAUTHORIZED_CODE, 401);

            var publicKey = KeyFactory.getInstance(RSA).generatePublic(
                    new X509EncodedKeySpec(decodeFromString(authCacheData.getPublicKey()))
            );

            jwtUtil.verifyToken(refreshToken, (RSAPublicKey) publicKey);

            if (jwtUtil.isRefreshTokenTimeExpired(refreshTokenClaimsSet)) {
                throw new AuthException(REFRESH_TOKEN_EXPIRED_MESSAGE, USER_UNAUTHORIZED_CODE, 401);
            }

            if (jwtUtil.isRefreshTokenCountExpired(refreshTokenClaimsSet)) {
                throw new AuthException(REFRESH_TOKEN_COUNT_EXPIRED_MESSAGE, USER_UNAUTHORIZED_CODE, 401);
            }

            return generateToken(userId, refreshTokenClaimsSet.getCount() - 1);
        } catch (AuthException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new AuthException(USER_UNAUTHORIZED_MESSAGE, USER_UNAUTHORIZED_CODE, 401);
        }
    }

    public AuthPayloadDto validateToken(String accessToken) {

        try {
            var userId = jwtUtil.getClaimsFromAccessToken(accessToken).getUserId();
            AuthCacheData authCacheData = cacheUtil.getBucket(AUTH_CACHE_DATA_PREFIX + userId);

            if (authCacheData == null) {
                throw new AuthException(TOKEN_EXPIRED_MESSAGE, TOKEN_EXPIRED_CODE, 406);
            }

            var publicKey = KeyFactory.getInstance(RSA).generatePublic(
                    new X509EncodedKeySpec(decodeFromString(authCacheData.getPublicKey()))
            );

            jwtUtil.verifyToken(accessToken, (RSAPublicKey) publicKey);

            if (jwtUtil.isTokenExpired(authCacheData.getAccessTokenClaimsSet().getExpirationTime())) {
                throw new AuthException(TOKEN_EXPIRED_MESSAGE, TOKEN_EXPIRED_CODE, 406);
            }

            return AuthPayloadDto.of(userId);
        } catch (AuthException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(String.valueOf(ex));
            throw new AuthException(USER_UNAUTHORIZED_MESSAGE, USER_UNAUTHORIZED_CODE, 401);
        }
    }
}