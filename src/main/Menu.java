package main;

import logger.Logger;

public class Menu {
	
	private static MenuPoints points;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger.Log("Menu main");
		Logger.Logout();
	}
	
	public static void showHelp() {
		Logger.Log("Menu showHelp");
		Logger.Logout();
	}
}
