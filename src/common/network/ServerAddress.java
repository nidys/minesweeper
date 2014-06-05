package common.network;

public enum ServerAddress {
	LOCALHOST("127.0.0.1:1099");
	private String addr;

	private ServerAddress(String s) {
		addr = s;
	}

	public String getValue() {
		return addr;
	}
}
