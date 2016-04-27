package Libraries;

import com.android.volley.DefaultRetryPolicy;

/**
 * Created by Kazal on 05-Mar-16.
 */
public class NetworkRetryPolicy {
    private static final int REQUEST_TIMEOUT_FIVE_SECOND = 5000;
    private static final int REQUEST_TIMEOUT_TEN_SECOND = 10000;
    private static final int REQUEST_TIMEOUT_FIFTEEN_SECOND = 15000;

    private static final DefaultRetryPolicy mRetryPolicyForFiveSecond = new
            DefaultRetryPolicy(REQUEST_TIMEOUT_FIVE_SECOND, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    private static final DefaultRetryPolicy mRetryPolicyForTenSecond = new
            DefaultRetryPolicy(REQUEST_TIMEOUT_TEN_SECOND, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    private static final DefaultRetryPolicy mRetryPolicyForFifteenSecond = new
            DefaultRetryPolicy(REQUEST_TIMEOUT_FIFTEEN_SECOND, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    public static DefaultRetryPolicy getRetryPolicyForFiveSecond() {
        return mRetryPolicyForFiveSecond;
    }

    public static DefaultRetryPolicy getRetryPolicyForTenSecond() {
        return mRetryPolicyForTenSecond;
    }

    public static DefaultRetryPolicy getRetryPolicyForFifteenSecond() {
        return mRetryPolicyForFifteenSecond;
    }
}
