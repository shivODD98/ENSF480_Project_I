package Domian;

import java.util.ArrayList;

import SharedObjects.Document;

/**
 * when NotifySingleton is created, updateList must be called atleast once to
 * load in all promotions
 * 
 * @author danielheyns
 *
 */
public class NotifySingleton implements Subject {

	private ArrayList<Observer> observers;
	public ArrayList<Document> documents;
	private static NotifySingleton instance;

	private NotifySingleton() {
		observers = new ArrayList<Observer>();
		documents = new ArrayList<Document>();
	}

	public static NotifySingleton getInstance() {
		if (instance == null) {
			instance = new NotifySingleton();
		}
		return instance;
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);

	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);

	}

	public void updateList(DocsController docController) {
		documents = new ArrayList<Document>(docController.getPromotions());
		for (int i = 0; i < observers.size(); i++)
			observers.get(i).update();
	}

	public void display() {
		for (int i = 0; i < documents.size(); i++) {
			System.out.println(documents.get(i).toString());
		}
	}

	private void removeNullObservers() {
		for (int i = 0; i < observers.size(); i++) {
			if (observers.get(i) == null) {
				observers.remove(i);
			}
		}
	}

}
