package com.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * Author : MrZ
 *
 * @Description Created in 15:36 on 2017/5/10.
 * Modified By :
 */
@Configuration
@EnableAuthorizationServer
public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;


    //这个bean是必不可少的，不然的话就会初始化报错的。
    @Bean
    public ApprovalStore approvalStore() throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.checkTokenAccess("permitAll()");
        oauthServer.allowFormAuthenticationForClients();
    }

    //获取client在授权服务中的数据
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
    }

    //token 处理
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore).approvalStore(approvalStore())
                .authenticationManager(authenticationManager);
    }
}
