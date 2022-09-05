package com.dimata.service.general.harisma.core.security.mechanism;

import io.quarkus.security.identity.IdentityProviderManager;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.AuthenticationRequest;
import io.quarkus.vertx.http.runtime.security.ChallengeData;
import io.quarkus.vertx.http.runtime.security.HttpAuthenticationMechanism;
import io.quarkus.vertx.http.runtime.security.HttpCredentialTransport;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
@Alternative
@Priority(1)
public class DimataAuthMechanism implements HttpAuthenticationMechanism {

    @Inject
    DimataAuthMechanismImpl implement;

    @Override
    public Uni<SecurityIdentity> authenticate(RoutingContext context, IdentityProviderManager identityProviderManager) {
        return implement.authentication(context);
    }

    @Override
    public Uni<ChallengeData> getChallenge(RoutingContext context) {
       return Uni.createFrom().optional(Optional.empty());
    }

    @Override
    public Set<Class<? extends AuthenticationRequest>> getCredentialTypes() {
        return new HashSet<>();
    }

    @Override
    public HttpCredentialTransport getCredentialTransport() {
        // TODO Auto-generated method stub
        return null;
    }

}