package main;

import logger.Logger;

public class Menu {
	
	private MenuPoints points;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger.Log("Menu main");
		Logger.Logout();
	}
	
	public void showHelp() {
		Logger.Log("Menu showHelp");
		Logger.Logout();
	}
}
