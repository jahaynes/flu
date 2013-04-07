package commands;

public interface Command {
	public void execute();
	public void rollback();	
}