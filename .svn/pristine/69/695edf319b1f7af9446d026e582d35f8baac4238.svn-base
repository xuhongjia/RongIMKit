package io.rong.app.map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import io.rong.imkit.RLog;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.LocationMessage;
import io.rong.message.TextMessage;

public class NewLocationProvider extends InputProvider.ExtendProvider {

	public NewLocationProvider(RongContext arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Drawable obtainPluginDrawable(Context context) {
		// TODO Auto-generated method stub
		return context.getResources().getDrawable(
				io.rong.imkit.R.drawable.rc_ic_location);
	}

	@Override
	public CharSequence obtainPluginTitle(Context context) {
		// TODO Auto-generated method stub
		return context.getString(io.rong.imkit.R.string.rc_plugins_location);
	}

	@Override
	public void onPluginClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(arg0.getContext(),
				ChooseLocationActivity.class);
		startActivityForResult(intent, 10);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK)
			return;
		switch (requestCode) {
		case 10:// 选择地理位置.
		{
			TextMessage msgMessage = TextMessage.obtain("这是一个定位消息.");
			sendMessage(msgMessage);
		}
			break;

		default:
			break;
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	private void sendMessage(MessageContent msg) {
		if (RongIM.getInstance() == null
				|| RongIM.getInstance().getRongIMClient() == null)
			return;

		Conversation conversation = getCurrentConversation();
		RongIM.getInstance()
				.getRongIMClient()
				.insertMessage(conversation.getConversationType(),
						conversation.getTargetId(), null, msg,
						new ResultCallback<Message>() {
							public void onSuccess(Message message) {
								message.setSentStatus(io.rong.imlib.model.Message.SentStatus.SENT);
								RongIM.getInstance()
										.getRongIMClient()
										.setMessageSentStatus(
												message.getMessageId(),
												io.rong.imlib.model.Message.SentStatus.SENT,
												null);
							}

							public void onError(
									io.rong.imlib.RongIMClient.ErrorCode e) {
							}

						});
	}
}