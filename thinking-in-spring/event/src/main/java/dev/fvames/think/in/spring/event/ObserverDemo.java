package dev.fvames.think.in.spring.event;

import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * Observable 实现
 *
 * @author
 * @version 2020/9/8 15:39
 */

public class ObserverDemo {

	public static void main(String[] args) {
		EventObservable observable = new EventObservable();
		observable.addObserver(new EventObserver());

		observable.notifyObservers("Hello, World.");
	}

	static class EventObservable extends Observable{
		@Override
		public void notifyObservers(Object arg) {
			super.setChanged();
			super.notifyObservers(new EventObject(arg));
			super.clearChanged();
		}
	}

	static class EventObserver implements Observer {

		@Override
		public void update(Observable o, Object event) {
			EventObject eventObject = (EventObject) event;
			System.out.println("收到事件：" + eventObject);
		}
	}
}
