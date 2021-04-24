package com.tech.twestudentside.PaymentGetway;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.cashfree.pg.CFPaymentService;
import com.cashfree.pg.ui.gpay.GooglePayStatusListener;
import com.facebook.share.internal.ShareConstants;
import com.tech.twestudentside.R;
import com.tech.twestudentside.utils.RetrofitClients;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentCashFreeActivity extends AppCompatActivity {
    private static final String TAG = "PaymentCashFreeActivity";
    CFPaymentService cfPaymentService;
    SeamlessMode currentMode = SeamlessMode.CARD;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    String result = "";
    String stage = "TEST";

    enum SeamlessMode {
        CARD,
        WALLET,
        NET_BANKING,
        UPI_COLLECT,
        PAY_PAL
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashfree);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle bundle;
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "ReqCode : " + CFPaymentService.REQ_CODE);
        Log.d(TAG, "API Response : ");
        if (data != null && (bundle = data.getExtras()) != null) {
            for (String key : bundle.keySet()) {
                if (bundle.getString(key) != null) {
                    Log.d(TAG, key + " : " + bundle.getString(key));
                }
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.phonePe_exists) {
            Toast.makeText(this, CFPaymentService.getCFPaymentServiceInstance().doesPhonePeExist(this, this.stage) + "",  Toast.LENGTH_LONG).show();
        } else if (view.getId() == R.id.gpay_ready) {
            CFPaymentService.getCFPaymentServiceInstance().isGPayReadyForPayment(this, new GooglePayStatusListener() {
                public void isReady() {
                    Toast.makeText(PaymentCashFreeActivity.this, "Ready", Toast.LENGTH_LONG).show();
                }

                public void isNotReady() {
                    Toast.makeText(PaymentCashFreeActivity.this, "Not Ready", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            CFPaymentService cFPaymentServiceInstance = CFPaymentService.getCFPaymentServiceInstance();
            this.cfPaymentService = cFPaymentServiceInstance;
            cFPaymentServiceInstance.setOrientation(0);
            switch (view.getId()) {
                case R.id.amazon /*2131361907*/:
                    this.cfPaymentService.doAmazonPayment(this, getInputParams(), "TOKEN_DATA", this.stage);
                    return;
                case R.id.gpay /*2131362126*/:
                    this.cfPaymentService.gPayPayment(this, getInputParams(), "TOKEN_DATA", this.stage);
                    return;
                case R.id.phonePe /*2131362312*/:
                    this.cfPaymentService.phonePePayment(this, getInputParams(), "TOKEN_DATA", this.stage);
                    return;
                case R.id.web /*2131362618*/:
                    this.cfPaymentService.doPayment(this, getInputParams(), "TOKEN_DATA", this.stage, "#784BD2", "#FFFFFF", false);
                    return;
                case R.id.web_seamless /*2131362619*/:
                    this.progressBar.setVisibility(View.VISIBLE);
                    getTokenGenrate("SeemLess");
                    return;
                default:
                    return;
            }
        }
    }

    private Map<String, String> getInputParams() {
        Map<String, String> params = new HashMap<>();
        params.put(CFPaymentService.PARAM_APP_ID, "580149be672f3dfe15da4b31441085");
        params.put(CFPaymentService.PARAM_ORDER_ID, "Order1225");
        params.put(CFPaymentService.PARAM_ORDER_AMOUNT, "40");
        params.put(CFPaymentService.PARAM_ORDER_NOTE, "Test");
        params.put(CFPaymentService.PARAM_CUSTOMER_NAME, "Harshit Asati");
        params.put(CFPaymentService.PARAM_CUSTOMER_PHONE, "8305343233");
        params.put(CFPaymentService.PARAM_CUSTOMER_EMAIL, "harshit.ixora90@gmail.com");
        params.put(CFPaymentService.PARAM_ORDER_CURRENCY, "INR");
        return params;
    }

    /* renamed from: com.tech.twestudentside.PaymentGetway.PaymentCashFreeActivity$3 */
    static /* synthetic */ class C15523 {

        /* renamed from: $SwitchMap$com$tech$twestudentside$PaymentGetway$PaymentCashFreeActivity$SeamlessMode */
        static final /* synthetic */ int[] f411x1ae07ab8;

        static {
            int[] iArr = new int[SeamlessMode.values().length];
            f411x1ae07ab8 = iArr;
            try {
                iArr[SeamlessMode.CARD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f411x1ae07ab8[SeamlessMode.WALLET.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f411x1ae07ab8[SeamlessMode.NET_BANKING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f411x1ae07ab8[SeamlessMode.UPI_COLLECT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f411x1ae07ab8[SeamlessMode.PAY_PAL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    /* access modifiers changed from: private */
    public Map<String, String> getSeamlessCheckoutParams() {
        Map<String, String> params = getInputParams();
        int i = C15523.f411x1ae07ab8[this.currentMode.ordinal()];
        if (i == 1) {
            params.put(CFPaymentService.PARAM_PAYMENT_OPTION, "card");
            params.put(CFPaymentService.PARAM_CARD_NUMBER, "4444333322221111");
            params.put(CFPaymentService.PARAM_CARD_YYYY, "2023");
            params.put(CFPaymentService.PARAM_CARD_MM, "07");
            params.put(CFPaymentService.PARAM_CARD_HOLDER, "Test");
            params.put(CFPaymentService.PARAM_CARD_CVV, "123");
        } else if (i == 2) {
            params.put(CFPaymentService.PARAM_PAYMENT_OPTION, "Paytm");
            params.put("paymentCode", "4007");
        } else if (i == 3) {
            params.put(CFPaymentService.PARAM_PAYMENT_OPTION, "Axis Bank");
            params.put("paymentCode", "3003");
        } else if (i == 4) {
            params.put(CFPaymentService.PARAM_PAYMENT_OPTION, "upi");
            params.put(CFPaymentService.PARAM_UPI_VPA, "VALID_VPA");
        } else if (i == 5) {
            params.put(CFPaymentService.PARAM_PAYMENT_OPTION, "paypal");
        }
        return params;
    }

    private void getTokenGenrate(String Type) {
        RetrofitClients.getInstance().getApi().cashfree_signature("Order1225", "40").enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    PaymentCashFreeActivity.this.progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                    String string = jsonObject.getString("cftoken");
                    if (status.equalsIgnoreCase("OK")) {
                        Toast.makeText(PaymentCashFreeActivity.this, message, Toast.LENGTH_LONG).show();
                        PaymentCashFreeActivity.this.cfPaymentService.doPayment(PaymentCashFreeActivity.this, PaymentCashFreeActivity.this.getSeamlessCheckoutParams(), "token1", PaymentCashFreeActivity.this.stage);
                        return;
                    }
                    Toast.makeText(PaymentCashFreeActivity.this, "", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    PaymentCashFreeActivity.this.progressBar.setVisibility(View.GONE);
                    Toast.makeText(PaymentCashFreeActivity.this, "", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (IOException e2) {
                    PaymentCashFreeActivity.this.progressBar.setVisibility(View.GONE);
                    Toast.makeText(PaymentCashFreeActivity.this, "",  Toast.LENGTH_LONG).show();
                    e2.printStackTrace();
                }
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                PaymentCashFreeActivity.this.progressBar.setVisibility(View.GONE);
                Toast.makeText(PaymentCashFreeActivity.this, "Please Check Network",  Toast.LENGTH_LONG).show();
            }
        });
    }
}
