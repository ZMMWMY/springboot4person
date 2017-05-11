package com.oauth2.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Author : MrZ
 *
 * @Description Created in 15:36 on 2017/5/10.
 * Modified By :
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    AuthorizationEndpoint  是用于授权服务请求的。默认的URL是：/oauth/authrize。
//
//    TokenEndpoint  是用于获取访问令牌（Access Tokens）的请求。默认的URL是：/oauth/token。

    @Autowired
    TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${config.oauth2.privateKey}")
    private String privateKey = "test";

    @Value("${config.oauth2.publicKey}")
    private String publicKey = "test";


    //    在令牌端点上定义了安全约束。
    //为什么不用自动配置。因为/oauth/check_token默认是denyAll.
    //必须手动设置oauthServer.checkTokenAccess("isAuthenticated()");
    //才访问能验证Access Token。
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.checkTokenAccess("permitAll()");
        oauthServer.allowFormAuthenticationForClients();
    }

    //获取client在授权服务中的数据
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        super.configure(clients);
        clients.inMemory().and().withClient("testClientId").secret("testClientSecret")
                .authorizedGrantTypes("client_credentials", "authorization_code", "refresh_token")
                .scopes("read", "write")
                .redirectUris("http://localhost:8080/client/");

    }

    //token 处理
    //使用JWT 使用此加密Token不需要后台存储
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).approvalStore(approvalStore()).accessTokenConverter(tokenEnhancer())
                .authenticationManager(authenticationManager);
    }

    //这个bean是必不可少的，不然的话就会初始化报错的。
    @Bean
    public ApprovalStore approvalStore() throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore());
        return store;
    }


    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        log.info("Initializing JWT with public key:\n" + publicKey);
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
//        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }
}
