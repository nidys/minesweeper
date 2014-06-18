package client.models;

import java.util.Observable;

public class MainModel extends Observable {

	/**
	 * Example usage of an Observer pattern (not sure if we'll use it). Change
	 * in model will fire proper reaction in every observer (especially
	 * MainView). DEPRECATED.
	 */
	public void fakeModelChange() {
		setChanged();
		notifyObservers();
	}
}
