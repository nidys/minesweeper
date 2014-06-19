package common.network;

public enum ServerAddress {
	LOCALHOST("127.0.0.1", Port.DEFAULT),

	TMP("89.79.21.3", Port.DEFAULT);
	private String addr;
	public static final String RMI_PLACE = "/roommanager";

	private ServerAddress(String addr, Port p) {
		this.addr = addr + ":" + p.getValue();
	}

	public String getValue() {
		return addr;
	}

	public enum Port {
		DEFAULT("1099");
		private String port;

		private Port(String s) {
			port = s;
		}

		public String getValue() {
			return port;
		}

		public int getInt() {
			return Integer.valueOf(port);
		}
	}
}
