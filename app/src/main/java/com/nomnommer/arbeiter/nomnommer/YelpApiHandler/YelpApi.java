package com.nomnommer.arbeiter.nomnommer.YelpApiHandler;

import com.nomnommer.arbeiter.nomnommer.YelpApiHandler.YelpParser.OAuth.TwoStepOAuth;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
/**
 * Code sample for accessing the Yelp API V2.
 *
 * This program demonstrates the capability of the Yelp API version 2.0 by using the Search API to
 * query for businesses by a search term and location, and the Business API to query additional
 * information about the top result from the search query.
 *
 * <p>
 * See <a href="http://www.yelp.com/developers/documentation">Yelp Documentation</a> for more info.
 *
 */

public class YelpApi {
    /**
     * API DEFAULTS
     */
    private static final String API_HOST = "api.yelp.com";
    private static final String SEARCH_PATH = "/v2/search";

    /**
     * API Keys : NO PEEKING OK?!!
     */
    private static final String CONSUMER_KEY = "vt69-7nz0OaKtpCng18x8g";
    private static final String CONSUMER_SECRET = "mrOpQ1YV-Zblqiag-mdTH54u3Vo";
    private static final String TOKEN = "yXyEzTQ32dl1jAww3JNiGuIRFVGuJVf1";
    private static final String TOKEN_SECRET = "uwFDFWa2Itesy0A-e-pa3ZvFeK8";
    Token accessToken;

    /**
     * Setup the Yelp API OAuth credentials.
     *
     * @param consumerKey Consumer key
     * @param consumerSecret Consumer secret
     * @param token Token
     * @param tokenSecret Token secret
     */

    OAuthService service;
    public YelpApi(String consumerKey, String consumerSecret, String token, String tokenSecret)
    {
                this.service =
                        new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(consumerKey)
                                .apiSecret(consumerSecret).build();
                this.accessToken = new Token(token, tokenSecret);
     }

    /**
     * Creates and sends a request to the Search API by term and location.
     * <p>
     * See <a href="http://www.yelp.com/developers/documentation/v2/search_api">Yelp Search API V2</a>
     * for more info.
     *
     * @param term <tt>String</tt> of the search term to be queried
     * @param location <tt>String</tt> of the location
     * @return <tt>String</tt> JSON Response
     */
    public String searchForBusinessesByLocation(String term, String location, int limit) {
        OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("location", location);
        request.addQuerystringParameter("limit", String.valueOf(limit));
        return sendRequestAndGetResponse(request);
    }

    /**
     * Sends an {@link OAuthRequest} and returns the {@link Response} body.
     * @param request {@link OAuthRequest} corresponding to the API request
     * @return <tt>String</tt> body of API response
     */
    private String sendRequestAndGetResponse(OAuthRequest request) {
        System.out.println("Querying " + request.getCompleteUrl() + " ...");
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    /**
     * Creates and returns an {@link OAuthRequest} based on the API endpoint specified.
     *
     * @param path API endpoint to be queried
     * @return <tt>OAuthRequest</tt>
     */
    private OAuthRequest createOAuthRequest(String path) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://" + API_HOST + path);
        return request;
    }
}
