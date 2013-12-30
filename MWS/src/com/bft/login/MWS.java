package com.bft.login;

import org.acra.*;
import org.acra.annotation.*;

import com.bft.mws.R;

import android.app.Application;

@ReportsCrashes(formKey = "", 
		formUri = "https://fremault.cloudant.com/acra-mws/_design/acra-storage/_update/report",
		reportType = org.acra.sender.HttpSender.Type.JSON,
		httpMethod = org.acra.sender.HttpSender.Method.PUT,
		formUriBasicAuthLogin="sedentallyptiesseciestio",
		formUriBasicAuthPassword="DkMT3dQMnryo4VI8aKTkI0Ux",
		// Your usual ACRA configuration
		mode = ReportingInteractionMode.TOAST,
		resToastText = R.string.crash_toast_text
		)
public class MWS extends Application {
  @Override
  public void onCreate() {
    // The following line triggers the initialization of ACRA
    super.onCreate();
    ACRA.init(this);
  }
}