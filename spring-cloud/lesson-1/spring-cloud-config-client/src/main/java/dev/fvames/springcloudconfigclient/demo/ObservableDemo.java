package dev.fvames.springcloudconfigclient.demo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @version 2019/7/22 15:33
 */

public class ObservableDemo {

	public static void main(String[] args) {

		MyObservable observable = new MyObservable();
		observable.addObserver(new Observer() {

			public void update(Observable o, Object arg) {
				System.out.println(arg);
			}
		});

		observable.setChanged();
		// 推模式，订阅者被通知
		observable.notifyObservers("Hello, World");

		echoIterator();
	}

	private static void echoIterator() {

		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			// 主动获取，拉模式
			System.out.println(iterator.next());
		}
	}

	public static class MyObservable extends Observable{

		/**
		 * 调用 setChange() 后才能执行到通知操作
		 */
		@Override
		protected synchronized void setChanged() {
			super.setChanged();
		}
	}
}
