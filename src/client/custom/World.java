package client.custom;

public class World {
	public int type, LocFlag;
	public String url, location, ip, name, port;
	public World(int type, String name, String url, String location, String ip, String port, int LocFlag) {
		this.type = type;
		this.name = name;
		this.url = url;
		this.location = location;
		this.ip = ip;
		this.port = port;
		this.LocFlag = LocFlag;
	}
}