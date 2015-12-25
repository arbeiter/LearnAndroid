package com.nomnommer.arbeiter.nomnommer.YelpApiHandler.YelpParser.OAuth;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * What's this for? To get the build to pass.
 */
public class TwoStepOAuth extends DefaultApi10a {
    @Override
    public String getAccessTokenEndpoint() {
        return null;
    }

    @Override
    public String getAuthorizationUrl(Token arg0) {
        return null;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return null;
    }
}