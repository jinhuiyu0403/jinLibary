package com.jin.mylibrary.bluetooth.search;

import android.bluetooth.BluetoothAdapter;

import com.jin.mylibrary.bluetooth.search.classic.BluetoothClassicSearcher;
import com.jin.mylibrary.bluetooth.search.le.BluetoothLESearcher;
import com.jin.mylibrary.bluetooth.search.response.BluetoothSearchResponse;

import static com.jin.mylibrary.bluetooth.Constants.SEARCH_TYPE_BLE;
import static com.jin.mylibrary.bluetooth.Constants.SEARCH_TYPE_CLASSIC;

public class BluetoothSearcher {

	protected BluetoothAdapter mBluetoothAdapter;
	protected BluetoothSearchResponse mSearchResponse;

	public static BluetoothSearcher newInstance(int type) {
		switch (type) {
		case SEARCH_TYPE_CLASSIC:
			return BluetoothClassicSearcher.getInstance();
		case SEARCH_TYPE_BLE:
			return BluetoothLESearcher.getInstance();
		default:
			throw new IllegalStateException(String.format(
					"unknown search type %d", type));
		}
	}

	protected void startScanBluetooth(BluetoothSearchResponse callback) {
		mSearchResponse = callback;
		notifySearchStarted();
	}

	protected void stopScanBluetooth() {
		notifySearchStopped();
		mSearchResponse = null;
	}

	protected void cancelScanBluetooth() {
		notifySearchCanceled();
		mSearchResponse = null;
	}

	private void notifySearchStarted() {
		if (mSearchResponse != null) {
			mSearchResponse.onSearchStarted();
		}
	}

	protected void notifyDeviceFounded(SearchResult device) {
		if (mSearchResponse != null) {
			mSearchResponse.onDeviceFounded(device);
		}
	}

	private void notifySearchStopped() {
		if (mSearchResponse != null) {
			mSearchResponse.onSearchStopped();
		}
	}

	private void notifySearchCanceled() {
		if (mSearchResponse != null) {
			mSearchResponse.onSearchCanceled();
		}
	}
}
