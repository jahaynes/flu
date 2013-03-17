package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

@SuppressWarnings("serial")
public class Explorer extends JPanel {

	/* Layout */
	private static final Dimension componentDimension = new Dimension(175, 40);
	private static final Dimension smallRigidArea = new Dimension(0, 20);
	private static final Dimension largeRigidArea = new Dimension(200, 40);
	private static final float xAlign = 0.5f;

	private static final EmptyBorder emptyBorder = new EmptyBorder(0,0,0,0);
	
	public Explorer() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.attachChildren();
		this.setBackground(new Color(213, 221, 226));
	}
	
	private void attachChildren() {
		
		decorateAndAdd(emptyBorder, componentDimension, xAlign, new JLabel("Flow Explorer"));
		
		add(Box.createRigidArea(smallRigidArea));
		
		addNameSection();
		
		add(Box.createRigidArea(largeRigidArea));
		
		addExpressionSection();
		
		add(Box.createRigidArea(largeRigidArea));
		
		addCurrentValueSection();
		
		add(Box.createRigidArea(new Dimension(0, 20)));
		
		addStopConditionSection();
		
		add(Box.createRigidArea(new Dimension(0, 60)));
	}
	
	private void addNameSection() {
		JTextField txtName = new JTextField();		
		txtName.getDocument().addUndoableEditListener(new UndoableEditListener() {
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				// TODO Auto-generated method stub
			}
		});
		decorateAndAdd(emptyBorder, componentDimension, xAlign, new JLabel("Name"), txtName);
	}
	
	private void addExpressionSection() {
		JTextArea txtExpr = new JTextArea();
		txtExpr.getDocument().addUndoableEditListener(new UndoableEditListener() {
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				// TODO Auto-generated method stub
			}
		});
		decorateAndAdd(emptyBorder, componentDimension, xAlign, new JLabel("Expression"), txtExpr);
	}	
	
	private void addCurrentValueSection() {
		JTextField txtCurrentValue = new JTextField();		
		txtCurrentValue.getDocument().addUndoableEditListener(new UndoableEditListener() {
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				// TODO Auto-generated method stub
			}
		});
		decorateAndAdd(emptyBorder, componentDimension, xAlign, new JLabel("Current Value"), txtCurrentValue);
	}
	
	private void addStopConditionSection() {
		JTextField txtStopcondition = new JTextField();		
		txtStopcondition.getDocument().addUndoableEditListener(new UndoableEditListener() {
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				// TODO Auto-generated method stub
			}
		});
		decorateAndAdd(emptyBorder, componentDimension, xAlign, new JLabel("Stop Condition"), txtStopcondition);
	}
	
	
	private void decorateAndAdd(Border border, Dimension maxSize, float alignX, JComponent... components) {
		for(JComponent c : components) {
			c.setMaximumSize(maxSize);
			c.setAlignmentX(alignX);
			c.setBorder(border);
			add(c);
		}
	}
	
}
