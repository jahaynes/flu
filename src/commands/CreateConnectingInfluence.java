package commands;

import helper.ModelViewFactory;

public class CreateConnectingInfluence implements Command {
	
	private int stock1;
	private int stock2;
	
	public CreateConnectingInfluence(int stock1, int stock2) {
		this.stock1 = stock1;
		this.stock2 = stock2;
	}
	
	@Override
	public void execute() {
		System.out.println("Connected: " + stock1 + " to " + stock2);
		ModelViewFactory.createInfluence();
	}

	@Override
	public void rollback() {
		System.out.println("Disconnected: " + stock1 + " from " + stock2);
	}
	
	
}
