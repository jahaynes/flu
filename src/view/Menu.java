package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {

	public Menu() {
		this.add(createMenuFile());	
	}
	
	private JMenu createMenuFile() {
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(createMenuItemNew());
		fileMenu.add(createMenuItemOpen());
		fileMenu.add(createMenuItemSave());
		fileMenu.add(createMenuItemExit());
		return fileMenu;
	}
	
	private JMenuItem createMenuItemNew() {
		JMenuItem menuItemNew = new JMenuItem("New");	
		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				throw new NotImplementedException();
			}
		});
		return menuItemNew;
	}
	
	private JMenuItem createMenuItemOpen() {
		JMenuItem menuItemNew = new JMenuItem("Open");	
		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				throw new NotImplementedException();
			}
		});
		return menuItemNew;
	}
	
	private JMenuItem createMenuItemSave() {
		JMenuItem menuItemNew = new JMenuItem("Save");	
		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				throw new NotImplementedException();
			}
		});
		return menuItemNew;
	}
		
	private JMenuItem createMenuItemExit() {
		JMenuItem menuItemNew = new JMenuItem("Exit");	
		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Todo: Prompt for save state first
				System.exit(0);
			}
		});
		return menuItemNew;
	}
	
}


