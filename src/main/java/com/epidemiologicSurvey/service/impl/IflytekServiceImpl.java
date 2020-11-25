package com.epidemiologicSurvey.service.impl;

import java.io.FileInputStream;
import java.io.IOException;

import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.upload.TempFile;

import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.service.IflytekService;
import com.epidemiologicSurvey.utils.ResponseVo;
import com.iflytek.cloud.speech.RecognizerListener;
import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechRecognizer;
import com.iflytek.cloud.speech.SpeechUtility;

@IocBean(create = "init")
public class IflytekServiceImpl implements IflytekService {

	private static final Log logger = Logs.get();

	@Inject
	protected PropertiesProxy conf;

	private String appId;
	private RecognizerListener recListener;
	private boolean mIsEndOfSpeech = false;
	private static StringBuffer mResult = new StringBuffer();
	private static JSONObject rst = new JSONObject();

	public void init() {
		appId = conf.get("iflytek.appId");
		SpeechUtility.createUtility("appid=" + appId);

		recListener = new RecognizerListener() {
			public void onBeginOfSpeech() {
				logger.debug("onBeginOfSpeech enter");
				logger.debug("*************开始录音*************");
			}

			public void onEndOfSpeech() {
				logger.debug("onEndOfSpeech enter");
				mIsEndOfSpeech = true;
			}

			public void onVolumeChanged(int volume) {
				logger.debug("onVolumeChanged enter");
				if (volume > 0)
					logger.debug("*************音量值:" + volume + "*************");

			}

			public void onResult(RecognizerResult result, boolean islast) {
				logger.debug("===============================>onResult enter");
				mResult.append(result.getResultString());

				if (islast) {
					logger.debug("识别结果为:" + mResult.toString());
					rst.put("result", mResult.toString());
					mIsEndOfSpeech = true;
					mResult.delete(0, mResult.length());
					waitupLoop();
				}
			}

			public void onError(SpeechError error) {
				mIsEndOfSpeech = true;
				logger.debug("*************" + error.getErrorDesc() + "*************");
				logger.debug("*************" + error.getErrorCode() + "*************");
				waitupLoop();
			}

			public void onEvent(int eventType, int arg1, int agr2, String msg) {
				logger.debug("onEvent enter");
			}

		};

	}

	private void waitupLoop() {
		synchronized (this) {
			IflytekServiceImpl.this.notify();
		}
	}

	public JSONObject RecognizePcmfileByte(TempFile tmpFile) {
		SpeechRecognizer recognizer = SpeechRecognizer.getRecognizer();
		recognizer.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");
		// 采样率8000 需与前端配置保持一致
		recognizer.setParameter(SpeechConstant.SAMPLE_RATE, "8000");
		recognizer.setParameter(SpeechConstant.RESULT_TYPE, "plain");
		recognizer.startListening(recListener);

		FileInputStream fis = null;
		final byte[] buffer = new byte[64 * 1024];

		try {
			fis = new FileInputStream(tmpFile.getFile());
			if (0 == fis.available()) {
				logger.debug("==================================>no audio avaible!");
				mResult.append("no audio avaible!");
				recognizer.cancel();
			} else {
				int lenRead = buffer.length;
				while (buffer.length == lenRead && !mIsEndOfSpeech) {
					lenRead = fis.read(buffer);
					recognizer.writeAudio(buffer, 0, lenRead);
				}
				recognizer.stopListening();
				while (recognizer.isListening()) {
					//TODO 没找到更好的解决方案
					Thread.sleep(100);
				}
				logger.debug("=====================>rst:" + rst.getString("result"));
				return ResponseVo.ok(rst);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseVo.error("识别失败");
		} finally {
			try {
				if (null != fis) {
					fis.close();
					fis = null;
				}
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		return ResponseVo.error("识别失败");

	}

	@Override
	public JSONObject listenToWrite(TempFile tmpFile) {
		if (SpeechRecognizer.getRecognizer() == null) {
			SpeechRecognizer.createRecognizer();
		}
		mIsEndOfSpeech = false;
		return	RecognizePcmfileByte(tmpFile);
	}

}
