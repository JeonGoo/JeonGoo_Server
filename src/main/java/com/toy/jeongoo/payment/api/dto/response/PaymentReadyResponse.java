package com.toy.jeongoo.payment.api.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Getter
@NoArgsConstructor
public class PaymentReadyResponse {

    private String tid;
    private String next_redirect_app_url;
    private String next_redirect_mobile_url;
    private String next_redirect_pc_url;
    private String android_app_scheme;
    private String ios_app_scheme;
    private DateTime created_at;

    public PaymentReadyResponse(String tid, String next_redirect_app_url, String next_redirect_mobile_url,
                                String next_redirect_pc_url, String android_app_scheme, String ios_app_scheme, DateTime created_at) {
        this.tid = tid;
        this.next_redirect_app_url = next_redirect_app_url;
        this.next_redirect_mobile_url = next_redirect_mobile_url;
        this.next_redirect_pc_url = next_redirect_pc_url;
        this.android_app_scheme = android_app_scheme;
        this.ios_app_scheme = ios_app_scheme;
        this.created_at = created_at;
    }
}
