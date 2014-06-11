package com.bft.login;

import java.io.File;
import java.util.List;

import org.acra.*;
import org.acra.annotation.*;

import com.bft.bo.Spot;
import com.bft.mws.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import android.app.Application;
import android.content.Context;

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

	private List<Spot> spotlist;

	public List<Spot> getSpotlist(){
		return spotlist;
	}
	public void setSpotlist(List<Spot> s){
		spotlist = s;
	}

	@Override
	public void onCreate() {
		// The following line triggers the initialization of ACRA
		super.onCreate();
		ACRA.init(this);
		initImageLoader(getApplicationContext());

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
		ImageLoader.getInstance().init(config);

	}

	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.build();


		File cacheDir = StorageUtils.getCacheDirectory(context);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
		.threadPriority(Thread.NORM_PRIORITY - 2)
		.denyCacheImageMultipleSizesInMemory()
		.discCache(new UnlimitedDiscCache(cacheDir))
		.defaultDisplayImageOptions(defaultOptions)
		.discCacheFileNameGenerator(new Md5FileNameGenerator())
		.tasksProcessingOrder(QueueProcessingType.LIFO)
		//.writeDebugLogs() // Remove for release app
		.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}