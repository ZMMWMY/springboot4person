package com.oauth2.server.core;


import org.springframework.security.oauth2.provider.*;

import java.util.List;

/**
 * Author : MrZ
 *
 * @Description Created in 16:33 on 2017/5/10.
 * Modified By :
 */
public class ClientDetailService implements ClientDetailsService, ClientRegistrationService {
    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return null;
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {

    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {

    }

    @Override
    public void updateClientSecret(String s, String s1) throws NoSuchClientException {

    }

    @Override
    public void removeClientDetails(String s) throws NoSuchClientException {

    }

    @Override
    public List<ClientDetails> listClientDetails() {
        return null;
    }
}
