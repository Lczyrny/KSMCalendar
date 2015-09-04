package pl.krakowskascenamuzyczna.ksmcalendar;

import retrofit.RequestInterceptor;

/**
 * Created by Bartos on 2015-09-04.
 */
public class HeaderHelpingRequestInterceptor implements RequestInterceptor {

    private static final String COOKIE_HEADER_KEY = "Cookie";
    private static final String USER_AGENT_HEADER_KEY = "User-Agent";

    private String cCookie;
    private String cUserAgent;

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader(USER_AGENT_HEADER_KEY, cUserAgent);
        if (cCookie != null) {
            request.addHeader(COOKIE_HEADER_KEY, cCookie);
        }
    }
    public void setCookie(String cookie){
        cCookie = cookie;
    }
    public void clearCredentials() {
        cCookie = null;
    }
}
